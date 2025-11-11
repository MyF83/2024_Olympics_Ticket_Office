import { TestBed } from '@angular/core/testing';
import { AuthStateService } from './auth-state.service';

describe('AuthStateService', () => {
  let service: AuthStateService;
  let mockSessionStorage: { [key: string]: string };
  let mockLocalStorage: { [key: string]: string };

  beforeEach(() => {
    // Mock storage data
    mockSessionStorage = {};
    mockLocalStorage = {};

    // Create spies for sessionStorage
    spyOn(sessionStorage, 'getItem').and.callFake((key: string) => mockSessionStorage[key] || null);
    spyOn(sessionStorage, 'setItem').and.callFake((key: string, value: string) => mockSessionStorage[key] = value);
    spyOn(sessionStorage, 'removeItem').and.callFake((key: string) => delete mockSessionStorage[key]);

    // Create spies for localStorage
    spyOn(localStorage, 'getItem').and.callFake((key: string) => mockLocalStorage[key] || null);
    spyOn(localStorage, 'setItem').and.callFake((key: string, value: string) => mockLocalStorage[key] = value);
    spyOn(localStorage, 'removeItem').and.callFake((key: string) => delete mockLocalStorage[key]);

    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthStateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('checkLoginStatus()', () => {
    it('should return true when authToken exists in sessionStorage', () => {
      mockSessionStorage['authToken'] = 'test-token';
      
      const result = service.checkLoginStatus();
      
      expect(result).toBe(true);
    });

    it('should return true when token exists in sessionStorage', () => {
      mockSessionStorage['token'] = 'test-token';
      
      const result = service.checkLoginStatus();
      
      expect(result).toBe(true);
    });

    it('should return true when authToken exists in localStorage', () => {
      mockLocalStorage['authToken'] = 'test-token';
      
      const result = service.checkLoginStatus();
      
      expect(result).toBe(true);
    });

    it('should return true when token exists in localStorage', () => {
      mockLocalStorage['token'] = 'test-token';
      
      const result = service.checkLoginStatus();
      
      expect(result).toBe(true);
    });

    it('should return false when no token exists and log message', () => {
      spyOn(console, 'log');
      
      const result = service.checkLoginStatus();
      
      expect(result).toBe(false);
      expect(console.log).toHaveBeenCalledWith('🔍 No valid login status found');
    });

    it('should update isLoggedIn$ observable when checking status', () => {
      mockSessionStorage['authToken'] = 'test-token';
      let isLoggedInValue = false;
      
      service.isLoggedIn$.subscribe(value => isLoggedInValue = value);
      service.checkLoginStatus();
      
      expect(isLoggedInValue).toBe(true);
    });
  });

  describe('setLoggedIn()', () => {
    it('should update the isLoggedIn$ observable', () => {
      let isLoggedInValue = false;
      
      service.isLoggedIn$.subscribe(value => isLoggedInValue = value);
      service.setLoggedIn(true);
      
      expect(isLoggedInValue).toBe(true);
    });

    it('should update to false when setting logged out', () => {
      let isLoggedInValue = true;
      
      service.isLoggedIn$.subscribe(value => isLoggedInValue = value);
      service.setLoggedIn(false);
      
      expect(isLoggedInValue).toBe(false);
    });
  });

  describe('setCurrentUser()', () => {
    it('should update currentUser$ observable and store in localStorage', () => {
      const testUser = { id: 1, firstname: 'Jane', lastname: 'Smith', email: 'jane@test.com' };
      let currentUserValue: any = null;
      
      service.currentUser$.subscribe(user => currentUserValue = user);
      service.setCurrentUser(testUser);
      
      expect(currentUserValue).toEqual(testUser);
      expect(localStorage.setItem).toHaveBeenCalledWith('currentUser', JSON.stringify(testUser));
    });

    it('should remove user from localStorage when setting null', () => {
      let currentUserValue: any = { id: 1 };
      
      service.currentUser$.subscribe(user => currentUserValue = user);
      service.setCurrentUser(null);
      
      expect(currentUserValue).toBeNull();
      expect(localStorage.removeItem).toHaveBeenCalledWith('currentUser');
    });
  });

  describe('getCurrentUser()', () => {
    it('should return current user value', () => {
      const testUser = { id: 1, firstname: 'Bob', email: 'bob@test.com' };
      service.setCurrentUser(testUser);
      
      const result = service.getCurrentUser();
      
      expect(result).toEqual(testUser);
    });

    it('should return null when no user is set', () => {
      service.setCurrentUser(null);
      
      const result = service.getCurrentUser();
      
      expect(result).toBeNull();
    });
  });

  describe('logout()', () => {
    it('should call performCleanLogout and log message', () => {
      spyOn(console, 'log');
      spyOn<any>(service, 'performCleanLogout');
      
      service.logout();
      
      expect(console.log).toHaveBeenCalledWith('🚪 AuthStateService: Performing logout');
      expect(service['performCleanLogout']).toHaveBeenCalled();
    });
  });

  describe('performCleanLogout()', () => {
    it('should clear session storage keys', () => {
      service.logout();
      
      expect(sessionStorage.removeItem).toHaveBeenCalledWith('token');
      expect(sessionStorage.removeItem).toHaveBeenCalledWith('authToken');
      expect(sessionStorage.removeItem).toHaveBeenCalledWith('currentUser');
      expect(sessionStorage.removeItem).toHaveBeenCalledWith('lastView');
      expect(sessionStorage.removeItem).toHaveBeenCalledWith('lastActivityTime');
      expect(sessionStorage.removeItem).toHaveBeenCalledWith('draftCarts');
    });

    it('should clear local storage keys except rememberMe', () => {
      service.logout();
      
      expect(localStorage.removeItem).toHaveBeenCalledWith('token');
      expect(localStorage.removeItem).toHaveBeenCalledWith('authToken');
      expect(localStorage.removeItem).toHaveBeenCalledWith('currentUser');
      expect(localStorage.removeItem).not.toHaveBeenCalledWith('rememberMe');
    });

    it('should update auth state to logged out', () => {
      let isLoggedInValue = true;
      let currentUserValue: any = { id: 1 };
      
      service.isLoggedIn$.subscribe(value => isLoggedInValue = value);
      service.currentUser$.subscribe(user => currentUserValue = user);
      
      service.logout();
      
      expect(isLoggedInValue).toBe(false);
      expect(currentUserValue).toBeNull();
    });

    it('should log completion message', () => {
      spyOn(console, 'log');
      
      service.logout();
      
      expect(console.log).toHaveBeenCalledWith('✅ Session cleanup completed');
    });
  });

  describe('registerCleanupHandler()', () => {
    it('should add handler to cleanup handlers array', () => {
      const handler = jasmine.createSpy('handler');
      
      service.registerCleanupHandler(handler);
      service.logout();
      
      expect(handler).toHaveBeenCalled();
    });

    it('should allow multiple handlers to be registered', () => {
      const handler1 = jasmine.createSpy('handler1');
      const handler2 = jasmine.createSpy('handler2');
      const handler3 = jasmine.createSpy('handler3');
      
      service.registerCleanupHandler(handler1);
      service.registerCleanupHandler(handler2);
      service.registerCleanupHandler(handler3);
      
      service.logout();
      
      expect(handler1).toHaveBeenCalled();
      expect(handler2).toHaveBeenCalled();
      expect(handler3).toHaveBeenCalled();
    });
  });

  describe('validateSession()', () => {
    it('should return true when authToken exists in sessionStorage', () => {
      mockSessionStorage['authToken'] = 'valid-token';
      spyOn(console, 'log');
      
      const result = service.validateSession();
      
      expect(result).toBe(true);
      expect(console.log).toHaveBeenCalledWith('✅ Session token is valid');
    });

    it('should return true when token exists in localStorage', () => {
      mockLocalStorage['token'] = 'valid-token';
      spyOn(console, 'log');
      
      const result = service.validateSession();
      
      expect(result).toBe(true);
      expect(console.log).toHaveBeenCalledWith('✅ Session token is valid');
    });

    it('should return false when no token exists and log message', () => {
      spyOn(console, 'log');
      
      const result = service.validateSession();
      
      expect(result).toBe(false);
      expect(console.log).toHaveBeenCalledWith('❌ No valid session token found');
    });

    it('should prioritize sessionStorage authToken over other tokens', () => {
      mockSessionStorage['authToken'] = 'session-auth-token';
      mockSessionStorage['token'] = 'session-token';
      mockLocalStorage['authToken'] = 'local-auth-token';
      mockLocalStorage['token'] = 'local-token';
      
      const result = service.validateSession();
      
      expect(result).toBe(true);
      expect(sessionStorage.getItem).toHaveBeenCalledWith('authToken');
    });
  });

  describe('forceLogoutAllTabs()', () => {
    it('should remove tokens from localStorage and perform logout', () => {
      spyOn<any>(service, 'performCleanLogout');
      
      service.forceLogoutAllTabs();
      
      expect(localStorage.removeItem).toHaveBeenCalledWith('authToken');
      expect(localStorage.removeItem).toHaveBeenCalledWith('token');
      expect(service['performCleanLogout']).toHaveBeenCalled();
    });
  });
});