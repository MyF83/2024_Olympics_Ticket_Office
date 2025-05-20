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
  imports: [CommonModule,FormsModule], // Add CommonModule here
  standalone: true,
  templateUrl: './games.component.html',
  styleUrls: ['./games.component.css']
})
export class HomeComponent implements OnInit {

  searchTerm: string = '';
  games: Gamesinterface[] = [];
  filteredGames: Gamesinterface[] = [];
  event: any[] = []; // Initialize event as an empty array

  constructor(
    private gameService: GameserviceService, 
    private dialog: MatDialog
  ) {}

  ngOnInit() {
    this.gameService.getEvents().subscribe({
      next: (events: any[]) => { // Use 'any[]' or define a proper interface for the event structure
        this.event = events.map(event => ({
          event_id: event.id || '', // Use a fallback value if 'id' is missing
          title: event.title || '', // FIX: use event.title, not event.name
          date: event.date || '',
          time: event.time || '',
          image: event.image || '',
          description: event.description || '', // Provide default or mapped value
          sports: event.sports || null, // Provide default or mapped value
          ceremonies: event.ceremonies || null, // Provide default or mapped value
          challenger1: event.challenger1 || null, // Provide default or mapped value
          challenger2: event.challenger2 || null, // Provide default or mapped value
          pricec1: event.pricec1 || 0, // Provide default or mapped value
          nbseatssoldc1: event.nbseatssoldc1 || 0, // Provide default or mapped value
          nbseatsavailc1: event.nbseatsavailc1 || 0, // Provide default or mapped value
          pricec2: event.pricec2 || 0, // Provide default or mapped value
          nbseatssoldc2: event.nbseatssoldc2 || 0, // Provide default or mapped value
          nbseatsavailc2: event.nbseatsavailc2 || 0, // Provide default or mapped value
          pricec3: event.pricec2 || 0, // Provide default or mapped value
          nbseatssoldc3: event.nbseatssoldc2 || 0, // Provide default or mapped value
          nbseatsavailc3: event.nbseatsavailc2 || 0, // Provide default or mapped value
          sites: event.sites || null // Provide default or mapped value
        }));
        this.filteredGames = this.event;
        console.log('Fetched games:', this.event);
      },
      error: (err) => {
        console.error('Error fetching games:', err);
      }
    });
  }

  filterGames(): void {
    const term = this.searchTerm.trim().toLowerCase();
    this.filteredGames = this.event.filter(game =>
      game.title.toLowerCase().includes(term) ||
      game.description.toLowerCase().includes(term) ||
      game.date.toLowerCase().includes(term) ||
      game.time.toLowerCase().includes(term) ||
      (game.sports && game.sports?.name && game.sports.name.toLowerCase().includes(term)) ||
      (game.sports && game.sports?.description && game.sports.description.toLowerCase().includes(term)) ||
      (game.sports && game.sports?.date && game.sports.date.toLowerCase().includes(term)) ||
      (game.sports && typeof game.sports.isParalympic !== 'undefined' && 
        (String(game.sports.isParalympic).toLowerCase().includes(term) || 
         (game.sports.isParalympic && 'paralympic'.includes(term)))
      ) ||
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





