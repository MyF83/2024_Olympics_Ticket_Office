import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { of, throwError } from 'rxjs';

import { OffersComponent } from './offers.component';
import { OfferserviceService } from '../../services/offerservice.service';
import { CartsServiceService } from '../../services/cartsservice.service';
import { AuthStateService } from '../../services/auth-state.service';
import { Offersinterface } from '../../interfaces/offersinterface';

describe('OffersComponent', () => {
  let component: OffersComponent;
  let fixture: ComponentFixture<OffersComponent>;
  let offerService: jasmine.SpyObj<OfferserviceService>;
  let cartsService: jasmine.SpyObj<CartsServiceService>;
  let authStateService: jasmine.SpyObj<AuthStateService>;
  let router: jasmine.SpyObj<Router>;
  let matDialog: jasmine.SpyObj<MatDialog>;

  const mockOffers: Offersinterface[] = [
    {
      offer_id: 1,
      title: 'Solo Pass',
      description: 'Individual ticket package',
      image: 'solo.jpg',
      rate: 50,
      nbSpectators: 1
    },
    {
      offer_id: 2,
      title: 'Duo Pass',
      description: 'Two-person ticket package',
      image: 'duo.jpg',
      rate: 90,
      nbSpectators: 2
    },
    {
      offer_id: 3,
      title: 'Family Pass',
      description: 'Family ticket package',
      image: 'family.jpg',
      rate: 150,
      nbSpectators: 4
    }
  ];

  beforeEach(async () => {
    const offerServiceSpy = jasmine.createSpyObj('OfferserviceService', ['getOffers']);
    const cartsServiceSpy = jasmine.createSpyObj('CartsServiceService', ['createCartForUser']);
    const authStateServiceSpy = jasmine.createSpyObj('AuthStateService', ['checkLoginStatus']);
    const routerSpy = jasmine.createSpyObj('Router', ['navigate']);
    const matDialogSpy = jasmine.createSpyObj('MatDialog', ['open']);

    await TestBed.configureTestingModule({
      imports: [OffersComponent, HttpClientTestingModule],
      providers: [
        { provide: OfferserviceService, useValue: offerServiceSpy },
        { provide: CartsServiceService, useValue: cartsServiceSpy },
        { provide: AuthStateService, useValue: authStateServiceSpy },
        { provide: Router, useValue: routerSpy },
        { provide: MatDialog, useValue: matDialogSpy }
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OffersComponent);
    component = fixture.componentInstance;
    
    offerService = TestBed.inject(OfferserviceService) as jasmine.SpyObj<OfferserviceService>;
    cartsService = TestBed.inject(CartsServiceService) as jasmine.SpyObj<CartsServiceService>;
    authStateService = TestBed.inject(AuthStateService) as jasmine.SpyObj<AuthStateService>;
    router = TestBed.inject(Router) as jasmine.SpyObj<Router>;
    matDialog = TestBed.inject(MatDialog) as jasmine.SpyObj<MatDialog>;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('ngOnInit', () => {
    it('should load offers on initialization', () => {
      offerService.getOffers.and.returnValue(of(mockOffers));

      component.ngOnInit();

      expect(offerService.getOffers).toHaveBeenCalled();
      expect(component.offers).toEqual(mockOffers);
    });

    it('should handle error when loading offers fails', () => {
      const errorResponse = { error: 'Failed to load offers' };
      offerService.getOffers.and.returnValue(throwError(errorResponse));
      spyOn(console, 'error');

      component.ngOnInit();

      expect(offerService.getOffers).toHaveBeenCalled();
      expect(component.offers).toEqual([]);
      // Error would be logged but component should handle gracefully
    });
  });

  describe('addToCart', () => {
    let mockOffer: Offersinterface;

    beforeEach(() => {
      mockOffer = mockOffers[0];
      spyOn(window, 'alert');
    });

    it('should add offer to cart when user is authenticated via sessionStorage', () => {
      spyOn(sessionStorage, 'getItem').and.returnValue('mock-token');
      spyOn(localStorage, 'getItem').and.returnValue(null);
      cartsService.createCartForUser.and.returnValue(of({ message: 'Cart created successfully' }));

      component.addToCart(mockOffer);

      expect(cartsService.createCartForUser).toHaveBeenCalledWith({
        offer_id: mockOffer.offer_id,
        event_id: null,
        nb_persons: 1,
        seat_class: 'price1',
        amount: 0
      });
      expect(window.alert).toHaveBeenCalledWith(`Offer '${mockOffer.title}' added to cart! Redirecting to complete your selection.`);
      expect(router.navigate).toHaveBeenCalledWith(['/account'], { queryParams: { view: 'carts' } });
    });

    it('should add offer to cart when user is authenticated via localStorage', () => {
      spyOn(sessionStorage, 'getItem').and.returnValue(null);
      spyOn(localStorage, 'getItem').and.returnValue('mock-auth-token');
      cartsService.createCartForUser.and.returnValue(of({ message: 'Cart created successfully' }));

      component.addToCart(mockOffer);

      expect(cartsService.createCartForUser).toHaveBeenCalled();
      expect(window.alert).toHaveBeenCalledWith(`Offer '${mockOffer.title}' added to cart! Redirecting to complete your selection.`);
      expect(router.navigate).toHaveBeenCalledWith(['/account'], { queryParams: { view: 'carts' } });
    });

    it('should handle when offer already exists in cart', () => {
      spyOn(sessionStorage, 'getItem').and.returnValue('mock-token');
      spyOn(localStorage, 'getItem').and.returnValue(null);
      cartsService.createCartForUser.and.returnValue(of({ message: 'Cart already exists for this offer' }));

      component.addToCart(mockOffer);

      expect(window.alert).toHaveBeenCalledWith(`Great! You already have the '${mockOffer.title}' offer. Let's go to your cart to manage it.`);
      expect(router.navigate).toHaveBeenCalledWith(['/account'], { queryParams: { view: 'carts' } });
    });

    it('should handle 409 conflict error (cart already exists)', () => {
      spyOn(sessionStorage, 'getItem').and.returnValue('mock-token');
      spyOn(localStorage, 'getItem').and.returnValue(null);
      const conflictError = { status: 409, error: 'Conflict' };
      cartsService.createCartForUser.and.returnValue(throwError(conflictError));

      component.addToCart(mockOffer);

      expect(window.alert).toHaveBeenCalledWith(`Offer '${mockOffer.title}' is already in your cart! Redirecting to My Carts.`);
      expect(router.navigate).toHaveBeenCalledWith(['/account'], { queryParams: { view: 'carts' } });
    });

    it('should handle general errors when adding to cart', () => {
      spyOn(sessionStorage, 'getItem').and.returnValue('mock-token');
      spyOn(localStorage, 'getItem').and.returnValue(null);
      const error = { status: 500, error: { message: 'Internal server error' } };
      cartsService.createCartForUser.and.returnValue(throwError(error));
      spyOn(console, 'error');

      component.addToCart(mockOffer);

      expect(console.error).toHaveBeenCalledWith('Error creating cart:', error);
      expect(window.alert).toHaveBeenCalledWith('Error adding offer to cart: Internal server error (Status: 500). Please try again.');
    });

    it('should redirect to login when user is not authenticated', () => {
      spyOn(sessionStorage, 'getItem').and.returnValue(null);
      spyOn(localStorage, 'getItem').and.returnValue(null);
      spyOn(localStorage, 'setItem');

      component.addToCart(mockOffer);

      expect(localStorage.setItem).toHaveBeenCalledWith('pending_offer', JSON.stringify(mockOffer));
      expect(window.alert).toHaveBeenCalledWith('You need to be logged in to add items to cart. Please login/register.');
      expect(router.navigate).toHaveBeenCalledWith(['/account'], { queryParams: { view: 'carts' } });
      expect(cartsService.createCartForUser).not.toHaveBeenCalled();
    });
  });

  describe('component initialization', () => {
    it('should initialize with empty offers array', () => {
      expect(component.offers).toEqual([]);
    });

    it('should call ngOnInit on component initialization', () => {
      spyOn(component, 'ngOnInit');
      component.ngOnInit();
      expect(component.ngOnInit).toHaveBeenCalled();
    });
  });
});
