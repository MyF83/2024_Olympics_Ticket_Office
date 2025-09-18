import { Injectable } from '@angular/core';
import { Gamesinterface } from '../interfaces/gamesinterface';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GameserviceService {
  private apiUrl = '/api/event'; // Backend URL for events
  
  
  // url = 'http://localhost:4200/games';


  /* Avant de passer à HttpClient plutîot que fetch :
  constructor() {}

  async getGames(): Promise<Gamesinterface[]> {
    const data = await fetch(this.apiUrl);
    return await data.json() ?? [];
  }

  async getGameById(id: Number): Promise<Gamesinterface | undefined> {
    const data = await fetch(`${this.apiUrl}/${id}`);
    return await data.json() ?? {};
  }*/

    constructor(private http: HttpClient) {}
  
    getGames(): Observable<Gamesinterface[]> {
    return this.http.get<Gamesinterface[]>(this.apiUrl);
  }

getEvents(): Observable<Event[]> {
  return this.http.get<Event[]>('/api/event/all');
}

}
