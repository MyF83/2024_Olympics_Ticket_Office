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
    }
  }

  this.route.queryParams.subscribe(params => {
    if (params['view'] === 'login') {
      this.currentView = 'login';
    } else if (params['view'] === 'register') {
      this.currentView = 'register';
    }
    if (params['view'] === 'login' || params['view'] === 'register') {
      this.currentView = params['view'];
      this.redirectToCartsAfterAuth = true;
    }
  });
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
          // Handle roles as array or object
          if (Array.isArray(response.roles)) {
            // If roles is an array, find the highest privilege role
            this.role_id = response.roles.length > 0 ? response.roles[0].role_id : null;
          } else if (response.roles && response.roles.role_id) {
            this.role_id = response.roles.role_id;
          } else {
            this.role_id = response.role_id || null;
          }
          this.user_id = response.user_id;
          this.isConnected = true;
          this.cdr.detectChanges(); // <-- Force view update
          alert('Login successful! Welcome back, ' + this.username + '!');
          console.log('User logged in:', response);
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
    console.log('Edit Info clicked');
  }


  

  onViewPayments() {
    this.currentView = 'payments';
    console.log('View Payments clicked');
  }

  onViewTickets() {
    this.currentView = 'tickets';
    console.log('View Tickets clicked');
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
    console.log('Offers Sold clicked');
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
      if (this.isConnected) {
        this.cartsService.getUserCarts().subscribe((carts: CartsInterface[]) => {
          this.userCarts = carts;
        });
      }
      console.log('View Carts clicked');

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

}
