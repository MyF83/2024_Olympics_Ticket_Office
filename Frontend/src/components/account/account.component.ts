import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
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
  
 

  constructor(
    private accountService: AccountsServiceService,
    private http: HttpClient,
    private sanitizer: DomSanitizer, // <-- Inject DomSanitizer
    private dialog: MatDialog,
    private securitypolicyService: SecuritypolicyService, // inject the service
    private location: Location, // Inject Location service for navigation
    private cdr: ChangeDetectorRef,
    private cartsService: CartsServiceService
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
            next: (response) => {
                this.isConnected = true;
                this.previousView = this.currentView;
                this.currentView = 'main';
                alert('Account created successfully! Your username is: ' + response.username);
                console.log('User registered:', response);
            },
            error: (err) => {
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
        // Define loginData here:  
        const loginData = {
          username: this.username,
          password: this.password
        };
          this.accountService.login(this.username, this.password).subscribe({
              next: (response) => {
                console.log('Login response:', response);
                // Save the JWT token to sessionStorage if present :
                if (response.token) {
                  sessionStorage.setItem('token', response.token);
                }  
                  this.isConnected = true;
                  this.role_id = response.roles?.role_id || response.role_id || null;
                  this.user_id = response.user_id;
                  this.cdr.detectChanges(); // <-- Force view update
                  alert('Login successful! Welcome back, ' + this.username + '!');
                  console.log('User logged in:', response);
                  // Fetch carts now that the user is authenticated
  this.cartsService.getUserCarts().subscribe((carts: CartsInterface[]) => this.userCarts = carts);
                  this.http.post<{ token: string }>('http://localhost:8080/api/user/login', loginData)
                    .subscribe(response => {
                      sessionStorage.setItem('token', response.token);
                      // ...other login logic...
                    });
              },
              error: (err) => {
                  alert('Login failed. Please check your credentials.');
                  console.error('Login error:', err);
              }
          });
          
      }
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
      console.log('View Carts clicked');

    }

    getUserCarts(): Observable<CartsInterface[]> {
  // ...implementation that returns an Observable, e.g.:
  return this.http.get<CartsInterface[]>('http://localhost:8080/api/user/carts/user');
}


}
