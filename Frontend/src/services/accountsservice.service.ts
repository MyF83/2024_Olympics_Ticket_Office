import { Injectable } from '@angular/core';
import { AccountsInterface } from '../interfaces/accountsinterface';
import { HttpClient } from '@angular/common/http';
import { Observable, of, tap } from 'rxjs'; // Import 'of' and 'tap' for data operations
import { AuthStateService } from './auth-state.service';

@Injectable({
  providedIn: 'root'
})
export class AccountsServiceService {
  updateUser(updatedUser: { firstname: string; lastname: string; email: string; password: string; phoneNumber: string; address: string; city: string; postalCode: string; country: string; }) {
    throw new Error('Method not implemented.');
  }
  
  createTicket(ticket: any): Observable<any> {
    return this.http.post<any>('/api/user/tickets', ticket);
  }

  getUserTickets(userId: number): Observable<any[]> {
    return this.http.get<any[]>(`/api/tickets/user/${userId}`);
  }
   
  updateDataUser(userData: any): Observable<any> {
    // Log the data being sent (especially password)
    console.log('Updating user data:', {
      ...userData,
      password: userData.password ? 'PASSWORD_PROVIDED' : 'NO_PASSWORD'
    });
    
    // Return the HTTP request as an Observable
    return this.http.put(`/api/user/${userData.user_id}`, userData);
  }


  private apiUrl = '/api/user';

  constructor(
    private http: HttpClient,
    private authStateService: AuthStateService
  ) { }

  getAccounts(): Observable<AccountsInterface[]> {
    return this.http.get<AccountsInterface[]>(`${this.apiUrl}`);
  }

  login(username: string, password: string) {
    // First clear any existing token
    sessionStorage.removeItem('token');
    
    // Use no-cache headers to prevent cached responses
    const httpOptions = {
      headers: { 'Cache-Control': 'no-cache, no-store, must-revalidate', 'Pragma': 'no-cache' }
    };
    
    return this.http.post<any>('/api/user/login', { username, password }, httpOptions).pipe(
      tap(response => {
        if (response && response.token) {
          // Update auth state service
          this.authStateService.setLoggedIn(true);
        }
      })
    );
  }
  
  logout() {
    // Get the token before it might be cleared by the caller
    const token = sessionStorage.getItem('token');
    
    // Set up headers to prevent caching
    const httpOptions = {
      headers: { 
        'Cache-Control': 'no-cache, no-store, must-revalidate', 
        'Pragma': 'no-cache' 
      }
    };
    
    // Update auth state
    this.authStateService.setLoggedIn(false);
    
    // Always return a successful observable even if the HTTP request fails
    return new Observable<any>(observer => {
      this.http.post<any>('/api/user/logout', { token: token ? `Bearer ${token}` : null }, httpOptions)
        .subscribe({
          next: (response) => {
            observer.next(response);
            observer.complete();
          },
          error: (err) => {
            console.error('Logout HTTP error:', err);
            // Consider the logout successful even if the server request failed
            // This ensures the client-side logout flow completes
            observer.next({ message: 'Logged out locally' });
            observer.complete();
          }
        });
    });
  }

  registerUser(data: { firstname: string; lastname: string; email: string; password: string; policyId: number }) {
    return this.http.post<AccountsInterface>(
      '/api/user/register',
      data
    );
  }

  

  createAccount(accountData: {
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    phoneNumber: string;
    address: string;
    city: string;
    postalCode: string;
    country: string;
}): Observable<{ username: string }> {
    return this.http.post<{ username: string }>(`${this.apiUrl}/create`, accountData);
  }

  getUserByUsername(username: string): Observable<AccountsInterface> {
    return this.http.get<AccountsInterface>(`${this.apiUrl}/username/${username}`);
  }

  getCurrentUser(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/me`);
  }

}
