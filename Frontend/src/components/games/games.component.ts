import { Component, OnInit } from '@angular/core';
// import { GamesCardComponent } from './gamescard/gamescard/gamescard.component';
import { GameserviceService } from '../../services/gameservice.service';
import { Gamesinterface } from '../../interfaces/gamesinterface';
import { CommonModule } from '@angular/common'; // Import CommonModule
import { GamesmodalComponent } from './gamesmodal/gamesmodal/gamesmodal.component';
import { MatDialog } from '@angular/material/dialog'; // Import MatDialog
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-home',
  imports: [CommonModule,FormsModule/*,GamesCardComponent*/ ], // Add CommonModule here
  standalone: true,
  templateUrl: './games.component.html',
  styleUrls: ['./games.component.css']
})
export class HomeComponent implements OnInit {

  searchTerm: string = '';
  games: Gamesinterface[] = [];
  filteredGames: Gamesinterface[] = [];

  constructor(
    private gameService: GameserviceService, 
    private dialog: MatDialog
  ) {}

  async ngOnInit() {
    console.log('Fetching games...'); // Log when fetching games
    this.gameService.getGames().subscribe((games: Gamesinterface[]) => {
      this.games = games;
      this.filteredGames = games;
      
    });
    /*this.dialogRef.open(GamesmodalComponent, {
      data: this.gameService, // Pass the game data to the modal
    });
    console.log("Modal opened with game data:", this.gameService); // Log the game data passed to the modal
  */}

  filterGames(): void {
    const term = this.searchTerm.trim().toLowerCase();
    this.filteredGames = this.games.filter(game =>
      game.title.toLowerCase().includes(term) ||
      game.description.toLowerCase().includes(term) ||
      game.date.toLowerCase().includes(term) ||
      (game.sports && game.sports?.name && game.sports.name.toLowerCase().includes(term)) ||
      (game.sports && game.sports?.description && game.sports.description.toLowerCase().includes(term)) ||
      (game.sports && game.sports?.date && game.sports.date.toLowerCase().includes(term)) ||
      (game.sports && game.challenger1?.name && game.challenger1.name.toLowerCase().includes(term)) ||
      (game.sports && game.challenger2?.name && game.challenger2.name.toLowerCase().includes(term)) ||
      (game.sports && game.sports.sites && game.sports?.sites?.name && game.sports.sites.name.toLowerCase().includes(term)) ||
      (game.sports && game.sports.sites && game.sports?.sites?.city && game.sports.sites.city.toLowerCase().includes(term)) ||
      (game.ceremonies && game.ceremonies?.name && game.ceremonies.name.toLowerCase().includes(term)) ||
      (game.ceremonies && game.ceremonies?.description && game.ceremonies.description.toLowerCase().includes(term)) ||
      (game.ceremonies && game.ceremonies.sites && game.ceremonies?.sites?.name && game.ceremonies.sites.name.toLowerCase().includes(term)) ||
      (game.ceremonies && game.ceremonies.sites && game.ceremonies?.sites?.city && game.ceremonies.sites.city.toLowerCase().includes(term)) 
    );
    console.log("The filter is used : " + this.filteredGames)
  }

  openDetails(game: Gamesinterface): void {
      this.dialog.open(GamesmodalComponent, {
      width: '1100px',
      panelClass: 'custom-modal',
      data: game // Pass the game data to the modal
    });
    console.log('Game details:', game); // Log the game details to the console
  }
}
  
  
    


