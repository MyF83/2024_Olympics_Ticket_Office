import { Component, Inject, ViewChild, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialog } from '@angular/material/dialog';
import { MatStepperModule, MatStepper } from '@angular/material/stepper';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { AuthStateService } from '../../services/auth-state.service';
import { PaymentModalComponent } from '../payment-modal/payment-modal.component';
import { CartsServiceService } from '../../services/cartsservice.service';

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
export class TicketStepperModalComponent implements OnInit {
  isLoggedIn = false;
  selectedOffer: any = null;
  nbSpectators: number = 1;
  priceClass: number = 1;
  showOfferSelection = false;
  showSummary = false;
  isCompletingExistingCart = false; // New flag to track if we're completing vs creating
  existingCartId: number | null = null; // Store cart ID for completion
  @ViewChild(MatStepper) stepper!: MatStepper;
  offerTypes: { type: 'solo' | 'duo' | 'family'; name: string; discountRate: number; nbSpectators: number; color: string; desc: string }[] = [
    { type: 'solo', name: 'Solo Offer', discountRate: 5, nbSpectators: 1, color: 'primary', desc: '-5% per 1 person' },
    { type: 'duo', name: 'Duo Offer', discountRate: 15, nbSpectators: 2, color: 'accent', desc: '-15% per 2 persons' },
    { type: 'family', name: 'Family Offer', discountRate: 35, nbSpectators: 4, color: 'warn', desc: '-35% per 4 persons' }
  ];
  selectedOfferType: string|null = null;

  constructor(
    public dialogRef: MatDialogRef<TicketStepperModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, // data.game is the selected event
    private authStateService: AuthStateService,
    private dialog: MatDialog,
    private cartsService: CartsServiceService
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

  ngOnInit() {
    // Check if user is logged in
    this.isLoggedIn = this.authStateService.checkLoginStatus();
    
    // Check if we're completing an existing cart or creating new
    if (this.data.cartId) {
      this.isCompletingExistingCart = true;
      this.existingCartId = this.data.cartId;
      // Pre-populate with existing cart data if available
      if (this.data.existingOffer) {
        this.selectedOffer = this.data.existingOffer;
      }
      console.log('Modal opened in completion mode for cart:', this.existingCartId);
    } else {
      console.log('Modal opened in creation mode');
    }
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

  proceedToPayment() {
    const cartData = {
      event_id: this.data.game?.event_id,
      offer_id: this.getOfferIdFromType(this.selectedOfferType),
      nb_persons: this.nbSpectators,
      seat_class: this.priceClass,
      offer_name: this.selectedOffer?.name,
      discount_rate: this.selectedOffer?.discountRate,
      amount: this.calculateAmount()
    };

    if (this.isCompletingExistingCart && this.existingCartId) {
      // Update existing cart
      console.log('Completing existing cart:', this.existingCartId);
      this.cartsService.completeCartSelection(this.existingCartId, cartData).subscribe({
        next: (response) => {
          console.log('Cart completed successfully:', response);
          this.openPaymentModal(cartData);
        },
        error: (error) => {
          console.error('Error completing cart:', error);
          alert('Error completing cart selection. Please try again.');
        }
      });
    } else {
      // Create new cart (original behavior)
      console.log('Creating new cart');
      this.cartsService.createCartForUser(cartData).subscribe({
        next: (cartResponse) => {
          console.log('Cart created successfully:', cartResponse);
          this.openPaymentModal(cartData);
        },
        error: (error) => {
          console.error('Error creating cart:', error);
          this.openPaymentModalFallback();
        }
      });
    }
  }

  private openPaymentModal(cartData: any) {
    this.dialogRef.close(); // Close the stepper modal
    
    // Open payment modal with the cart data
    const paymentDialogRef = this.dialog.open(PaymentModalComponent, {
      width: '500px',
      data: {
        cart: cartData
      }
    });
    
    paymentDialogRef.afterClosed().subscribe(result => {
      if (result && result.success) {
        console.log('Payment successful', result);
      }
    });
  }

  private getOfferIdFromType(offerType: string | null): number {
    switch(offerType) {
      case 'solo': return 1;
      case 'duo': return 2; 
      case 'family': return 3;
      default: return 1;
    }
  }

  private calculateAmount(): number {
    // Base price would depend on priceClass and event
    // For now, using a simplified calculation
    const basePrice = this.priceClass === 1 ? 100 : this.priceClass === 2 ? 200 : 300;
    const totalBeforeDiscount = basePrice * this.nbSpectators;
    const discountAmount = totalBeforeDiscount * (this.selectedOffer?.discountRate || 0) / 100;
    return totalBeforeDiscount - discountAmount;
  }

  private openPaymentModalFallback() {
    this.dialogRef.close();
    const paymentDialogRef = this.dialog.open(PaymentModalComponent, {
      width: '500px',
      data: {
        cart: {
          event_id: this.data.game?.event_id,
          offer_id: this.getOfferIdFromType(this.selectedOfferType),
          nb_persons: this.nbSpectators,
          seat_class: this.priceClass,
          offer_name: this.selectedOffer?.name,
          discount_rate: this.selectedOffer?.discountRate,
          amount: this.calculateAmount()
        }
      }
    });
  }
}
