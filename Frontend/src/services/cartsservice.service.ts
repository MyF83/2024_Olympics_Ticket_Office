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
  return this.http.get<CartsInterface[]>('http://localhost:8080/api/user/carts/user');
}
 

  addToCart(offer: Offersinterface) {
  let cart = JSON.parse(localStorage.getItem('guest_cart') || '[]');
  cart.push(offer);
  localStorage.setItem('guest_cart', JSON.stringify(cart));
    }
    mergeGuestCart(guestCart: Offersinterface[], userId: number) {
  return this.http.post(`/api/cart/merge`, { userId, items: guestCart });
}
}
