package com.myriamfournier.olympics_ticket_office.service;

import java.util.List;

import com.myriamfournier.olympics_ticket_office.pojo.carts;

public interface CartService{

    List<carts> getAllCarts();

    carts getCartById(Long id);

    void createCart(carts carts);

    void updateCartById(carts carts, Long id);


    void deleteCartById(Long id);


    


}
