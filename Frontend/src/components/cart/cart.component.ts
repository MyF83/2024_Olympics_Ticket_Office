import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CartsServiceService } from '../../services/cartsservice.service';
import { CartsInterface } from '../../interfaces/cartsinterface';
import { AuthStateService } from '../../services/auth-state.service';
import { Router } from '@angular/router';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { PaymentModalComponent } from '../payment-modal/payment-modal.component';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule, MatDialogModule],
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  userCarts: any[] = []; // Changed to any[] to handle enriched cart data from backend
  isLoading = true;
  errorMessage = '';

  constructor(
    private cartsService: CartsServiceService,
    private authStateService: AuthStateService,
    private router: Router,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.loadCarts();
  }

  loadCarts(): void {
    // Check if user is logged in
    if (this.authStateService.checkLoginStatus()) {
      this.isLoading = true;
      this.cartsService.getUserCarts().subscribe({
        next: (carts) => {
          this.userCarts = carts;
          this.isLoading = false;
        },
        error: (error) => {
          console.error('Error loading carts', error);
          this.errorMessage = 'Failed to load your carts. Please try again.';
          this.isLoading = false;
        }
      });
    } else {
      // Redirect to login if not authenticated
      this.router.navigate(['/account']);
    }
  }

  proceedToCheckout(cart: any): void {
    // Open payment modal for this cart (cart now contains enriched data from backend)
    const dialogRef = this.dialog.open(PaymentModalComponent, {
      width: '500px',
      data: { cart }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result && result.success) {
        // Payment was successful, refresh carts
        this.loadCarts();
      }
    });
  }

  removeFromCart(cartId: number): void {
    // Implement remove functionality
    if (confirm('Are you sure you want to remove this item from your cart?')) {
      this.cartsService.deleteCart(cartId).subscribe({
        next: () => {
          console.log('Successfully removed item from cart');
          // Refresh the cart list after removal
          this.loadCarts();
        },
        error: (error) => {
          console.error('Error removing item from cart:', error);
          this.errorMessage = 'Failed to remove item from cart. Please try again.';
        }
      });
    }
  }
}
