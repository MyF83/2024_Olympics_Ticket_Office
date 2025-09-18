import { Injectable } from '@angular/core';
import { AccountsInterface } from '../interfaces/accountsinterface';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { of } from 'rxjs'; // Import 'of' for mock data

@Injectable({
  providedIn: 'root'
})
export class AccountsServiceService {
  updateUser(updatedUser: { firstname: string; lastname: string; email: string; password: string; phoneNumber: string; address: string; city: string; postalCode: string; country: string; }) {
    throw new Error('Method not implemented.');
  }
   
  updateDataUser(userData: any): Observable<any> {
    // Return the HTTP request as an Observable
return this.http.put(`/api/user/${userData.user_id}`, userData);
  }


  private apiUrl = '/api/user';

  constructor(private http: HttpClient) { }

  getAccounts(): Observable<AccountsInterface[]> {
    return this.http.get<AccountsInterface[]>(`${this.apiUrl}`);
  }

  login(username: string, password: string) {
    return this.http.post<any>('/api/user/login', { username, password });
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

}
