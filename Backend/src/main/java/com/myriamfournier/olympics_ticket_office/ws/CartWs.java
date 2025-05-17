package com.myriamfournier.olympics_ticket_office.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.myriamfournier.olympics_ticket_office.pojo.carts;
import com.myriamfournier.olympics_ticket_office.service.CartService;

@RequestMapping(ApiRegistration.API_REST
        + ApiRegistration.CART)
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CartWs {


    @Autowired
    private CartService cartService;



// ///////////////////////////////////////////// //
//       ALL GET METHODS FOR CART ENTITY        //
// /////////////////////////////////////////// //


    //GET method to retrieve all carts
    // Example: GET /api/cart/all   
    @GetMapping
    public List<carts> getAllCartsDefault() {
        return cartService.getAllCarts(); // Assuming you have a cartService to fetch all carts
    }  

    @GetMapping("/users")
    public List<carts> getAllWithUsers() {
        return cartService.getAllWithUsers(); // Assuming you have a cartService to fetch all carts
    }  
/* 
    @GetMapping("/userselections")
    public List<carts> getAllWithUserselections() {
        return cartService.getAllWithUserselections(); // Assuming you have a cartService to fetch all carts
    } */


    //GET method to retrieve a cart by ID
    // Example: GET /api/cart/{id}
    @GetMapping("{id}")
    /*@ResponseBody  */     
    public carts getCartById(@PathVariable("id") Long id) {
        return cartService.getCartById(id); // Assuming you have a cartService to fetch event by ID
    }



// ///////////////////////////////////////////// //
//       ALL POST METHODS FOR CART ENTITY       //
// /////////////////////////////////////////// //


    @PostMapping
    public void createCart(@RequestBody carts carts){
            cartService.createCart(carts);
    }



// ///////////////////////////////////////////// //
//       ALL PUT METHODS FOR CART ENTITY        //
// /////////////////////////////////////////// //

    //PUT method to update an existing cart
    // Example: PUT /api/cart/update/{id}
    @PutMapping("{id}")
    public void updateCartById(@PathVariable("id") Long id, @RequestBody carts carts) {
        cartService.updateCartById(carts, id); // Assuming you have a cartService to update cart by ID

    }



// ///////////////////////////////////////////// //
//       ALL DELETE METHODS FOR CART ENTITY     //
// /////////////////////////////////////////// //

    @DeleteMapping("{id}")
    public void deleteCartById(@PathVariable Long id){
        cartService.deleteCartById(id);

    }


}
