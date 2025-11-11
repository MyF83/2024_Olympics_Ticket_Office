import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { CartsServiceService } from './cartsservice.service';

describe('CartsServiceService', () => {
  let service: CartsServiceService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [CartsServiceService]
    });
    service = TestBed.inject(CartsServiceService);
    httpMock = TestBed.inject(HttpTestingController);
    
    // Clear localStorage before each test
    localStorage.clear();
  });

  afterEach(() => {
    httpMock.verify();
    localStorage.clear();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should get user carts', () => {
    const mockCarts = [
      { 
        cart_id: 1, 
        user_id: 1, 
        total_amount: 100, 
        date: '2024-01-01',
        userselections: { offer_id: 1, nb_persons: 2 }
      },
      { 
        cart_id: 2, 
        user_id: 1, 
        total_amount: 200, 
        date: '2024-01-02',
        userselections: { offer_id: 2, nb_persons: 3 }
      }
    ] as any;

    service.getUserCarts().subscribe(carts => {
      expect(carts).toEqual(mockCarts);
      expect(carts.length).toBe(2);
    });

    const req = httpMock.expectOne('/api/user/carts/user');
    expect(req.request.method).toBe('GET');
    req.flush(mockCarts);
  });

  it('should add offer to localStorage guest cart', () => {
    const mockOffer = {
      offer_id: 1,
      title: 'Test Offer',
      price: 100,
      description: 'Test description'
    } as any;

    service.addToCart(mockOffer);

    const cart = JSON.parse(localStorage.getItem('guest_cart') || '[]');
    expect(cart).toEqual([mockOffer]);
    expect(cart.length).toBe(1);
  });

  it('should add multiple offers to localStorage guest cart', () => {
    const offer1 = { offer_id: 1, title: 'Offer 1', price: 100 } as any;
    const offer2 = { offer_id: 2, title: 'Offer 2', price: 200 } as any;

    service.addToCart(offer1);
    service.addToCart(offer2);

    const cart = JSON.parse(localStorage.getItem('guest_cart') || '[]');
    expect(cart).toEqual([offer1, offer2]);
    expect(cart.length).toBe(2);
  });

  it('should handle empty localStorage when adding to cart', () => {
    const mockOffer = { offer_id: 1, title: 'Test Offer' } as any;
    
    // Ensure localStorage is empty
    expect(localStorage.getItem('guest_cart')).toBeNull();

    service.addToCart(mockOffer);

    const cart = JSON.parse(localStorage.getItem('guest_cart') || '[]');
    expect(cart).toEqual([mockOffer]);
  });

  it('should merge guest cart with user cart', () => {
    const guestCart = [
      { offer_id: 1, title: 'Guest Offer 1' },
      { offer_id: 2, title: 'Guest Offer 2' }
    ] as any;
    const userId = 123;
    const mockResponse = { success: true, merged_items: 2 };

    service.mergeGuestCart(guestCart, userId).subscribe(response => {
      expect(response).toEqual(mockResponse);
    });

    const req = httpMock.expectOne('/api/cart/merge');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual({ userId, items: guestCart });
    req.flush(mockResponse);
  });

  it('should create cart for user', () => {
    const ticketData = {
      offer_id: 1,
      nb_persons: 2,
      seat_class: 'premium'
    };
    const mockResponse = { cart_id: 456, status: 'created' };

    service.createCartForUser(ticketData).subscribe(response => {
      expect(response).toEqual(mockResponse);
    });

    const req = httpMock.expectOne('/api/user/carts');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(ticketData);
    req.flush(mockResponse);
  });

  it('should complete cart selection', () => {
    const cartId = 123;
    const cartData = {
      event_id: 1,
      seat_class: 'premium',
      nb_persons: 2,
      amount: 200
    };
    const mockResponse = { success: true, cart_id: cartId };

    service.completeCartSelection(cartId, cartData).subscribe(response => {
      expect(response).toEqual(mockResponse);
    });

    const req = httpMock.expectOne('/api/user/carts/123/complete');
    expect(req.request.method).toBe('PUT');
    expect(req.request.body).toEqual(cartData);
    req.flush(mockResponse);
  });

  it('should get cart items for user', () => {
    const userId = 123;
    const mockCartItems = [
      { cart_id: 1, offer_title: 'Football Match', amount: 100 },
      { cart_id: 2, offer_title: 'Swimming Event', amount: 150 }
    ];

    service.getCartItems(userId).subscribe(items => {
      expect(items).toEqual(mockCartItems);
      expect(items.length).toBe(2);
    });

    const req = httpMock.expectOne('/api/user/carts/123');
    expect(req.request.method).toBe('GET');
    req.flush(mockCartItems);
  });

  it('should clear cart for user', () => {
    const userId = 123;
    const mockResponse = { success: true, message: 'Cart cleared' };

    service.clearCart(userId).subscribe(response => {
      expect(response).toEqual(mockResponse);
    });

    const req = httpMock.expectOne('/api/user/carts/123');
    expect(req.request.method).toBe('DELETE');
    req.flush(mockResponse);
  });

  it('should delete specific cart', () => {
    const cartId = 456;
    const mockResponse = { success: true, deleted_cart_id: cartId };

    service.deleteCart(cartId).subscribe(response => {
      expect(response).toEqual(mockResponse);
    });

    const req = httpMock.expectOne('/api/cart/456');
    expect(req.request.method).toBe('DELETE');
    req.flush(mockResponse);
  });

  it('should handle HTTP errors when getting user carts', () => {
    service.getUserCarts().subscribe(
      () => fail('expected an error, not carts'),
      error => {
        expect(error.status).toBe(500);
      }
    );

    const req = httpMock.expectOne('/api/user/carts/user');
    req.flush('Server Error', { status: 500, statusText: 'Internal Server Error' });
  });

  it('should handle HTTP errors when merging guest cart', () => {
    const guestCart = [{ offer_id: 1 }] as any;
    
    service.mergeGuestCart(guestCart, 123).subscribe(
      () => fail('expected an error, not success'),
      error => {
        expect(error.status).toBe(400);
      }
    );

    const req = httpMock.expectOne('/api/cart/merge');
    req.flush('Bad Request', { status: 400, statusText: 'Bad Request' });
  });
});
