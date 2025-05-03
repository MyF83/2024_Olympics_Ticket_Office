import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Gamesinterface } from '../../../../interfaces/gamesinterface';


@Component({
  selector: 'app-gamesmodal',
  templateUrl: './gamesmodal.component.html',
  styleUrls: ['./gamesmodal.component.css']
})
export class GamesmodalComponent {
  
  constructor(
    public dialogRef: MatDialogRef<GamesmodalComponent>,
    @Inject(MAT_DIALOG_DATA) public game: Gamesinterface
  ) {}

  closeModal(): void {
    this.dialogRef.close();
  }

  buyTicket(): void {
    this.dialogRef.close('buy-ticket');
  }
}
