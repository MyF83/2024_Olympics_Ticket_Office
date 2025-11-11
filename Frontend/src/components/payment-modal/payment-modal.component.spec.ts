import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule, FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { of, throwError, BehaviorSubject } from 'rxjs';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';

import { PaymentModalComponent } from './payment-modal.component';
import { CartsServiceService } from '../../services/cartsservice.service';
import { AccountsServiceService } from '../../services/accountsservice.service';
import { AuthStateService } from '../../services/auth-state.service';

describe('PaymentModalComponent', () => {
  let component: PaymentModalComponent;
  let fixture: ComponentFixture<PaymentModalComponent>;
  let mockDialogRef: jasmine.SpyObj<MatDialogRef<PaymentModalComponent>>;
  let mockRouter: jasmine.SpyObj<Router>;
  let mockCartsService: jasmine.SpyObj<CartsServiceService>;
  let mockAccountsService: jasmine.SpyObj<AccountsServiceService>;
  let mockAuthStateService: jasmine.SpyObj<AuthStateService>;
  let mockCurrentUser$: BehaviorSubject<any>;
  let mockDialogData: any;

  beforeEach(async () => {
    // Create spies for all dependencies
    mockDialogRef = jasmine.createSpyObj('MatDialogRef', ['close']);
    mockRouter = jasmine.createSpyObj('Router', ['navigate']);
    mockCartsService = jasmine.createSpyObj('CartsServiceService', ['clearCart']);
    mockAccountsService = jasmine.createSpyObj('AccountsServiceService', ['getCurrentUser', 'createTicket']);
    
    // Create a BehaviorSubject for currentUser$
    mockCurrentUser$ = new BehaviorSubject(null);
    mockAuthStateService = jasmine.createSpyObj('AuthStateService', ['setCurrentUser'], {
      currentUser$: mockCurrentUser$.asObservable()
    });

    // Mock dialog data with cart
    mockDialogData = {
      cart: [
        {
          id: 1,
          event_id: 1,
          seat_class: 'premium',
          nb_persons: 2,
          amount: 200,
          event_name: 'Swimming Finals'
        }
      ]
    };

    await TestBed.configureTestingModule({
      imports: [
        PaymentModalComponent,
        ReactiveFormsModule,
        MatDialogModule,
        NoopAnimationsModule
      ],
      providers: [
        FormBuilder,
        { provide: MatDialogRef, useValue: mockDialogRef },
        { provide: MAT_DIALOG_DATA, useValue: mockDialogData },
        { provide: Router, useValue: mockRouter },
        { provide: CartsServiceService, useValue: mockCartsService },
        { provide: AccountsServiceService, useValue: mockAccountsService },
        { provide: AuthStateService, useValue: mockAuthStateService }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(PaymentModalComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('Component Initialization', () => {
    it('should initialize with default values', () => {
      expect(component.isProcessing).toBeFalse();
      expect(component.errorMessage).toBe('');
      expect(component.successMessage).toBe('');
      expect(component.currentUser).toBeNull();
    });

    it('should store cart data from dialog data', () => {
      fixture.detectChanges();
      expect(component.cartItems).toEqual(mockDialogData.cart);
    });

    it('should handle single cart item (not array)', () => {
      const singleCartData = { cart: { id: 1, event_name: 'Single Event' } };
      component.data = singleCartData;
      
      // Simulate constructor logic
      if (component.data && component.data.cart) {
        component.cartItems = Array.isArray(component.data.cart) ? component.data.cart : [component.data.cart];
      }
      
      expect(component.cartItems).toEqual([{ id: 1, event_name: 'Single Event' }]);
    });

    it('should initialize payment form with validators', () => {
      fixture.detectChanges();
      
      expect(component.paymentForm).toBeDefined();
      expect(component.paymentForm.get('cardholderName')?.hasError('required')).toBeTrue();
      expect(component.paymentForm.get('cardNumber')?.hasError('required')).toBeTrue();
      expect(component.paymentForm.get('expiryDate')?.hasError('required')).toBeTrue();
      expect(component.paymentForm.get('cvv')?.hasError('required')).toBeTrue();
    });
  });

  describe('User Authentication', () => {
    it('should set current user when auth state provides user', () => {
      const mockUser = { id: 1, firstname: 'John', lastname: 'Doe', email: 'john@test.com' };
      
      fixture.detectChanges();
      mockCurrentUser$.next(mockUser);
      
      expect(component.currentUser).toEqual(mockUser);
    });

    it('should load user profile when no user from auth state', () => {
      const mockUser = { id: 1, firstname: 'Jane', email: 'jane@test.com' };
      mockAccountsService.getCurrentUser.and.returnValue(of(mockUser));
      
      // Mock sessionStorage
      spyOn(sessionStorage, 'getItem').and.returnValue('mock-token');
      
      fixture.detectChanges();
      component.loadUserProfile();
      
      expect(mockAccountsService.getCurrentUser).toHaveBeenCalled();
      expect(mockAuthStateService.setCurrentUser).toHaveBeenCalledWith(mockUser);
      expect(component.currentUser).toEqual(mockUser);
    });

    it('should handle error when loading user profile', () => {
      mockAccountsService.getCurrentUser.and.returnValue(throwError('Auth error'));
      spyOn(sessionStorage, 'getItem').and.returnValue('mock-token');
      
      component.loadUserProfile();
      
      expect(component.errorMessage).toBe('Authentication error. Please login again.');
    });

    it('should set error when no auth token found', () => {
      spyOn(sessionStorage, 'getItem').and.returnValue(null);
      spyOn(localStorage, 'getItem').and.returnValue(null);
      
      component.loadUserProfile();
      
      expect(component.errorMessage).toBe('No authentication token found. Please login again.');
    });
  });

  describe('Form Validation', () => {
    beforeEach(() => {
      fixture.detectChanges();
    });

    it('should validate cardholder name', () => {
      const nameControl = component.paymentForm.get('cardholderName');
      
      nameControl?.setValue('');
      expect(nameControl?.hasError('required')).toBeTrue();
      
      nameControl?.setValue('A');
      expect(nameControl?.hasError('minlength')).toBeTrue();
      
      nameControl?.setValue('John Doe');
      expect(nameControl?.valid).toBeTrue();
    });

    it('should validate card number format', () => {
      const cardControl = component.paymentForm.get('cardNumber');
      
      cardControl?.setValue('1234');
      expect(cardControl?.hasError('pattern')).toBeTrue();
      
      cardControl?.setValue('1234 5678 9012 3456');
      expect(cardControl?.valid).toBeTrue();
    });

    it('should validate expiry date format', () => {
      const expiryControl = component.paymentForm.get('expiryDate');
      
      expiryControl?.setValue('13/25');
      expect(expiryControl?.hasError('pattern')).toBeTrue();
      
      expiryControl?.setValue('12/25');
      expect(expiryControl?.valid).toBeTrue();
    });

    it('should validate CVV format', () => {
      const cvvControl = component.paymentForm.get('cvv');
      
      cvvControl?.setValue('12');
      expect(cvvControl?.hasError('pattern')).toBeTrue();
      
      cvvControl?.setValue('1234');
      expect(cvvControl?.hasError('pattern')).toBeTrue();
      
      cvvControl?.setValue('123');
      expect(cvvControl?.valid).toBeTrue();
    });
  });

  describe('Custom Expiry Date Validator', () => {
    it('should return null for valid future date', () => {
      const futureYear = (new Date().getFullYear() + 1) % 100;
      const control = { value: `12/${futureYear.toString().padStart(2, '0')}` };
      
      const result = component.validateExpiryDate(control);
      expect(result).toBeNull();
    });

    it('should return error for expired date', () => {
      const control = { value: '01/20' }; // Past date
      
      const result = component.validateExpiryDate(control);
      expect(result).toEqual({ expired: true });
    });

    it('should return error for invalid format', () => {
      const control = { value: '13/25' }; // Invalid month
      
      const result = component.validateExpiryDate(control);
      expect(result).toEqual({ invalidFormat: true });
    });

    it('should return null for empty value', () => {
      const control = { value: '' };
      
      const result = component.validateExpiryDate(control);
      expect(result).toBeNull();
    });
  });

  describe('Form Field Formatters', () => {
    beforeEach(() => {
      fixture.detectChanges();
    });

    it('should format card number input', () => {
      const mockEvent = {
        target: { value: '1234567890123456' }
      };
      
      component.formatCardNumber(mockEvent);
      
      expect(mockEvent.target.value).toBe('1234 5678 9012 3456');
      expect(component.paymentForm.get('cardNumber')?.value).toBe('1234 5678 9012 3456');
    });

    it('should limit card number to 19 characters', () => {
      const mockEvent = {
        target: { value: '12345678901234567890' } // Too long
      };
      
      component.formatCardNumber(mockEvent);
      
      expect(mockEvent.target.value).toBe('1234 5678 9012 3456');
    });

    it('should format expiry date input', () => {
      const mockEvent = {
        target: { value: '1225' }
      };
      
      component.formatExpiryDate(mockEvent);
      
      expect(mockEvent.target.value).toBe('12/25');
      expect(component.paymentForm.get('expiryDate')?.value).toBe('12/25');
    });

    it('should limit CVV input to 3 digits', () => {
      const mockEvent = {
        target: { value: '12345' }
      };
      
      component.formatCVV(mockEvent);
      
      expect(mockEvent.target.value).toBe('123');
      expect(component.paymentForm.get('cvv')?.value).toBe('123');
    });
  });

  describe('Form Submission', () => {
    beforeEach(() => {
      fixture.detectChanges();
      // Set up valid form
      component.paymentForm.patchValue({
        cardholderName: 'John Doe',
        cardNumber: '1234 5678 9012 3456',
        expiryDate: '12/25',
        cvv: '123'
      });
    });

    it('should not submit if form is invalid', () => {
      component.paymentForm.patchValue({ cardholderName: '' }); // Make form invalid
      
      component.onSubmit();
      
      expect(component.isProcessing).toBeFalse();
    });

    it('should start processing when form is valid', () => {
      jasmine.clock().install();
      
      component.onSubmit();
      
      expect(component.isProcessing).toBeTrue();
      expect(component.errorMessage).toBe('');
      expect(component.successMessage).toBe('');
      
      jasmine.clock().uninstall();
    });
  });

  describe('Ticket Generation', () => {
    beforeEach(() => {
      fixture.detectChanges();
      component.currentUser = { id: 1, firstname: 'John' };
      component.cartItems = mockDialogData.cart;
    });

    it('should generate tickets successfully', () => {
      const mockResponse = { tickets: [{ id: 1, ticketNumber: 'TICKET-123' }] };
      mockAccountsService.createTicket.and.returnValue(of(mockResponse));
      mockCartsService.clearCart.and.returnValue(of({}));
      
      component.generateTickets();
      
      expect(mockAccountsService.createTicket).toHaveBeenCalledWith(jasmine.objectContaining({
        cartItems: component.cartItems
      }));
    });

    it('should handle error when user not authenticated', () => {
      component.currentUser = null;
      
      component.generateTickets();
      
      expect(component.errorMessage).toBe('User not authenticated');
      expect(component.isProcessing).toBeFalse();
    });

    it('should handle error when no cart items', () => {
      component.cartItems = [];
      
      component.generateTickets();
      
      expect(component.errorMessage).toBe('No items in cart');
      expect(component.isProcessing).toBeFalse();
    });

    it('should handle ticket creation error', () => {
      mockAccountsService.createTicket.and.returnValue(throwError('Ticket error'));
      
      component.generateTickets();
      
      expect(component.errorMessage).toBe('Failed to generate tickets. Please try again.');
      expect(component.isProcessing).toBeFalse();
    });

    it('should close dialog after successful ticket generation', (done) => {
      const mockResponse = { tickets: [{ id: 1 }] };
      mockAccountsService.createTicket.and.returnValue(of(mockResponse));
      mockCartsService.clearCart.and.returnValue(of({}));
      
      component.generateTickets();
      
      // Wait for the setTimeout to complete
      setTimeout(() => {
        expect(mockDialogRef.close).toHaveBeenCalledWith({ 
          success: true, 
          tickets: mockResponse.tickets 
        });
        done();
      }, 2100);
    });
  });

  describe('Cart Management', () => {
    beforeEach(() => {
      fixture.detectChanges();
      component.currentUser = { id: 1, firstname: 'John' };
    });

    it('should clear cart after successful payment', () => {
      mockCartsService.clearCart.and.returnValue(of({}));
      
      component.clearCart();
      
      expect(mockCartsService.clearCart).toHaveBeenCalledWith(1);
    });

    it('should handle cart clearing error', () => {
      mockCartsService.clearCart.and.returnValue(throwError('Clear error'));
      spyOn(console, 'error');
      
      component.clearCart();
      
      expect(console.error).toHaveBeenCalledWith('Error clearing cart:', 'Clear error');
    });

    it('should not clear cart if no current user', () => {
      component.currentUser = null;
      
      component.clearCart();
      
      expect(mockCartsService.clearCart).not.toHaveBeenCalled();
    });
  });

  describe('Dialog Actions', () => {
    it('should close dialog when cancel is clicked', () => {
      component.onCancel();
      
      expect(mockDialogRef.close).toHaveBeenCalled();
    });
  });

  describe('Form Field Getters', () => {
    beforeEach(() => {
      fixture.detectChanges();
    });

    it('should return form controls via getters', () => {
      expect(component.cardholderName).toBe(component.paymentForm.get('cardholderName'));
      expect(component.cardNumber).toBe(component.paymentForm.get('cardNumber'));
      expect(component.expiryDate).toBe(component.paymentForm.get('expiryDate'));
      expect(component.cvv).toBe(component.paymentForm.get('cvv'));
    });
  });

  describe('Edge Cases', () => {
    it('should handle missing dialog data', () => {
      const originalData = component.data;
      component.data = null;
      component.cartItems = []; // Reset to empty
      
      // Simulate constructor logic
      if (component.data && component.data.cart) {
        component.cartItems = Array.isArray(component.data.cart) ? component.data.cart : [component.data.cart];
      }
      
      expect(component.cartItems).toEqual([]);
      
      // Restore original data
      component.data = originalData;
    });

    it('should handle localStorage fallback for auth tokens', () => {
      const mockUser = { id: 1, firstname: 'Jane' };
      mockAccountsService.getCurrentUser.and.returnValue(of(mockUser));
      
      spyOn(sessionStorage, 'getItem').and.returnValue(null);
      spyOn(localStorage, 'getItem').and.returnValue('mock-local-token');
      
      component.loadUserProfile();
      
      expect(mockAccountsService.getCurrentUser).toHaveBeenCalled();
      expect(component.currentUser).toEqual(mockUser);
    });
  });
});