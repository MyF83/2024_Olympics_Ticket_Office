import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class InfosService {
  constructor(private http: HttpClient) {}

  getSports(): Observable<any[]> {
    return this.http.get<any[]>('/api/sport');
  }

  getSites(): Observable<any[]> {
    return this.http.get<any[]>('/api/site');
  }
  

  getSportsWithSites(): Observable<any> {
    return forkJoin({
      sport: this.getSports(),
      site: this.getSites()
    });
  }
}