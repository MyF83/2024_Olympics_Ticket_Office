import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Gamesinterface } from '../../../../interfaces/gamesinterface';
import { CommonModule } from '@angular/common';


@Component({
  standalone: true,
  selector: 'app-gamesmodal',
  imports: [CommonModule],
  templateUrl: './gamesmodal.component.html',
  styleUrls: ['./gamesmodal.component.css']
})
export class GamesmodalComponent {
  
  constructor(
    public dialogRef: MatDialogRef<GamesmodalComponent>,
    @Inject(MAT_DIALOG_DATA) public game: Gamesinterface
  ) {console.log('Modal received game:', game);
    console.log('pricec1:', game.pricec1, typeof game.pricec1);
    console.log('pricec2:', game.pricec2, typeof game.pricec2);
    console.log('pricec3:', game.pricec3, typeof game.pricec3);}

  closeModal(): void {
    this.dialogRef.close();
  }

  buyTicket(): void {
    this.dialogRef.close('buy-ticket');
  }
}
