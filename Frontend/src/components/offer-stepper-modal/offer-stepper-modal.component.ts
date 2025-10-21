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
import { CartsServiceService } from '../../services/cartsservice.service';
import { GameserviceService } from '../../services/gameservice.service';
import { HttpClient } from '@angular/common/http';

@Component({
  standalone: true,
  selector: 'app-offer-stepper-modal',
  imports: [
    CommonModule,
    MatStepperModule,
    MatButtonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule
  ],
  templateUrl: './offer-stepper-modal.component.html',
  styleUrls: ['./offer-stepper-modal.component.css']
})
export class OfferStepperModalComponent implements OnInit {
  @ViewChild(MatStepper) stepper!: MatStepper;
  
  // Cart completion data
  cartId: number;
  selectedOffer: any;
  selectedEvent: any = null;
  nbPersons: number = 1;
  seatClass: string = 'price1';
  
  // Available data
  availableEvents: any[] = [];
  showEventSelection = false;
  showConditionsSelection = false;
  showSummary = false;
  
  // Loading states
  isLoading = false;
  
  constructor(
    public dialogRef: MatDialogRef<OfferStepperModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private authStateService: AuthStateService,
    private cartsService: CartsServiceService,
    private gameService: GameserviceService,
    private http: HttpClient
  ) {
    this.cartId = data.cartId;
    this.selectedOffer = data.existingOffer;
  }
  
  ngOnInit() {
    this.loadAvailableEvents();
  }
  
  loadAvailableEvents() {
    this.gameService.getGames().subscribe({
      next: (events: any) => {
        this.availableEvents = events;
        console.log('Loaded events for selection:', events.length);
      },
      error: (error: any) => {
        console.error('Error loading events:', error);
      }
    });
  }
  
  goToEventSelection() {
    this.showEventSelection = true;
    this.stepper.next();
  }
  
  selectEvent(event: any) {
    this.selectedEvent = event;
    console.log('Selected event:', event.title);
  }
  
  goToConditionsSelection() {
    if (!this.selectedEvent) {
      alert('Please select an event first');
      return;
    }
    this.showConditionsSelection = true;
    this.stepper.next();
  }
  
  goToSummary() {
    if (!this.nbPersons || !this.seatClass) {
      alert('Please complete all conditions');
      return;
    }
    this.showSummary = true;
  }
  
  getSeatClassName(seatClass: string): string {
    switch(seatClass) {
      case 'price1': return 'Category 1';
      case 'price2': return 'Category 2';
      case 'price3': return 'Category 3';
      default: return 'Unknown category';
    }
  }
  
  getSeatPrice(): number {
    if (!this.selectedEvent) return 0;
    
    switch(this.seatClass) {
      case 'price1': return this.selectedEvent.pricec1 || 0;
      case 'price2': return this.selectedEvent.pricec2 || 0;
      case 'price3': return this.selectedEvent.pricec3 || 0;
      default: return 0;
    }
  }
  
  calculateTotalAmount(): number {
    const basePrice = this.getSeatPrice() * this.nbPersons;
    const discount = this.selectedOffer?.rate || 0;
    return basePrice * (1 - discount / 100);
  }
  
  validateSelection(): void {
    this.isLoading = true;
    
    console.log('=== VALIDATION DEBUG ===');
    console.log('Cart ID:', this.cartId);
    console.log('UserSelection ID:', this.data.userselectionId);
    console.log('Selected Offer:', this.selectedOffer);
    console.log('Selected Event:', this.selectedEvent);
    console.log('Number of Persons:', this.nbPersons);
    console.log('Seat Class:', this.seatClass);
    console.log('Calculated Amount:', this.calculateTotalAmount());
    
    // Create updated userselection object with proper backend format
    const updatedUserselection = {
      usersel_id: this.data.userselectionId,
      offers: {
        offer_id: this.selectedOffer.offer_id,
        title: this.selectedOffer.title,
        description: this.selectedOffer.description,
        image: this.selectedOffer.image,
        rate: this.selectedOffer.rate,
        nbSpectators: this.selectedOffer.nbSpectators
      },
      events: {
        event_id: this.selectedEvent.event_id,
        title: this.selectedEvent.title,
        date: this.selectedEvent.date,
        time: this.selectedEvent.time,
        description: this.selectedEvent.description,
        pricec1: this.selectedEvent.pricec1,
        pricec2: this.selectedEvent.pricec2,
        pricec3: this.selectedEvent.pricec3
      },
      nbPersons: this.nbPersons,
      seat_class: this.seatClass,
      amount: this.calculateTotalAmount()
    };
    
    console.log('Sending update request with:', updatedUserselection);
    console.log('API URL:', `http://localhost:8082/api/userselection/${this.data.userselectionId}`);
    
    // Call backend to update userselection
    this.http.put(`http://localhost:8080/api/userselection/${this.data.userselectionId}`, updatedUserselection)
      .subscribe({
        next: (response) => {
          console.log('Userselection updated successfully:', response);
          
          // Now update the cart's totalAmount
          const cartUpdateData = {
            cart_id: this.cartId,
            totalAmount: this.calculateTotalAmount(),
            date: new Date().toISOString() // Current timestamp
          };
          
          console.log('Updating cart totalAmount:', cartUpdateData);
          
          this.http.put(`http://localhost:8080/api/cart/${this.cartId}`, cartUpdateData)
            .subscribe({
              next: (cartResponse) => {
                console.log('Cart totalAmount updated successfully:', cartResponse);
                this.isLoading = false;
                this.dialogRef.close('completed');
              },
              error: (cartError: any) => {
                console.error('Error updating cart totalAmount:', cartError);
                // Still close as successful since userselection was updated
                this.isLoading = false;
                this.dialogRef.close('completed');
              }
            });
        },
        error: (error: any) => {
          console.error('=== VALIDATION ERROR ===');
          console.error('Full error object:', error);
          console.error('Error status:', error.status);
          console.error('Error message:', error.message);
          console.error('Error details:', error.error);
          this.isLoading = false;
          alert(`Error completing selection: ${error.status} - ${error.error?.message || error.message}. Please try again.`);
        }
      });
  }
  
  closeModal(): void {
    this.dialogRef.close('cancelled');
  }
}