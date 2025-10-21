import { Component, OnInit, Inject, PLATFORM_ID } from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { FormsModule } from '@angular/forms';
import  { AccountsInterface } from '../../interfaces/accountsinterface';
import { AccountsServiceService } from '../../services/accountsservice.service'; // Import the service
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';
import { SecuritypolicymodalComponent } from '../securitypolicymodal/securitypolicymodal.component';
import { SecuritypolicyService } from '../../services/securitypolicy.service';
import { Securitypolicy } from '../../interfaces/securitypolicy';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { Location } from '@angular/common';
import {MatIconModule} from '@angular/material/icon';
import { ChangeDetectorRef } from '@angular/core';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser'; 
import { CartsServiceService } from '../../services/cartsservice.service'; // Import the CartService
import { CartsInterface } from '../../interfaces/cartsinterface';
import { MatTableModule } from '@angular/material/table';
import { CdkTableDataSourceInput } from '@angular/cdk/table';
import { ActivatedRoute } from '@angular/router';
import { OfferStepperModalComponent } from '../offer-stepper-modal/offer-stepper-modal.component';
import { CartPaymentModalComponent } from '../cart-payment-modal/cart-payment-modal.component';

@Component({
  selector: 'app-account',
  imports: [
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatOptionModule,
    MatCheckboxModule, // Added MatCheckboxModule for the checkbox
    MatIconModule,
    MatTableModule
  ],
  standalone: true,
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AccountComponent implements OnInit {
  isConnected: boolean = false;
  hidePassword: boolean = true;
  role_id: number | null = null;
  htmlContent: SafeHtml | null = null;

  nbPersons: number = 1;
selectedPriceClass: number = 1; // 1, 2, or 3
  
  previousView: 'main' | 'register' | 'login' | 'create' | 'editInfo' | 'payments' | 'tickets' | 'login' | 'connected' |  'manageEvents' | 'manageOffers' | 'offersSold' | 'carts' = 'main';
  currentView: 'main' | 'register' | 'create' | 'editInfo' | 'payments' | 'tickets' | 'login' | 'connected' |  'manageEvents' | 'manageOffers' | 'offersSold' | 'carts' = 'main';

  // Boolean for the policy acceptation term checkbox:
  hasAcceptedTerms: boolean = false;
 
  // User ID for the updating user information:
  user_id: number = 0;
  // Generated with backend method and necessary for the login:
  username: string = '';
  
  // Required fiels
  firstname: string = '';
  lastname: string = '';
  password: string = '';
  email: string = '';
  policies: number = 1; 
  
  // Optional fields, that user can fill in his profile :
  phoneNumber: string = '';
  address: string = '';
  account: AccountsInterface[] = [];
  city: string = '';
  postalCode: string = '';
  country: string = '';
  countries: { code: string, name: string }[] = [];
  policy: { policy_id: number; title: string; description: string; url: string; creationDate: string; version: number; isActive: boolean; };
  userCarts: CartsInterface[] = [];
  selectedCartsForPayment: Map<string, boolean> = new Map(); // Track selected carts
  globalPaymentAmount: number = 0; // Total amount for global payment
  
  // Mobile responsive properties
  isMobileMenuOpen: boolean = false;
  isMobile: boolean = false;
  
  // Sales tracking properties for admin
  totalSales: number = 0;
  salesList: any[] = [];
  salesLoading: boolean = false;

  private redirectToCartsAfterAuth = false;

  constructor(
    private accountService: AccountsServiceService,
    private http: HttpClient,
    private sanitizer: DomSanitizer, // <-- Inject DomSanitizer
    private dialog: MatDialog,
    private securitypolicyService: SecuritypolicyService, // inject the service
    private location: Location, // Inject Location service for navigation
    private cdr: ChangeDetectorRef,
    private cartsService: CartsServiceService,
    @Inject(PLATFORM_ID) private platformId: Object,
    private route: ActivatedRoute
  ) {
    // Initialize policy with default values
    this.policy = {
      policy_id: 0,
      title: 'Default Title',
      description: 'Default Description',
      url: 'https://example.com',
      creationDate: 'N/A',
      version: 1,
      isActive: false
    };
    if (this.policy.url) {
      this.http.get(this.policy.url, { responseType: 'text' }).subscribe(html => {
        this.htmlContent = this.sanitizer.bypassSecurityTrustHtml(html);
      });
    }
    console.log('Initialized default policy:', this.policy);
  }

  ngOnInit(): void {
    // Check for mobile on init and add resize listener
    this.checkMobile();
    if (isPlatformBrowser(this.platformId)) {
      window.addEventListener('resize', () => this.checkMobile());
    }
    
    this.accountService.getAccounts().subscribe((accounts: AccountsInterface[]) => {
      this.account = accounts;
      this.securitypolicyService.getPolicies().subscribe(policies => {
      
//this.cartsService.getUserCarts().subscribe((carts: CartsInterface[]) => this.userCarts = carts);
const activePolicy = policies.find(p => p.isActive === true);
if (activePolicy) {
  this.policies = activePolicy.policy_id;
}
  });
      console.log('Fetching Account details:', this.account);
    });
    this.fetchCountries();

  // Persistence of the connection state:
  // Check if the user is already connected by looking for a token in sessionStorage or localStorage 
  if (isPlatformBrowser(this.platformId)) {
    const token = sessionStorage.getItem('token') || localStorage.getItem('token');
    if (token) {
      this.isConnected = true;
      // Load user profile when token is found
      this.loadUserProfile();
    }
  }

  this.route.queryParams.subscribe(params => {
    if (params['view'] === 'login') {
      this.currentView = 'login';
    } else if (params['view'] === 'register') {
      this.currentView = 'register';
    } else if (params['view'] === 'carts') {
      this.currentView = 'carts';
      this.onViewCarts(); // Load carts data
    }
    if (params['view'] === 'login' || params['view'] === 'register') {
      this.currentView = params['view'];
      this.redirectToCartsAfterAuth = true;
    }
  });
  }

  loadUserProfile(): void {
    this.accountService.getCurrentUser().subscribe({
      next: (userProfile) => {
        console.log('Loaded user profile:', userProfile);
        this.user_id = userProfile.user_id;
        this.firstname = userProfile.firstname || '';
        this.lastname = userProfile.lastname || '';
        this.username = userProfile.username || '';
        this.email = userProfile.email || '';
        this.phoneNumber = userProfile.phoneNumber || '';
        this.address = userProfile.address || '';
        this.city = userProfile.city || '';
        this.postalCode = userProfile.postalCode || '';
        this.role_id = userProfile.role_id || null;
        
        // Debug logging for role detection
        console.log('User role_id set to:', this.role_id);
        console.log('Admin panel should be visible:', this.role_id === 1 || this.role_id === 2);
        
        // Force change detection to update the UI
        this.cdr.detectChanges();
      },
      error: (error) => {
        console.error('Failed to load user profile:', error);
        // If profile loading fails, user might not be authenticated
        this.isConnected = false;
        sessionStorage.removeItem('token');
        localStorage.removeItem('token');
      }
    });
  }
  
  // Mobile responsive methods
  checkMobile(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.isMobile = window.innerWidth <= 768;
      if (!this.isMobile) {
        this.isMobileMenuOpen = false; // Close mobile menu on desktop
      }
    }
  }
  
  toggleMobileMenu(): void {
    this.isMobileMenuOpen = !this.isMobileMenuOpen;
  }
  
  closeMobileMenu(): void {
    this.isMobileMenuOpen = false;
  }
  
  // Additional methods for ticket functionality
  onQRCodeError(event: any): void {
    console.error('QR Code image failed to load:', event);
    // Could set a fallback or show error message
  }
  
  downloadTicket(ticket: any): void {
    console.log('Download ticket:', ticket);
    // Implement ticket download logic
  }
  
  viewTicketDetails(ticket: any): void {
    console.log('View ticket details:', ticket);
    // Implement ticket details view
  }

  fetchCountries(): void {
    this.http.get<{ country_id: number, name: string }[]>('http://localhost:8080/api/country').subscribe(
      (data) => {
        console.log('Fetched countries:', data); // Debugging log
        this.countries = data
          .map((country) => ({ code: country.country_id.toString(), name: country.name }))
          .sort((a, b) => a.name.localeCompare(b.name)); // Sort countries alphabetically by name
      },
      (error) => {
        console.error('Error fetching countries:', error);
      }
    );
  }

  onRegister() {
    console.log('Register fields:', this.firstname, this.lastname, this.email, this.password);
    if (this.firstname && this.lastname && this.email && this.password && this.hasAcceptedTerms) {
        const registerData = {
            firstname: this.firstname,
            lastname: this.lastname,
            email: this.email,
            password: this.password,
            policyId: this.policies // Only send policyId, do not send roles
        };
        console.log('Sending account data to backend:', registerData); // Debugging log
        this.accountService.registerUser(registerData).subscribe({
            next: (response: any) => {
                this.isConnected = true;
                this.previousView = this.currentView;
                this.currentView = 'main';
                alert('Account created successfully! Your username is: ' + response.username);
                console.log('User registered:', response);
                // Check for pending cart in localStorage
                const pendingCart = localStorage.getItem('pending_cart');
                if (pendingCart) {
                  const ticketData = JSON.parse(pendingCart);
                  this.cartsService.createCartForUser(ticketData).subscribe(
                    (res: any) => {
                      console.log('Cart creation response:', res);
                      localStorage.removeItem('pending_cart');
                      this.cartsService.getUserCarts().subscribe((carts: CartsInterface[]) => {
                        console.log('Fetched carts after cart creation:', carts);
                        this.userCarts = carts;
                        this.currentView = 'carts';
                        this.redirectToCartsAfterAuth = false;
                      });
                    },
                    (err: any) => {
                      console.error('Cart creation error:', err);
                      alert('Failed to create cart.');
                    }
                  );
                } else if (this.redirectToCartsAfterAuth) {
                  this.cartsService.getUserCarts().subscribe((carts: CartsInterface[]) => {
                    this.userCarts = carts;
                    this.currentView = 'carts';
                    this.redirectToCartsAfterAuth = false;
                  });
                }
            },
            error: (err: any) => {
                alert('Registration failed. Please try again.');
                console.error('Registration error:', err);
            }
        });
    } else {
        console.warn('All fields are required for registration and terms must be accepted.');
    }
  }

  onCreateAccount(): void {
    const accountData = {
        firstname: this.firstname,
        lastname: this.lastname,
        email: this.email,
        password: this.password,
        policyId: this.policies
    };

    console.log('Sending account data to backend:', accountData); // Debugging log

    this.accountService.registerUser(accountData).subscribe({
        next: (response) => {
            console.log('Account created successfully:', response); // Debugging log
            alert('Account created successfully! Your username is: ' + response.username);
            this.previousView = this.currentView;
            this.currentView = 'main';
        },
        error: (err) => {
            alert('Error creating account. Please try again.');
            console.error('Account creation error:', err);
        }
    });
  }

  onLogin() {
    console.log('Login fields:', this.username, this.password);
    this.previousView = this.currentView;
    this.currentView = 'main';
    if (this.username && this.password) {
      const loginData = {
        username: this.username,
        password: this.password
      };
      this.accountService.login(this.username, this.password).subscribe({
        next: (response: any) => {
          console.log('Login response:', response); // Debug login response
          // Save the JWT token to sessionStorage if present :
          if (response.token) {
            sessionStorage.setItem('token', response.token);
          }
          
          // Extract user information from response
          this.user_id = response.user_id;
          this.firstname = response.firstname || '';
          this.lastname = response.lastname || '';
          this.email = response.email || '';
          
          // Handle roles as array or object
          if (Array.isArray(response.roles)) {
            // If roles is an array, find the highest privilege role
            this.role_id = response.roles.length > 0 ? response.roles[0].role_id : null;
          } else if (response.roles && response.roles.role_id) {
            this.role_id = response.roles.role_id;
          } else {
            this.role_id = response.role_id || null;
          }
          
          // Debug logging for role detection during login
          console.log('Login - role_id set to:', this.role_id);
          console.log('Login - Admin panel should be visible:', this.role_id === 1 || this.role_id === 2);
          
          this.isConnected = true;
          
          // Load complete user profile including role_id after login
          this.loadUserProfile();
          
          this.cdr.detectChanges(); // <-- Force view update
          alert('Login successful! Welcome back, ' + this.firstname + ' ' + this.lastname + '!');
          console.log('User logged in:', response);
          // Check for pending cart/offer in localStorage
          const pendingCart = localStorage.getItem('pending_cart');
          const pendingOffer = localStorage.getItem('pending_offer');
          
          if (pendingCart && pendingCart !== 'null' && pendingCart.trim() !== '') {
            console.log('Found pending cart:', pendingCart);
            try {
              const ticketData = JSON.parse(pendingCart);
              console.log('Parsed pending cart data:', ticketData);
              this.cartsService.createCartForUser(ticketData).subscribe(
                (res: any) => {
                  console.log('Cart creation response:', res);
                  localStorage.removeItem('pending_cart');
                  this.cartsService.getUserCarts().subscribe((carts: CartsInterface[]) => {
                    console.log('Fetched carts after cart creation:', carts);
                    this.userCarts = carts;
                    this.currentView = 'carts';
                    this.redirectToCartsAfterAuth = false;
                  });
                },
                (err: any) => {
                  console.error('Cart creation error:', err);
                  console.error('Error details:', {
                    status: err.status,
                    message: err.message,
                    error: err.error,
                    url: err.url
                  });
                  
                  let errorMessage = 'Unknown error';
                  if (err.error) {
                    if (typeof err.error === 'string') {
                      errorMessage = err.error;
                    } else if (err.error.error) {
                      errorMessage = err.error.error;
                    } else if (err.error.message) {
                      errorMessage = err.error.message;
                    }
                  } else if (err.message) {
                    errorMessage = err.message;
                  }
                  
                  alert(`Failed to create cart: ${errorMessage}. The pending cart has been removed.`);
                  localStorage.removeItem('pending_cart'); // Remove invalid pending cart
                }
              );
            } catch (parseError) {
              console.error('Error parsing pending cart data:', parseError);
              localStorage.removeItem('pending_cart'); // Remove invalid data
              alert('Invalid pending cart data removed.');
            }
          } else if (pendingOffer && pendingOffer !== 'null' && pendingOffer.trim() !== '') {
            console.log('Found pending offer:', pendingOffer);
            try {
              const offer = JSON.parse(pendingOffer);
              console.log('Parsed pending offer data:', offer);
              console.log('Original pending offer string:', pendingOffer);
              
              // Validate offer data structure
              if (!offer.offer_id) {
                console.error('Invalid offer data - missing offer_id:', offer);
                localStorage.removeItem('pending_offer');
                alert('Invalid offer data found. Please try adding the offer to cart again.');
                return;
              }
              
              // Convert offer to cart format
              const cartData = {
                offer_id: offer.offer_id,
                event_id: null,
                nb_persons: 1,
                seat_class: 'price1',
                amount: 0
              };
              
              console.log('Creating cart from pending offer with data:', cartData);
              console.log('Cart data offer_id type:', typeof cartData.offer_id, 'value:', cartData.offer_id);
              
              this.cartsService.createCartForUser(cartData).subscribe(
                (res: any) => {
                  console.log('Cart creation from offer response:', res);
                  localStorage.removeItem('pending_offer');
                  
                  if (res.message && res.message.includes('already exists')) {
                    alert(`Great! You already have the '${offer.title}' offer. You can manage it in My Carts below.`);
                  } else {
                    alert(`Offer '${offer.title}' added to cart! Complete your selection in My Carts.`);
                  }
                  
                  this.cartsService.getUserCarts().subscribe((carts: CartsInterface[]) => {
                    console.log('Fetched carts after offer cart creation:', carts);
                    this.userCarts = carts;
                    this.currentView = 'carts';
                    this.redirectToCartsAfterAuth = false;
                  });
                },
                (err: any) => {
                  console.error('Offer cart creation error:', err);
                  
                  // Handle 409 Conflict as a special case (cart already exists)
                  if (err.status === 409) {
                    console.log('Cart already exists for pending offer - treating as success');
                    localStorage.removeItem('pending_offer');
                    alert(`Great! You already have the '${offer.title}' offer. You can manage it in My Carts below.`);
                    this.cartsService.getUserCarts().subscribe((carts: CartsInterface[]) => {
                      console.log('Fetched carts after handling existing cart:', carts);
                      this.userCarts = carts;
                      this.currentView = 'carts';
                      this.redirectToCartsAfterAuth = false;
                    });
                    return;
                  }
                  
                  console.error('Error details:', {
                    status: err.status,
                    message: err.message,
                    error: err.error,
                    url: err.url
                  });
                  
                  let errorMessage = 'Unknown error';
                  if (err.error) {
                    if (typeof err.error === 'string') {
                      errorMessage = err.error;
                    } else if (err.error.error) {
                      errorMessage = err.error.error;
                    } else if (err.error.message) {
                      errorMessage = err.error.message;
                    }
                  } else if (err.message) {
                    errorMessage = err.message;
                  }
                  
                  alert(`Failed to create cart from offer: ${errorMessage}. The pending offer has been removed.`);
                  localStorage.removeItem('pending_offer'); // Remove invalid pending offer
                }
              );
            } catch (parseError) {
              console.error('Error parsing pending offer data:', parseError);
              localStorage.removeItem('pending_offer'); // Remove invalid data
              alert('Invalid pending offer data removed.');
            }
          } else {
            this.cartsService.getUserCarts().subscribe((carts: CartsInterface[]) => {
              console.log('Fetched carts:', carts); // Debug cart fetch
              this.userCarts = carts;
              if (this.redirectToCartsAfterAuth) {
                this.currentView = 'carts';
                this.redirectToCartsAfterAuth = false;
              }
            });
          }
        },
        error: (err: any) => {
          alert('Login failed. Please check your credentials.');
          console.error('Login error:', err);
        }
      });
    }
  }


  onLogout() {
    sessionStorage.removeItem('token');
    // localStorage.removeItem('token');
    this.isConnected = false;
    this.currentView = 'main';
    this.role_id = null;
    this.user_id = 0;
    this.username = '';
    console.log('User logged out');
  }

  onEditInfo() {
    this.currentView = 'editInfo';
    this.cdr.detectChanges(); // Force change detection for mobile
    if (this.username) {
      this.accountService.getUserByUsername(this.username).subscribe({
        next: (user) => {
          this.firstname = user.firstname;
          this.lastname = user.lastname;
          this.email = user.email;
          this.password = '';
          this.phoneNumber = user.phoneNumber;
          this.address = user.address;
          this.city = user.city;
          this.postalCode = user.postalCode;
          this.country = user.countries && user.countries.country_id ? user.countries.country_id.toString() : '';
        },
        error: (err) => {
          console.error('Failed to fetch user profile:', err);
        }
      });
    }
    console.log('Edit Info clicked - currentView set to:', this.currentView);
  }


  

  onViewPayments() {
    this.currentView = 'payments';
    this.cdr.detectChanges(); // Force change detection for mobile
    console.log('View Payments clicked - currentView set to:', this.currentView);
  }

  onViewTickets() {
    this.currentView = 'tickets';
    this.cdr.detectChanges(); // Force change detection for mobile
    console.log('View Tickets clicked - currentView set to:', this.currentView);
    this.loadUserTickets(); // Load tickets when switching to tickets view
  }

  onDisconnect() {
    this.isConnected = false;
    this.currentView = 'main';
    console.log('User disconnected');
  }

openSecurityPolicyModal(event: Event): void {
  event.preventDefault();
  this.securitypolicyService.getPolicies().subscribe(policies => {
    const activePolicy = policies.find(p => p.isActive === true);
    if (activePolicy) {
      this.dialog.open(SecuritypolicymodalComponent, {
        data: activePolicy
      });
    } else {
      alert('No active security policy found.');
    }
  }, error => {
    alert('Failed to load security policy.');
    console.error(error);
  });
}

    goBackGeneralPage(): void {
    this.location.back();
  }

  goBack() {
  const temp = this.currentView;
  this.currentView = this.previousView;
  this.previousView = temp;
}

  setView(view: typeof this.currentView) {
  this.previousView = this.currentView;
  this.currentView = view;
}

  onManageEvents() {
    // Set a view or navigate to the events management section
    this.currentView = 'manageEvents';
    console.log('Manage Events clicked');
  }

  onManageOffers() {
    // Set a view or navigate to the offers management section
    this.currentView = 'manageOffers';
    console.log('Manage Offers clicked');
  }

  onOffersSold() {
    // Set a view or navigate to the offers sold section
    this.currentView = 'offersSold';
    this.cdr.detectChanges(); // Force change detection for mobile
    this.loadSalesData();
    console.log('Offers Sold clicked - currentView set to:', this.currentView);
  }
  
  loadSalesData(): void {
    if (this.role_id === 1 || this.role_id === 2) {
      this.salesLoading = true;
      this.http.get<any>('http://localhost:8080/api/admin/sales-summary').subscribe({
        next: (data) => {
          this.totalSales = data.totalSales || 0;
          this.salesList = data.salesList || [];
          this.salesLoading = false;
          console.log('Real sales data loaded:', data);
        },
        error: (error) => {
          console.error('Error loading sales data:', error);
          this.salesLoading = false;
          // Mock data for development
          this.generateMockSalesData();
        }
      });
    }
  }
  
  generateMockSalesData(): void {
    this.totalSales = 15750.80;
    this.salesList = [
      {
        saleId: 'S001',
        customerName: 'John Doe',
        eventTitle: 'Opening Ceremony',
        offerTitle: 'Family Offer (35% off)',
        amount: 325.50,
        date: '2024-10-15',
        ticketCount: 4,
        seatClass: 'Premium'
      },
      {
        saleId: 'S002', 
        customerName: 'Jane Smith',
        eventTitle: 'Swimming Finals',
        offerTitle: 'Duo Offer (15% off)',
        amount: 180.75,
        date: '2024-10-14',
        ticketCount: 2,
        seatClass: 'Standard'
      },
      {
        saleId: 'S003',
        customerName: 'Bob Wilson',
        eventTitle: 'Athletics - 100m Final',
        offerTitle: 'Solo Offer (5% off)',
        amount: 95.00,
        date: '2024-10-13',
        ticketCount: 1,
        seatClass: 'VIP'
      }
    ];
  }

   

 onSaveInfo() {
    const updatedUser = {
      user_id: this.user_id, 
      firstname: this.firstname,
      lastname: this.lastname,
      email: this.email,
      password: this.password,
      phoneNumber: this.phoneNumber,
      address: this.address,
      city: this.city,
      postalCode: this.postalCode,
      countries: this.country ? { country_id: Number(this.country) } : null // <-- Send as object
    };

    this.accountService.updateDataUser(updatedUser).subscribe({
    next: (response) => {
      alert('Information updated successfully!');
      this.currentView = 'main';
      // Optionally refresh user data here
    },
    error: (err) => {
      alert('Failed to update information.');
      console.error('Update error:', err);
    }
  });

}

onLoginSuccess(userId: number) {
  const guestCart = localStorage.getItem('guest_cart');
  if (guestCart) {
    this.cartsService.mergeGuestCart(JSON.parse(guestCart), userId).subscribe(() => {
      localStorage.removeItem('guest_cart');
      // Optionally refresh the user's cart from backend
    });
  }
}

    onViewCarts() {
      this.currentView = 'carts';
      this.cdr.detectChanges(); // Force change detection for mobile
      if (this.isConnected) {
        this.cartsService.getUserCarts().subscribe((carts: CartsInterface[]) => {
          this.userCarts = carts;
        });
      }
      console.log('View Carts clicked - currentView set to:', this.currentView);

    }

    getUserCarts(): Observable<CartsInterface[]> {
  // ...implementation that returns an Observable, e.g.:
  return this.http.get<CartsInterface[]>('http://localhost:8080/api/user/carts/user');
}

get cartCoastDetails() {
  const cart = this.userCarts[0];
  if (!cart) return [];
  const offer = cart.user_id?.userselections?.offers;
  const events = cart.user_id?.userselections?.events;
  const sports = events?.sports;
  const ceremonies = events?.ceremonies;

  let seatPrice = 0;
  const priceKey = ('price' + this.selectedPriceClass) as 'price1' | 'price2' | 'price3';

  if (sports && typeof sports[priceKey] === 'number') {
    seatPrice = sports[priceKey];
  } else if (ceremonies && typeof (ceremonies as any)[priceKey] === 'number') {
    seatPrice = (ceremonies as any)[priceKey];
  }

  const nbSeats = this.nbPersons;
  const offerRate = offer?.rate || 0;
  const amount = (nbSeats * seatPrice) * (1 - offerRate / 100);

  return [
    { property: 'Unit Price', value: seatPrice },
    { property: 'Nb of Seats', value: nbSeats },
    { property: 'Amount', value: amount }
  ];
}

get cartOfferDetails() {
  const cart = this.userCarts[0];
  if (!cart || !cart.user_id.userselections.offers) return [];
  return [{
    name: cart.user_id.userselections.offers.title,
    discountRate: cart.user_id.userselections.offers.rate,
    nbSpectators: cart.user_id.userselections.offers.nbSpectators
  }];
}

get cartEventDetails() {
  const cart = this.userCarts[0];
  if (!cart || !cart.user_id?.userselections?.events) return [];
  const events = cart.user_id.userselections.events;
  const sports = events.sports;
  const ceremonies = events.ceremonies;
  return [
    { property: 'Title', value: events.title || sports?.name || ceremonies?.name || '' },
    { property: 'Description', value: events.description || sports?.description || ceremonies?.description || '' },
    { property: 'Date', value: events.date || '' },
    { property: 'Time', value: events.time || '' },
    { property: 'Address', value: sports?.sites?.address || ceremonies?.sites?.address || '' },
    { property: 'Postal Code', value: sports?.sites?.postalCode || ceremonies?.sites?.postalCode || '' },
    { property: 'City', value: sports?.sites?.city || ceremonies?.sites?.city || '' }
  ];
}

// Missing methods referenced in template
logout(): void {
  this.onLogout();
}

getLastActivityTime(): string {
  return 'Session active';
}

formatCartCreationDate(dateString: string): string {
  if (!dateString) return 'Date not available';
  try {
    const date = new Date(dateString);
    return date.toLocaleDateString('fr-FR') + ' ' + date.toLocaleTimeString('fr-FR');
  } catch (error) {
    return 'Invalid date';
  }
}

isCartIncomplete(cart: any): boolean {
  if (!cart || !cart.user_id || !cart.user_id.userselections) {
    return true;
  }
  
  const userselections = cart.user_id.userselections;
  const hasOffer = userselections.offers && userselections.offers.offer_id;
  const hasEvent = userselections.events && userselections.events.event_id;
  const hasPersons = userselections.nbPersons;
  const hasSeatClass = userselections.seat_class;
  
  // Check completion status from backend
  const completionStatus = userselections.completion_status;
  if (completionStatus === 'complete') {
    return false;
  }
  if (completionStatus === 'step1_complete') {
    return true; // Has offer but missing event/persons/seat_class
  }
  
  // Fallback to manual checking
  return !hasOffer || !hasEvent || !hasPersons || !hasSeatClass;
}

isCartComplete(cart: any): boolean {
  return !this.isCartIncomplete(cart);
}

completeCartSelection(cart: any): void {
  console.log('Complete cart selection for cart:', cart.cart_id);
  
  // Check if cart has offer but is missing event, persons, or seat class
  const hasOffer = cart.user_id?.userselections?.offers?.offer_id;
  const userselectionId = cart.user_id?.userselections?.usersel_id;
  
  if (!hasOffer) {
    console.error('Cart has no offer selected');
    alert('This cart has no offer selected. Please add an offer first.');
    return;
  }
  
  if (!userselectionId) {
    console.error('Cart has no userselection ID');
    alert('Cart data is incomplete. Please try refreshing the page.');
    return;
  }
  
  // Open offer stepper modal for cart completion
  const dialogRef = this.dialog.open(OfferStepperModalComponent, {
    width: '700px',
    height: '600px',
    data: {
      cartId: cart.cart_id,
      userselectionId: userselectionId,
      existingOffer: cart.user_id?.userselections?.offers,
      existingEvent: cart.user_id?.userselections?.events
    }
  });
  
  dialogRef.afterClosed().subscribe(result => {
    if (result === 'completed') {
      console.log('Cart completion successful');
      // Refresh cart data
      this.onViewCarts();
    } else if (result === 'cancelled') {
      console.log('Cart completion cancelled');
    }
  });
}

getSeatClassName(seatClass: any): string {
  if (!seatClass) return 'Not selected';
  
  switch(seatClass) {
    case 'price1':
    case 1:
      return 'Category 1';
    case 'price2':
    case 2:
      return 'Category 2';
    case 'price3':
    case 3:
      return 'Category 3';
    default:
      return 'Unknown category';
  }
}

getSeatPrice(cart: CartsInterface): number {
  if (!cart.user_id?.userselections?.events) {
    return 0;
  }
  
  const event = cart.user_id.userselections.events;
  // For now, we'll use a default seat class since the interface doesn't define it
  // The backend should provide this information
  return event.pricec1 || 0; // Default to category 1 price
}

calculateCostBeforeReduction(cart: any): number {
  if (!cart.user_id?.userselections?.events || !cart.user_id?.userselections?.nbPersons) {
    return 0;
  }
  
  const event = cart.user_id.userselections.events;
  const nbPersons = cart.user_id.userselections.nbPersons;
  const seatClass = cart.user_id.userselections.seat_class || 'price1';
  
  let seatPrice = 0;
  switch(seatClass) {
    case 'price1': seatPrice = event.pricec1 || 0; break;
    case 'price2': seatPrice = event.pricec2 || 0; break;
    case 'price3': seatPrice = event.pricec3 || 0; break;
    default: seatPrice = event.pricec1 || 0;
  }
  
  return seatPrice * nbPersons;
}

calculateTotalAmountWithDiscount(cart: any): number {
  if (!cart.user_id?.userselections?.offers) {
    return this.calculateCostBeforeReduction(cart);
  }
  
  const costBeforeReduction = this.calculateCostBeforeReduction(cart);
  const discountRate = cart.user_id.userselections.offers.rate || 0;
  const discountAmount = costBeforeReduction * (discountRate / 100);
  
  return costBeforeReduction - discountAmount;
}

closeCart(cart: any): void {
  console.log('Close cart:', cart.cart_id);
  if (confirm('Are you sure you want to close this cart? This action cannot be undone.')) {
    this.cartsService.deleteCart(cart.cart_id).subscribe({
      next: (response: any) => {
        console.log('Cart closed successfully:', response);
        alert('Cart closed successfully!');
        // Refresh the carts list
        this.cartsService.getUserCarts().subscribe((carts: CartsInterface[]) => {
          this.userCarts = carts;
        });
      },
      error: (error: any) => {
        console.error('Error closing cart:', error);
        alert('Error closing cart. Please try again.');
      }
    });
  }
}

proceedToPayment(cart: any): void {
  console.log('Proceed to payment for cart:', cart.cart_id);
  // TODO: Implement payment flow
}

  refreshTickets(): void {
    console.log('Refresh tickets');
    this.loadUserTickets();
  }

  loadUserTickets(): void {
    console.log('Load tickets called for user:', this.user_id);
    
    if (!this.user_id) {
      console.log('No user ID available, cannot load tickets');
      this.userTickets = [];
      return;
    }
    
    this.accountService.getUserTickets(this.user_id).subscribe({
      next: (tickets) => {
        console.log('Successfully loaded tickets:', tickets);
        this.userTickets = tickets || [];
        this.cdr.detectChanges(); // Force change detection
      },
      error: (error) => {
        console.error('Error loading user tickets:', error);
        this.userTickets = [];
        this.cdr.detectChanges();
      }
    });
  }checkForPurchasedTicket(): void {
  console.log('Check for purchased ticket');
  // TODO: Implement ticket check
}

// Properties referenced in template
latestPurchasedTicket: any = null;
userTickets: any[] = [];

// Global Payment Management Methods
onCartSelectionChange(cart: any, isSelected: boolean): void {
  this.selectedCartsForPayment.set(cart.cart_id.toString(), isSelected);
  this.updateGlobalPaymentAmount();
}

addCartToGlobalPayment(cart: any): void {
  const isSelected = this.selectedCartsForPayment.get(cart.cart_id.toString()) || false;
  if (!isSelected) {
    this.selectedCartsForPayment.set(cart.cart_id.toString(), true);
    this.updateGlobalPaymentAmount();
  }
}

getSelectedCartsCount(): number {
  let count = 0;
  this.selectedCartsForPayment.forEach(isSelected => {
    if (isSelected) count++;
  });
  return count;
}

updateGlobalPaymentAmount(): void {
  console.log('=== Updating Global Payment Amount ===');
  this.globalPaymentAmount = 0;
  this.userCarts.forEach(cart => {
    const isSelected = this.selectedCartsForPayment.get(cart.cart_id.toString());
    console.log(`Cart ${cart.cart_id}: selected=${isSelected}, complete=${this.isCartComplete(cart)}, totalAmount=${cart.totalAmount}`);
    if (isSelected && this.isCartComplete(cart)) {
      // Use the totalAmount stored in database instead of recalculating
      const cartAmount = cart.totalAmount || 0;
      this.globalPaymentAmount += cartAmount;
      console.log(`Adding cart ${cart.cart_id} amount: ${cartAmount}, new total: ${this.globalPaymentAmount}`);
    }
  });
  console.log('Final global payment amount:', this.globalPaymentAmount);
}

calculateUserSelectionAmount(cart: any): number {
  // New calculation formula: (((nb_persons - offer.nb_spectators) * seat_class_price) + ((offer.nb_spectators * seat_class_price * (100 - offer.rate)) / 100))
  
  const nbPersons = cart.nb_persons || 0;
  const seatClassPrice = this.getSeatPrice(cart);
  
  // Get offer information (nb_spectators and rate)
  const offerNbSpectators = cart.offer_nb_spectators || 0;
  const offerRate = cart.discount_rate || 0;
  
  // Calculate regular price for extra persons beyond offer coverage
  const extraPersons = Math.max(0, nbPersons - offerNbSpectators);
  const extraPersonsAmount = extraPersons * seatClassPrice;
  
  // Calculate discounted price for persons covered by offer
  // Updated formula: (nb_spectators * seat_class_price * (100 - offer.rate)) / 100
  const offerPersonsAmount = (offerNbSpectators * seatClassPrice * (100 - offerRate)) / 100;
  
  return extraPersonsAmount + offerPersonsAmount;
}

proceedToGlobalPayment(): void {
  if (this.getSelectedCartsCount() === 0) {
    alert('Please select at least one cart for payment.');
    return;
  }
  
  // Get all selected carts
  const selectedCarts = this.userCarts.filter(cart => 
    this.selectedCartsForPayment.get(cart.cart_id.toString()) === true
  );
  
  // Open cart payment modal with all selected carts
  console.log('Proceeding to global payment with carts:', selectedCarts);
  console.log('Total amount:', this.globalPaymentAmount);
  
  const dialogRef = this.dialog.open(CartPaymentModalComponent, {
    width: '700px',
    maxHeight: '90vh',
    data: {
      selectedCarts: selectedCarts,
      totalAmount: this.globalPaymentAmount
    }
  });

  dialogRef.afterClosed().subscribe(result => {
    if (result && result.success) {
      console.log('Payment completed successfully:', result);
      console.log('Tickets generated:', result.tickets);
      console.log('Cleared carts:', result.clearedCarts);
      
      // Clear the selection states for the paid carts
      result.clearedCarts.forEach((cart: any) => {
        this.selectedCartsForPayment.delete(cart.cart_id.toString());
      });
      
      // Refresh the cart list to reflect the changes
      this.cartsService.getUserCarts().subscribe((carts: CartsInterface[]) => {
        this.userCarts = carts;
        this.updateGlobalPaymentAmount(); // This will reset to 0 since no carts are selected
      });
      
      // ALWAYS REDIRECT TO TICKETS VIEW
      if (result.redirectToTickets) {
        this.currentView = 'tickets';
        this.loadUserTickets(); // Load tickets immediately
      }
      
      // Show success message
      alert(`Payment successful! ${result.tickets.length || 'Your'} tickets have been generated. Redirecting to My Tickets.`);
    }
  });
}

getEventLocation(cart: CartsInterface): string {
  if (!cart.user_id?.userselections?.events) {
    return 'Not selected';
  }
  
  const event = cart.user_id.userselections.events;
  
  // Check if it's a ceremony or sport event
  if (event.ceremonies?.sites) {
    const site = event.ceremonies.sites;
    return `${site.name}, ${site.city}`;
  }
  
  // If it's a sport event, we'd need to access the sports.sites path
  // but the interface shows sports as 'any', so we'll handle it generically
  return event.title || 'Location not available';
}

  trackBySaleId(index: number, sale: any): string {
    return sale.saleId;
  }

}
