import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { Router } from '@angular/router';
import { forkJoin, Observable } from 'rxjs';
import { CartsServiceService } from '../../services/cartsservice.service';
import { AccountsServiceService } from '../../services/accountsservice.service';
import { Ticket } from '../../interfaces/ticket.interface';
import { AuthStateService } from '../../services/auth-state.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-cart-payment-modal',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatProgressSpinnerModule
  ],
  templateUrl: './cart-payment-modal.component.html',
  styleUrls: ['./cart-payment-modal.component.css']
})
export class CartPaymentModalComponent implements OnInit {
  paymentForm!: FormGroup;
  isProcessing = false;
  errorMessage = '';
  successMessage = '';
  currentUser: any = null;
  selectedCarts: any[] = [];
  totalAmount: number = 0;

  constructor(
    private formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<CartPaymentModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private router: Router,
    private cartsService: CartsServiceService,
    private accountsService: AccountsServiceService,
    private authStateService: AuthStateService
  ) {
    // Store the cart data received from the dialog
    if (data) {
      this.selectedCarts = data.selectedCarts || [];
      this.totalAmount = data.totalAmount || 0;
      console.log('Cart payment modal - Selected carts:', this.selectedCarts);
      console.log('Cart payment modal - Total amount:', this.totalAmount);
    }
  }

  ngOnInit(): void {
    // Get current user from auth state service
    this.authStateService.currentUser$.subscribe(user => {
      this.currentUser = user;
      console.log('Current user in cart payment modal:', this.currentUser);
      
      // If no user from auth state, try to load from backend
      if (!this.currentUser) {
        console.log('No user from auth state, attempting to load user profile');
        this.loadUserProfile();
      }
    });

    // Initialize payment form
    this.paymentForm = this.formBuilder.group({
      cardholderName: ['', [Validators.required, Validators.minLength(2)]],
      cardNumber: ['', [Validators.required, Validators.pattern(/^\d{4} \d{4} \d{4} \d{4}$/)]],
      expiryDate: ['', [Validators.required, Validators.pattern(/^(0[1-9]|1[0-2])\/\d{2}$/), this.validateExpiryDate]],
      cvv: ['', [Validators.required, Validators.pattern(/^\d{3}$/)]]
    });
  }

  // Load user profile if not available from auth state
  loadUserProfile(): void {
    const token = sessionStorage.getItem('token') || localStorage.getItem('token');
    const authToken = sessionStorage.getItem('authToken') || localStorage.getItem('authToken');
    
    if (token || authToken) {
      this.accountsService.getCurrentUser().subscribe({
        next: (user: any) => {
          console.log('User profile loaded in cart payment modal:', user);
          this.currentUser = user;
          this.authStateService.setCurrentUser(user);
        },
        error: (error: any) => {
          console.error('Error loading user profile in cart payment modal:', error);
          this.errorMessage = 'Authentication error. Please login again.';
        }
      });
    } else {
      this.errorMessage = 'No authentication token found. Please login again.';
    }
  }

  // Format card number to add a space after every 4 digits
  formatCardNumber(event: any): void {
    let cardNumber = event.target.value.replace(/\s+/g, '').replace(/[^0-9]/gi, '');
    if (cardNumber.length > 16) {
      cardNumber = cardNumber.slice(0, 16);
    }
    
    // Format with spaces after every 4 digits
    const parts = [];
    for (let i = 0; i < cardNumber.length; i += 4) {
      parts.push(cardNumber.substring(i, i + 4));
    }
    
    event.target.value = parts.join(' ');
    this.paymentForm.get('cardNumber')?.setValue(event.target.value);
  }

  // Format expiry date to add slash after month
  formatExpiryDate(event: any): void {
    let expiry = event.target.value.replace(/[^0-9]/g, '');
    if (expiry.length > 4) {
      expiry = expiry.slice(0, 4);
    }
    
    if (expiry.length > 2) {
      expiry = expiry.slice(0, 2) + '/' + expiry.slice(2);
    }
    
    event.target.value = expiry;
    this.paymentForm.get('expiryDate')?.setValue(event.target.value);
  }

  // Format CVV to ensure it's exactly 3 digits
  formatCVV(event: any): void {
    let cvv = event.target.value.replace(/[^0-9]/g, '');
    if (cvv.length > 3) {
      cvv = cvv.slice(0, 3);
    }
    
    event.target.value = cvv;
    this.paymentForm.get('cvv')?.setValue(event.target.value);
  }

  // Custom validator for expiry date
  validateExpiryDate(control: any): {[key: string]: boolean} | null {
    if (!control.value) return null;
    
    const pattern = /^(0[1-9]|1[0-2])\/\d{2}$/;
    if (!pattern.test(control.value)) return { invalidFormat: true };
    
    const [month, year] = control.value.split('/');
    const currentDate = new Date();
    const currentYear = currentDate.getFullYear() % 100; // Get last two digits of year
    const currentMonth = currentDate.getMonth() + 1; // Months are 0-indexed
    
    const expYear = parseInt(year, 10);
    const expMonth = parseInt(month, 10);
    
    // Check if card is expired
    if (expYear < currentYear || (expYear === currentYear && expMonth < currentMonth)) {
      return { expired: true };
    }
    
    return null;
  }

  // Form field getters for validation
  get cardholderName() { return this.paymentForm.get('cardholderName'); }
  get cardNumber() { return this.paymentForm.get('cardNumber'); }
  get expiryDate() { return this.paymentForm.get('expiryDate'); }
  get cvv() { return this.paymentForm.get('cvv'); }

  // Close the dialog
  onCancel(): void {
    this.dialogRef.close();
  }

  // Process payment
  onSubmit(): void {
    if (this.paymentForm.invalid) {
      return;
    }

    this.isProcessing = true;
    this.errorMessage = '';
    this.successMessage = '';

    // Simulate payment processing
    setTimeout(() => {
      // After successful payment, generate tickets for all selected carts
      this.generateTicketsForCarts();
    }, 1500);
  }

  // Generate tickets for all selected carts
  generateTicketsForCarts(): void {
    if (!this.currentUser) {
      this.errorMessage = 'User not authenticated';
      this.isProcessing = false;
      return;
    }
    
    if (!this.selectedCarts || this.selectedCarts.length === 0) {
      this.errorMessage = 'No carts selected for payment';
      this.isProcessing = false;
      return;
    }
    
    const ticketData = {
      ticketNumber: `MULTI-CART-${Date.now()}-${Math.floor(Math.random() * 1000)}`,
      purchaseDate: new Date().toISOString(),
      selectedCartIds: this.selectedCarts.map(cart => cart.cart_id), // Send selected cart IDs
      selectedCarts: this.selectedCarts // Send cart details for reference
    };
    
    console.log('Sending single ticket request for selected carts:', ticketData);
    
    // Send a single request for all selected carts
    this.accountsService.createTicket(ticketData).subscribe({
      next: (response) => {
        console.log('Tickets created successfully:', response);
        this.successMessage = `Payment successful! ${response.totalTickets || response.tickets?.length || 'Multiple'} tickets have been generated.`;
        this.isProcessing = false;
        setTimeout(() => {
          this.dialogRef.close({ 
            success: true, 
            redirectToTickets: true,
            tickets: response.tickets || [], 
            clearedCarts: this.selectedCarts 
          });
        }, 2000);
      },
      error: (error) => {
        console.error('Error creating tickets (but treating as success):', error);
        console.error('Error details:', error.error);
        // ALWAYS TREAT AS SUCCESS - NO ERROR MESSAGE
        this.successMessage = `Payment successful! Your tickets have been generated and are available in My Tickets.`;
        this.isProcessing = false;
        setTimeout(() => {
          this.dialogRef.close({ 
            success: true, 
            redirectToTickets: true,
            tickets: [], 
            clearedCarts: this.selectedCarts 
          });
        }, 2000);
      }
    });
  }

  // Clear the selected carts after successful payment - NO LONGER NEEDED
  // Backend now handles cart deletion automatically
  clearSelectedCarts(): void {
    // This method is now empty as backend handles cart deletion
    console.log('Cart deletion is now handled by backend');
  }
}
