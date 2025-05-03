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

import com.myriamfournier.olympics_ticket_office.pojo.challengers;
import com.myriamfournier.olympics_ticket_office.service.ChallengerService;

@RequestMapping(ApiRegistration.API_REST
        + ApiRegistration.CHALLENGER)
@RestController
public class ChallengerWs {


    @Autowired
    private ChallengerService challengerService;



// /////////////////////////////////////////////////// //
//       ALL GET METHODS FOR CHALLENGER ENTITY        //
// ///////////////////////////////////////////////// //


    //GET method to retrieve all challengers
    // Example: GET /api/challenger/all   
    @GetMapping
    public List<challengers> getAllChallengersDefault() {
        return challengerService.getAllChallengers(); // Assuming you have a challengerService to fetch all challengers
    }  

    @GetMapping("/countries")
    public List<challengers> getAllWithCountries() {
        return challengerService.getAllWithCountries(); // Assuming you have a challengerService to fetch all challengers
    }  


    //GET method to retrieve a challenger by ID
    // Example: GET /api/challenger/{id}
    @GetMapping("{id}")
    /*@ResponseBody  */     
    public challengers getChallengerById(@PathVariable("id") Long id) {
        return challengerService.getChallengerById(id); // Assuming you have a cartService to fetch challenger by ID
    }



// ///////////////////////////////////////////// //
//       ALL POST METHODS FOR CART ENTITY       //
// /////////////////////////////////////////// //


    @PostMapping
    public void createChallenger(@RequestBody challengers challengers){
        challengerService.createChallenger(challengers);
    }



// /////////////////////////////////////////////////// //
//       ALL PUT METHODS FOR CHALLENGER ENTITY        //
// ///////////////////////////////////////////////// //

    //PUT method to update an existing challenger
    // Example: PUT /api/challenger/update/{id}
    @PutMapping("{id}")
    public void updateChallengerById(@PathVariable("id") Long id, @RequestBody challengers challengers) {
        challengerService.updateChallengerById(challengers, id); // Assuming you have a cartService to update challenger by ID

    }



// /////////////////////////////////////////////////// //
//       ALL DELETE METHODS FOR CHALLENGER ENTITY     //
// ///////////////////////////////////////////////// //

    @DeleteMapping("{id}")
    public void deleteChallengerById(@PathVariable Long id){
        challengerService.deleteChallengerById(id);

    }


}
