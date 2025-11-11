package com.myriamfournier.olympics_ticket_office.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myriamfournier.olympics_ticket_office.pojo.carts;
import com.myriamfournier.olympics_ticket_office.repository.CartRepository;
import com.myriamfournier.olympics_ticket_office.service.CartService;
import com.myriamfournier.olympics_ticket_office.pojo.offers;

import com.myriamfournier.olympics_ticket_office.pojo.users;
import com.myriamfournier.olympics_ticket_office.repository.UserRepository;

@Service
public class CartServiceImpl implements CartService{

@Autowired
    private CartRepository cartRepository; // Assuming you have a CartRepository interface

@Autowired
private UserRepository userRepository;



    // Implement the methods defined in CartService interface here
    
    @Override
    public List<carts> getAllCarts() {
        return cartRepository.findAllWithDetails();
    }

    @Override
    public List<carts> getAllWithUsers() {
        return cartRepository.findAllWithUsers();
    }

    /* 
    @Override
    public List<carts> getAllWithUserselections() {
        return cartRepository.findAllWithUserselections();
    }*/


    @Override
    public carts getCartById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }



    @Override
    public void createCart(carts carts) {
        cartRepository.save(carts);
    }


    @Override
    public void updateCartById(carts carts, Long id) {
            // un enregistement est immuable
            // impossible à modifier
            // de ce fait, on doit recuperer l'element, le modifier
            // le remettre
            carts oldCart = getCartById(id);

        if(oldCart != null){
            oldCart.setDate(carts.getDate());
            oldCart.setTotalAmount(carts.getTotalAmount());
            cartRepository.save(oldCart);
        }
    }




    @Override
    public void deleteCartById(Long id) {
        cartRepository.deleteById(id);
    }

  
    @Override
    public void mergeGuestCart(Long userId, List<com.myriamfournier.olympics_ticket_office.pojo.offers> items) {
       // This method merges guest cart items into the user's cart
       // The actual offer/event data is stored in userselections linked to the user
       
       // Find or create user cart
        List<carts> allCarts = cartRepository.findAllWithDetails();
        carts userCart = null;
        for (carts cart : allCarts) {
            if (cart.getUsers() != null && cart.getUsers().getUser_id().equals(userId)) {
                userCart = cart;
                break;
            }
        }
        
        // If no cart exists, create one
        if (userCart == null) {
            userCart = new carts();
            users user = userRepository.findById(userId).orElse(null);
            userCart.setUser(user);
            userCart.setTotalAmount(0.0);
            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(System.currentTimeMillis());
            userCart.setDate(currentTimestamp);
            cartRepository.save(userCart);
        }
        
        // Update cart with merged items (simplified implementation)
        // The actual pricing logic would be handled elsewhere in the system
        if (items != null && !items.isEmpty()) {
            // Update the cart timestamp to reflect the merge operation
            userCart.setDate(new java.sql.Timestamp(System.currentTimeMillis()));
            cartRepository.save(userCart);
        }
    }

@Override
public List<carts> findByUser(users users) {
    return cartRepository.findByUsers(users);
}

}
