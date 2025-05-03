import { Component, OnInit } from '@angular/core';
import { GamesCardComponent } from './gamescard/gamescard/gamescard.component';
import { GameserviceService } from '../../services/gameservice.service';
import { Gamesinterface } from '../../interfaces/gamesinterface';
import { CommonModule } from '@angular/common'; // Import CommonModule



@Component({
  selector: 'app-home',
  imports: [/*GamesCardComponent,*/ CommonModule], // Add CommonModule here
  standalone: true,
  templateUrl: './games.component.html',
  styleUrl: './games.component.css'
})
export class HomeComponent implements OnInit {
  games: Gamesinterface[] = [];

  constructor(private gameService: GameserviceService) {}

  async ngOnInit() {
    this.gameService.getGames().subscribe((games: Gamesinterface[]) => {
      this.games = games;
    });
  }
}
