import { Component, Inject, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatStepperModule, MatStepper } from '@angular/material/stepper';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';

@Component({
  standalone: true,
  selector: 'app-ticket-stepper-modal',
  imports: [
    CommonModule,
    MatStepperModule,
    MatButtonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule
  ],
  templateUrl: './ticket-stepper-modal-component.component.html',
  styleUrls: ['./ticket-stepper-modal-component.component.css']
})
export class TicketStepperModalComponent {
  selectedOffer: any = null;
  nbSpectators: number = 1;
  priceClass: number = 1;
  showOfferSelection = false;
  showSummary = false;
  @ViewChild(MatStepper) stepper!: MatStepper;
  offerTypes: { type: 'solo' | 'duo' | 'family'; name: string; discountRate: number; nbSpectators: number; color: string; desc: string }[] = [
    { type: 'solo', name: 'Solo Offer', discountRate: 5, nbSpectators: 1, color: 'primary', desc: '-5% per 1 person' },
    { type: 'duo', name: 'Duo Offer', discountRate: 15, nbSpectators: 2, color: 'accent', desc: '-15% per 2 persons' },
    { type: 'family', name: 'Family Offer', discountRate: 35, nbSpectators: 4, color: 'warn', desc: '-35% per 4 persons' }
  ];
  selectedOfferType: string|null = null;

  constructor(
    public dialogRef: MatDialogRef<TicketStepperModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any // data.game is the selected event
  ) {}

  selectOffer(offer: any) {
    this.selectedOffer = offer;
  }

  goToOffersPage() {
    this.showOfferSelection = true;
  }

  selectOfferType(type: 'solo' | 'duo' | 'family') {
    const found = this.offerTypes.find(o => o.type === type);
    if (found) {
      this.selectedOffer = { name: found.name, discountRate: found.discountRate, nbSpectators: found.nbSpectators };
      this.selectedOfferType = type;
    }
  }

  nextFromStep2() {
    if (this.stepper) {
      this.stepper.next();
    }
  }

  finish() {
    this.showSummary = true;
  }

  closeAndClearCart() {
    // Optionally clear cart data here if needed
    this.dialogRef.close({ action: 'close' });
  }

  goToRegister() {
    this.dialogRef.close({
      action: 'register',
      ticketData: {
        eventId: this.data.game?.event_id,
        eventTitle: this.data.game?.title,
        offerType: this.selectedOfferType,
        offerName: this.selectedOffer?.name,
        offerDiscountRate: this.selectedOffer?.discountRate,
        offerNbSpectators: this.selectedOffer?.nbSpectators,
        nbSpectators: this.nbSpectators,
        priceClass: this.priceClass
      }
    });
  }

  goToLogin() {
    this.dialogRef.close({
      action: 'login',
      ticketData: {
        eventId: this.data.game?.event_id,
        eventTitle: this.data.game?.title,
        offerType: this.selectedOfferType,
        offerName: this.selectedOffer?.name,
        offerDiscountRate: this.selectedOffer?.discountRate,
        offerNbSpectators: this.selectedOffer?.nbSpectators,
        nbSpectators: this.nbSpectators,
        priceClass: this.priceClass
      }
    });
  }
}
