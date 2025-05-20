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
    public void mergeGuestCart(Long userId, List<offers> items) {
       // 1. Find the user's cart (assuming one active cart per user)
        List<carts> allCarts = cartRepository.findAllWithDetails();
        carts userCart = null;
            for (carts carts : allCarts) {
            if (carts.getUsers() != null && carts.getUsers().getUser_id().equals(userId)) {
                userCart = carts;
                break;
            }
    }
        // 2. If no cart exists, create one
        if (userCart == null) {
            userCart = new carts();
            users users = userRepository.findById(userId).orElse(null); // Assuming you have a UserRepository
            userCart.setUser(users); // or setUsers(users) if you have a users entity
            userCart.setOffers(new ArrayList<>());
            userCart.setTotalAmount(0.0);
        }

           // 3. Add items from guest cart (avoid duplicates)
            List<offers> currentOffers = userCart.getOffers() != null ? userCart.getOffers() : new ArrayList<>();
            for (offers guestOffer : items) {
                boolean alreadyInCart = currentOffers.stream()
                    .anyMatch(o -> o.getOffer_id().equals(guestOffer.getOffer_id()));
                if (!alreadyInCart) {
                    currentOffers.add(guestOffer);
                    // Optionally update totalAmount here
                }
            }
            userCart.setOffers(currentOffers);

            // 4. Save the cart
            cartRepository.save(userCart);
    }

@Override
public List<carts> findByUser(users users) {
    return cartRepository.findByUsers(users);
}

}
