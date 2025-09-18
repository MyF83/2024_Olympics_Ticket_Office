import { Injectable } from '@angular/core';
import { Offersinterface } from '../interfaces/offersinterface';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OfferserviceService {
  private apiUrl = '/api/offer';

  constructor(private http: HttpClient) { }

  getOffers(): Observable<Offersinterface[]> {
      return this.http.get<Offersinterface[]>(this.apiUrl);
  }
} 
