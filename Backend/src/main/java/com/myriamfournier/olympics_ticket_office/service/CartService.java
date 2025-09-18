package com.myriamfournier.olympics_ticket_office.service;

import java.util.List;

import com.myriamfournier.olympics_ticket_office.pojo.carts;
import com.myriamfournier.olympics_ticket_office.pojo.offers;
import com.myriamfournier.olympics_ticket_office.pojo.users;


public interface CartService{

    List<carts> getAllCarts();
    List<carts> getAllWithUsers();
    /* List<carts> getAllWithUserselections();*/

    carts getCartById(Long id);

    void createCart(carts carts);

    void updateCartById(carts carts, Long id);


    void deleteCartById(Long id);

    void mergeGuestCart(Long userId, List<offers> items);
    
    List<carts> findByUser(users user);




    


}
