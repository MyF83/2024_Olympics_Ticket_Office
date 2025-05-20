import { Injectable } from '@angular/core';
import { Securitypolicy } from '../interfaces/securitypolicy';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SecuritypolicyService { // it corresponds to the Policies table's service
  private apiUrl = 'http://localhost:8080/api/policy';

  constructor(private http: HttpClient) { }

  getPolicies(): Observable<Securitypolicy[]> {
      return this.http.get<Securitypolicy[]>(this.apiUrl);
  }
  
}
