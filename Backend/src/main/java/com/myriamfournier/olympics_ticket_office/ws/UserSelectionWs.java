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

import com.myriamfournier.olympics_ticket_office.pojo.userselections;
import com.myriamfournier.olympics_ticket_office.service.UserSelectionService;

@RequestMapping(ApiRegistration.API_REST
        + ApiRegistration.USERSELECTION)
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserSelectionWs {


    @Autowired
    private UserSelectionService userselectionService;



// ///////////////////////////////////////////////////// //
//       ALL GET METHODS FOR USERSELCTION ENTITY        //
// /////////////////////////////////////////////////// //


    //GET method to retrieve all userselections
    // Example: GET /api/userselections/all   
    @GetMapping
    public List<userselections> getAllUserSelectionsDefault() {
        return userselectionService.getAllUserSelections(); // Assuming you have a eventService to fetch all events
    }  

    @GetMapping("/offers")
    public List<userselections> getAllWithOffers() {
        return userselectionService.getAllWithOffers(); // Assuming you have a eventService to fetch all events
    }  

    @GetMapping("/events")
    public List<userselections> getAllWithEvents() {
        return userselectionService.getAllWithEvents(); // Assuming you have a eventService to fetch all events
    }  


    //GET method to retrieve a userselection by ID
    // Example: GET /api/userselection/{id}
    @GetMapping("{id}")
    /*@ResponseBody  */     
    public userselections getUserSelectionById(@PathVariable("id") Long id) {
        return userselectionService.getUserSelectionById(id); // Assuming you have a userselectionService to fetch userselection by ID
    }



// ////////////////////////////////////////////////////// //
//       ALL POST METHODS FOR USERSELECTION ENTITY       //
// //////////////////////////////////////////////////// //


    @PostMapping
    public void createUserSelection(@RequestBody userselections userselections){
        userselectionService.createUserSelection(userselections);
    }



// /////////////////////////////////////////////////////// //
//       ALL PUT METHODS FOR USERSELECTIONS ENTITY        //
// ///////////////////////////////////////////////////// //

    //PUT method to update an existing userselection
    // Example: PUT /api/userselection/update/{id}
    @PutMapping("{id}")
    public void updateUserSelectionById(@PathVariable("id") Long id, @RequestBody userselections userselections) {
        userselectionService.updateUserSelectionById(userselections, id); // Assuming you have a userselectionService to update userselection by ID

    }



// //////////////////////////////////////////////////////// //
//       ALL DELETE METHODS FOR USERSELECTIONS ENTITY      //
// ////////////////////////////////////////////////////// //

    @DeleteMapping("{id}")
    public void deleteUserSelectionById(@PathVariable Long id){
        userselectionService.deleteUserSelectionById(id);

    }


}
