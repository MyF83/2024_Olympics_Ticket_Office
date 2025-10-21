import { Injectable } from '@angular/core';
import { Offersinterface } from '../interfaces/offersinterface';
import { Observable } from 'rxjs';
import { CartsInterface } from '../interfaces/cartsinterface';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class CartsServiceService {
constructor(private http: HttpClient) { }

 getUserCarts(): Observable<CartsInterface[]> {
  return this.http.get<CartsInterface[]>('/api/user/carts/user');
}
 

  addToCart(offer: Offersinterface) {
  let cart = JSON.parse(localStorage.getItem('guest_cart') || '[]');
  cart.push(offer);
  localStorage.setItem('guest_cart', JSON.stringify(cart));
    }
    mergeGuestCart(guestCart: Offersinterface[], userId: number) {
  return this.http.post(`/api/cart/merge`, { userId, items: guestCart });
}

  // Add a method to create a cart for the authenticated user
  createCartForUser(ticketData: any): Observable<any> {
    console.log('Sending ticketData to backend:', ticketData); // Debug log
    console.log('Making POST request to: /api/user/carts');
    return this.http.post('/api/user/carts', ticketData);
  }

  // Add method to complete/update an existing cart
  completeCartSelection(cartId: number, cartData: any): Observable<any> {
    console.log('Completing cart selection for cart:', cartId, 'with data:', cartData);
    return this.http.put(`/api/user/carts/${cartId}/complete`, cartData);
  }

  // Get cart items for a specific user
  getCartItems(userId: number): Observable<any[]> {
    return this.http.get<any[]>(`/api/user/carts/${userId}`);
  }

  // Clear the cart for a specific user
  clearCart(userId: number): Observable<any> {
    return this.http.delete(`/api/user/carts/${userId}`);
  }

  // Delete a specific cart item
  deleteCart(cartId: number): Observable<any> {
    return this.http.delete(`/api/cart/${cartId}`);
  }
}
