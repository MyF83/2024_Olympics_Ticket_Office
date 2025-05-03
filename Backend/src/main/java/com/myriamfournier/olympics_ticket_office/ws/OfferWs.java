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

import com.myriamfournier.olympics_ticket_office.pojo.offers;
import com.myriamfournier.olympics_ticket_office.service.OfferService;

@RequestMapping(ApiRegistration.API_REST
        + ApiRegistration.OFFER)
@RestController
public class OfferWs {


    @Autowired
    private OfferService offerService;



// ///////////////////////////////////////////////// //
//       ALL GET METHODS FOR OFFER ENTITY            //
// /////////////////////////////////////////////// //


    //GET method to retrieve all offers
    // Example: GET /api/offer/all   
    // @GetMapping("all")
    @GetMapping
    /*@ResponseBody*/
    public List<offers> getAllOffers() {
        return offerService.getAllOffers(); // Assuming you have a offerService to fetch all offers
    }  


    //GET method to retrieve a offer by ID
    // Example: GET /api/offer/{id}
    @GetMapping("{id}")
    /*@ResponseBody  */     
    public offers getOfferById(@PathVariable("id") Long id) {
        return offerService.getOfferById(id); // Assuming you have a offerService to fetch offer by ID
    }



// ///////////////////////////////////////////////// //
//       ALL POST METHODS FOR OFFER ENTITY           //
// /////////////////////////////////////////////// //


    @PostMapping
    public void createOffer(@RequestBody offers offer){
            offerService.createOffer(offer);
    }



// ///////////////////////////////////////////////// //
//       ALL PUT METHODS FOR OFFER ENTITY           //
// /////////////////////////////////////////////// //

    //PUT method to update an existing offer
    // Example: PUT /api/offer/update/{id}
    @PutMapping("{id}")
    public void updateOfferById(@PathVariable("id") Long id, @RequestBody offers offer) {
        offerService.updateOfferById(offer, id); // Assuming you have a offerService to update offer by ID

    }



// ///////////////////////////////////////////////// //
//       ALL DELETE METHODS FOR OFFER ENTITY         //
// /////////////////////////////////////////////// //

    @DeleteMapping("{id}")
    public void deleteOfferById(@PathVariable Long id){
        offerService.deleteOfferById(id);

    }


}
