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
    return this.http.post('/api/user/carts', ticketData);
  }
}
