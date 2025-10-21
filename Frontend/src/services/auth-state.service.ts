import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

interface User {
  id?: number;
  firstname?: string;
  lastname?: string;
  email?: string;
  username?: string;
  role_id?: number;
}

@Injectable({
  providedIn: 'root'
})
export class AuthStateService {
  private isLoggedInSubject = new BehaviorSubject<boolean>(false);
  public isLoggedIn$ = this.isLoggedInSubject.asObservable();
  
  private currentUserSubject = new BehaviorSubject<User | null>(null);
  public currentUser$ = this.currentUserSubject.asObservable();

  private sessionCleanupHandlers: (() => void)[] = [];
  private readonly SESSION_STORAGE_KEYS = [
    'token', 'authToken', 'currentUser', 'lastView', 'lastActivityTime', 'draftCarts'
  ];
  private readonly LOCAL_STORAGE_KEYS = [
    'token', 'authToken', 'currentUser', 'rememberMe'
  ];

  constructor() {
    // Initialize login state and user data from storage
    this.checkLoginStatus();
    this.loadCurrentUser();
    this.initializeSessionCleanup();
  }

  // Initialize session cleanup mechanisms
  private initializeSessionCleanup(): void {
    // Listen for storage events (cross-tab logout)
    if (typeof window !== 'undefined') {
      window.addEventListener('storage', (event) => {
        if (event.key === 'authToken' || event.key === 'token') {
          if (!event.newValue) {
            console.log('🔄 Cross-tab logout detected');
            this.performCleanLogout();
          }
        }
      });
    }
  }

  checkLoginStatus(): boolean {
    const token = sessionStorage.getItem('authToken') || 
                  sessionStorage.getItem('token') || 
                  localStorage.getItem('authToken') || 
                  localStorage.getItem('token');
    const isLoggedIn = !!token;
    this.isLoggedInSubject.next(isLoggedIn);
    
    if (!isLoggedIn) {
      console.log('🔍 No valid login status found');
    }
    
    return isLoggedIn;
  }

  setLoggedIn(value: boolean): void {
    this.isLoggedInSubject.next(value);
  }
  
  /**
   * Load current user data from storage
   */
  private loadCurrentUser(): void {
    if (typeof window !== 'undefined') {
      const userJson = localStorage.getItem('currentUser') || sessionStorage.getItem('currentUser');
      if (userJson) {
        try {
          const user = JSON.parse(userJson);
          this.currentUserSubject.next(user);
        } catch (error) {
          console.error('Error parsing user data:', error);
          this.currentUserSubject.next(null);
        }
      }
    }
  }
  
  /**
   * Set current user data
   * @param user The user data to set
   */
  setCurrentUser(user: User | null): void {
    this.currentUserSubject.next(user);
    if (user && typeof window !== 'undefined') {
      localStorage.setItem('currentUser', JSON.stringify(user));
    } else if (typeof window !== 'undefined') {
      localStorage.removeItem('currentUser');
    }
  }
  
  /**
   * Get current user data
   * @returns The current user or null if not logged in
   */
  getCurrentUser(): User | null {
    return this.currentUserSubject.getValue();
  }
  
  /**
   * Clear all auth data with comprehensive cleanup
   */
  logout(): void {
    console.log('🚪 AuthStateService: Performing logout');
    this.performCleanLogout();
  }

  /**
   * Perform comprehensive logout cleanup
   */
  private performCleanLogout(): void {
    // Execute custom cleanup handlers first
    this.sessionCleanupHandlers.forEach(handler => {
      try {
        handler();
      } catch (error) {
        console.error('Error in session cleanup handler:', error);
      }
    });

    // Clear all session storage keys
    if (typeof window !== 'undefined') {
      this.SESSION_STORAGE_KEYS.forEach(key => {
        sessionStorage.removeItem(key);
      });
      
      // Clear local storage keys (except rememberMe settings)
      this.LOCAL_STORAGE_KEYS.forEach(key => {
        if (key !== 'rememberMe') {
          localStorage.removeItem(key);
        }
      });
    }

    // Update auth state
    this.setLoggedIn(false);
    this.currentUserSubject.next(null);
    
    console.log('✅ Session cleanup completed');
  }

  /**
   * Register a cleanup handler to be called during logout
   */
  registerCleanupHandler(handler: () => void): void {
    this.sessionCleanupHandlers.push(handler);
  }

  /**
   * Validate current session token
   */
  validateSession(): boolean {
    const token = sessionStorage.getItem('authToken') || 
                  sessionStorage.getItem('token') || 
                  localStorage.getItem('authToken') || 
                  localStorage.getItem('token');
    
    if (!token) {
      console.log('❌ No valid session token found');
      // Don't automatically logout - let caller decide
      return false;
    }
    
    // Additional token validation logic could go here
    // (e.g., checking expiration, format, etc.)
    console.log('✅ Session token is valid');
    return true;
  }

  /**
   * Force logout all tabs/windows
   */
  forceLogoutAllTabs(): void {
    if (typeof window !== 'undefined') {
      // Remove token from localStorage to trigger storage event in other tabs
      localStorage.removeItem('authToken');
      localStorage.removeItem('token');
    }
    this.performCleanLogout();
  }
}