import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'; // Import CommonModule
import { OfferserviceService } from '../../services/offerservice.service';
import { Offersinterface } from '../../interfaces/offersinterface';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CartsServiceService } from '../../services/cartsservice.service';
import { HttpClient } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';
import { LoginRegisterModalComponent } from '../login-register-modal/login-register-modal.component';
import { AuthStateService } from '../../services/auth-state.service';

@Component({
  selector: 'app-offers',
  imports: [CommonModule, FormsModule], // Add CommonModule here
  standalone: true,
  templateUrl: './offers.component.html',
  styleUrls: ['./offers.component.css']
})
export class OffersComponent {


  offers: Offersinterface[] = [];

  constructor(
    private offerService: OfferserviceService,
    private dialog: MatDialog,
    private authStateService: AuthStateService,
    private cartsService: CartsServiceService,
    private router: Router,
    private http: HttpClient) 
    { } // Inject the services

    ngOnInit() {
      this.offerService.getOffers().subscribe(data => {
        this.offers = data; // Assigning data after subscribing to the observable
      });
    }

    /*
    getImageForOffer(title: string): string {
      if (title.toLowerCase().includes('solo')) return 'png/Solo.png';
      if (title.toLowerCase().includes('duo')) return 'png/Duo.png';
      if (title.toLowerCase().includes('family')) return 'png/Family.png';
      return 'png/empty.png'; 
    }*/

    addToCart(offer: Offersinterface) {
      // Check if user is authenticated - check both sessionStorage and localStorage
      const token = sessionStorage.getItem('token') || localStorage.getItem('token');
      const authToken = sessionStorage.getItem('authToken') || localStorage.getItem('authToken');
      
      console.log('Checking authentication for Add to Cart:', { 
        token: !!token, 
        authToken: !!authToken,
        tokenType: token ? 'token' : (authToken ? 'authToken' : 'none')
      });
      
      // Use whichever token is available
      const finalToken = token || authToken;
      
      if (!finalToken) {
        // Store the offer for after authentication
        localStorage.setItem('pending_offer', JSON.stringify(offer));
        alert('You need to be logged in to add items to cart. Please login/register.');
        this.router.navigate(['/account'], { queryParams: { view: 'carts' } });
        return;
      }

      // Create cart data with only offer information (event will be selected later)
      const cartData = {
        offer_id: offer.offer_id,
        event_id: null, // Will be selected in games-stepper-modal
        nb_persons: 1, // Default, will be updated in stepper
        seat_class: 'price1', // Default, will be updated in stepper
        amount: 0 // Will be calculated after event selection
      };

      console.log('Creating cart with data:', cartData);
      console.log('Offer object received:', offer);
      console.log('Offer ID type:', typeof offer.offer_id, 'Value:', offer.offer_id);
      console.log('Using token:', finalToken ? 'Present' : 'Missing');

      // Create cart entry
      this.cartsService.createCartForUser(cartData).subscribe({
        next: (response: any) => {
          console.log('Cart created successfully:', response);
          if (response.message && response.message.includes('already exists')) {
            // User already has this offer - show a friendly message
            alert(`Great! You already have the '${offer.title}' offer. Let's go to your cart to manage it.`);
            // Redirect to Account/My Carts page to show existing offer
            this.router.navigate(['/account'], { queryParams: { view: 'carts' } });
          } else {
            // New offer added successfully
            alert(`Offer '${offer.title}' added to cart! Redirecting to complete your selection.`);
            // Redirect to Account/My Carts page
            this.router.navigate(['/account'], { queryParams: { view: 'carts' } });
          }
        },
        error: (error) => {
          console.error('Error creating cart:', error);
          console.error('Full error object:', JSON.stringify(error, null, 2));
          
          // Handle 409 Conflict as a special case (cart already exists)
          if (error.status === 409) {
            console.log('Cart already exists - treating as success');
            alert(`Offer '${offer.title}' is already in your cart! Redirecting to My Carts.`);
            this.router.navigate(['/account'], { queryParams: { view: 'carts' } });
            return;
          }
          
          console.error('Error details:', {
            status: error.status,
            statusText: error.statusText,
            message: error.message,
            error: error.error,
            url: error.url,
            headers: error.headers
          });
          
          let errorMessage = 'Unknown error';
          
          // Handle different types of error responses
          if (error.error) {
            if (typeof error.error === 'string') {
              // Backend returned plain text error
              errorMessage = error.error;
            } else if (error.error.error) {
              // Backend returned JSON with nested error
              errorMessage = error.error.error;
            } else if (error.error.message) {
              // Backend returned JSON with message
              errorMessage = error.error.message;
            }
          } else if (error.message) {
            errorMessage = error.message;
          }
          
          // Add status code information if available
          if (error.status) {
            errorMessage += ` (Status: ${error.status})`;
          }
          
          console.error('Final error message:', errorMessage);
          alert(`Error adding offer to cart: ${errorMessage}. Please try again.`);
        }
      });
    }

}
