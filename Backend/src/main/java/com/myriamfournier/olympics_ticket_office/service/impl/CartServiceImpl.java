package com.myriamfournier.olympics_ticket_office.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myriamfournier.olympics_ticket_office.pojo.carts;
import com.myriamfournier.olympics_ticket_office.repository.CartRepository;
import com.myriamfournier.olympics_ticket_office.service.CartService;

@Service
public class CartServiceImpl implements CartService{

@Autowired
    private CartRepository cartRepository; // Assuming you have a CartRepository interface


    // Implement the methods defined in CartService interface here
    
    @Override
    public List<carts> getAllCarts() {
        return cartRepository.findAllCarts();
    }


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

  
 

}
