import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Gamesinterface } from '../../../../interfaces/gamesinterface';
import { CommonModule } from '@angular/common';
import { MatDialogModule, MatDialog } from '@angular/material/dialog';
import { TicketStepperModalComponent } from '../../../ticket-stepper-modal-component/ticket-stepper-modal-component.component';
import { Router } from '@angular/router';

@Component({
  standalone: true,
  selector: 'app-gamesmodal',
  imports: [CommonModule, MatDialogModule],
  templateUrl: './gamesmodal.component.html',
  styleUrls: ['./gamesmodal.component.css']
})
export class GamesmodalComponent {
  
  constructor(
    public dialogRef: MatDialogRef<GamesmodalComponent>,
    @Inject(MAT_DIALOG_DATA) public game: Gamesinterface,
    private dialog: MatDialog,
    private router: Router
  ) {
    console.log('Modal received game:', game);
    console.log('pricec1:', game.pricec1, typeof game.pricec1);
    console.log('pricec2:', game.pricec2, typeof game.pricec2);
    console.log('pricec3:', game.pricec3, typeof game.pricec3);
  }

  closeModal(): void {
    this.dialogRef.close();
  }

  buyTicket(): void {
    this.dialogRef.close();
    const stepperDialogRef = this.dialog.open(TicketStepperModalComponent, {
      width: '600px',
      data: { game: this.game }
    });
    stepperDialogRef.afterClosed().subscribe(result => {
      if (result && (result.action === 'login' || result.action === 'register')) {
        if (result.ticketData) {
          localStorage.setItem('pending_cart', JSON.stringify(result.ticketData));
        }
        this.router.navigate(['/account'], { queryParams: { view: result.action } });
      }
      // If action is 'close' or undefined, do nothing
    });
  }
}
