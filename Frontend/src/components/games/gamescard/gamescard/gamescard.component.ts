import { Component, Input, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Gamesinterface } from '../../../../interfaces/gamesinterface';
import { GameserviceService } from '../../../../services/gameservice.service';


@Component({
  selector: 'app-gamescard',
  imports: [CommonModule],
  templateUrl: './gamescard.component.html',
  styleUrls: ['./gamescard.component.css']
})
export class GamesCardComponent {
  @Input() gamesInterface: Gamesinterface[] = [];

  // Example usage of signal
  gamesSignal = signal([


    { id: 1, name: 'Game 1', description: 'Description 1', imageUrl: '../../../../../../public/jpg/Table-tennis-sml.jpg' },
    { id: 2, name: 'Game 2', description: 'Description 2', imageUrl: '../../../../../../public/jpg/Rowing-sml.jpg' },
    { id: 3, name: 'Game 3', description: 'Description 3', imageUrl: '../../../../../../public/jpg/Golf-sml.jpg'}
  ]);
}

