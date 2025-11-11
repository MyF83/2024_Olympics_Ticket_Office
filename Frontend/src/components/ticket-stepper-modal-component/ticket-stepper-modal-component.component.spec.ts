import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialog } from '@angular/material/dialog';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { of, throwError } from 'rxjs';

import { TicketStepperModalComponent } from './ticket-stepper-modal-component.component';
import { AuthStateService } from '../../services/auth-state.service';
import { CartsServiceService } from '../../services/cartsservice.service';
import { PaymentModalComponent } from '../payment-modal/payment-modal.component';

describe('TicketStepperModalComponent', () => {
  let component: TicketStepperModalComponent;
  let fixture: ComponentFixture<TicketStepperModalComponent>;
  let mockDialogRef: jasmine.SpyObj<MatDialogRef<TicketStepperModalComponent>>;
  let mockDialog: jasmine.SpyObj<MatDialog>;
  let mockAuthStateService: jasmine.SpyObj<AuthStateService>;
  let mockCartsService: jasmine.SpyObj<CartsServiceService>;
  let mockDialogData: any;

  beforeEach(async () => {
    // Create spies for all dependencies
    mockDialogRef = jasmine.createSpyObj('MatDialogRef', ['close']);
    mockDialog = jasmine.createSpyObj('MatDialog', ['open']);
    mockAuthStateService = jasmine.createSpyObj('AuthStateService', ['checkLoginStatus', 'getCurrentUser']);
    mockCartsService = jasmine.createSpyObj('CartsServiceService', ['createCartForUser', 'completeCartSelection']);

    // Mock dialog data
    mockDialogData = {
      game: {
        id: 1,
        name: 'Swimming Finals',
        price: 100,
        discipline: 'Swimming'
      }
    };

    await TestBed.configureTestingModule({
      imports: [
        TicketStepperModalComponent, 
        HttpClientTestingModule,
        BrowserAnimationsModule
      ],
      providers: [
        { provide: MatDialogRef, useValue: mockDialogRef },
        { provide: MAT_DIALOG_DATA, useValue: mockDialogData },
        { provide: MatDialog, useValue: mockDialog },
        { provide: AuthStateService, useValue: mockAuthStateService },
        { provide: CartsServiceService, useValue: mockCartsService }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(TicketStepperModalComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('Component Initialization', () => {
    it('should initialize with default values', () => {
      expect(component.isLoggedIn).toBeFalse();
      expect(component.selectedOffer).toBeNull();
      expect(component.nbSpectators).toBe(1);
      expect(component.priceClass).toBe(1);
      expect(component.showOfferSelection).toBeFalse();
      expect(component.showSummary).toBeFalse();
      expect(component.isCompletingExistingCart).toBeFalse();
      expect(component.existingCartId).toBeNull();
      expect(component.selectedOfferType).toBeNull();
    });

    it('should have predefined offer types', () => {
      expect(component.offerTypes).toBeDefined();
      expect(component.offerTypes.length).toBe(3);
      expect(component.offerTypes[0].type).toBe('solo');
      expect(component.offerTypes[1].type).toBe('duo');
      expect(component.offerTypes[2].type).toBe('family');
    });

    it('should set selectedOffer from dialog data on init', () => {
      fixture.detectChanges(); // Trigger ngOnInit
      expect(component.selectedOffer).toEqual(mockDialogData.game);
    });
  });

  describe('Authentication Check', () => {
    it('should check login status on init when user is logged in', () => {
      const mockUser = { user_id: 1, firstname: 'John', email: 'john@test.com' };
      mockAuthStateService.checkLoginStatus.and.returnValue(true);
      mockAuthStateService.getCurrentUser.and.returnValue(mockUser);

      component.ngOnInit();

      expect(mockAuthStateService.checkLoginStatus).toHaveBeenCalled();
      expect(component.isLoggedIn).toBeTrue();
    });

    it('should handle not logged in state', () => {
      mockAuthStateService.checkLoginStatus.and.returnValue(false);

      component.ngOnInit();

      expect(component.isLoggedIn).toBeFalse();
    });
  });

  describe('Offer Type Selection', () => {
    it('should select solo offer type', () => {
      component.selectOfferType('solo');

      expect(component.selectedOfferType).toBe('solo');
      expect(component.showOfferSelection).toBeFalse(); // This doesn't get set in selectOfferType
    });

    it('should select duo offer type', () => {
      component.selectOfferType('duo');

      expect(component.selectedOfferType).toBe('duo');
      expect(component.selectedOffer.name).toContain('Duo');
    });

    it('should select family offer type', () => {
      component.selectOfferType('family');

      expect(component.selectedOfferType).toBe('family');
      expect(component.selectedOffer.name).toContain('Family');
    });
  });

  describe('Component Actions', () => {
    beforeEach(() => {
      component.selectedOffer = { price: 100, name: 'Swimming Finals' };
      component.selectOfferType('duo');
    });

    it('should show summary when finish is called', () => {
      component.finish();
      expect(component.showSummary).toBeTrue();
    });

    it('should go to offers page', () => {
      component.goToOffersPage();
      expect(component.showOfferSelection).toBeTrue();
    });

    it('should close dialog and clear cart', () => {
      component.closeAndClearCart();
      expect(mockDialogRef.close).toHaveBeenCalledWith({ action: 'close' });
    });

    it('should go to register with ticket data', () => {
      component.data.game = { event_id: 1, title: 'Swimming Finals' };
      component.goToRegister();
      
      expect(mockDialogRef.close).toHaveBeenCalledWith({
        action: 'register',
        ticketData: jasmine.objectContaining({
          eventId: 1,
          eventTitle: 'Swimming Finals',
          offerType: 'duo'
        })
      });
    });

    it('should move to next step', () => {
      // Mock the stepper
      component.stepper = jasmine.createSpyObj('MatStepper', ['next']);
      
      component.nextFromStep2();
      
      expect(component.stepper.next).toHaveBeenCalled();
    });
  });

  describe('Edge Cases', () => {
    it('should handle nextFromStep2 when stepper is not available', () => {
      component.stepper = undefined as any;
      
      // Should not throw error
      expect(() => component.nextFromStep2()).not.toThrow();
    });

    it('should handle selectOffer method', () => {
      const testOffer = { name: 'Test Offer', price: 50 };
      component.selectOffer(testOffer);
      
      expect(component.selectedOffer).toEqual(testOffer);
    });

    it('should handle invalid offer type selection', () => {
      const originalOffer = component.selectedOffer;
      component.selectOfferType('invalid' as any);
      
      // selectedOffer should not change for invalid type
      expect(component.selectedOffer).toEqual(originalOffer);
    });
  });
});
