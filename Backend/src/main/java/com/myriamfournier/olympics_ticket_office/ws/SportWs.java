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

import com.myriamfournier.olympics_ticket_office.pojo.sports;
import com.myriamfournier.olympics_ticket_office.service.SportService;

@RequestMapping(ApiRegistration.API_REST
        + ApiRegistration.SPORT)
@RestController
public class SportWs {


    @Autowired
    private SportService sportService;



// ////////////////////////////////////////////// //
//       ALL GET METHODS FOR SPORT ENTITY        //
// //////////////////////////////////////////// //


    //GET method to retrieve all sports
    // Example: GET /api/sport/all   
    @GetMapping
    public List<sports> getAllSportsDefault() {
        return sportService.getAllSports(); // Assuming you have a sportService to fetch all sports
    }  

    @GetMapping("/sites")
    public List<sports> getAllWithSites() {
        return sportService.getAllWithSites(); // Assuming you have a sportService to fetch all sports
    }  


    //GET method to retrieve a sport by ID
    // Example: GET /api/sport/{id}
    @GetMapping("{id}")
    /*@ResponseBody  */     
    public sports getSportById(@PathVariable("id") Long id) {
        return sportService.getSportById(id); // Assuming you have a sportService to fetch sport by ID
    }



// ////////////////////////////////////////////// //
//       ALL POST METHODS FOR SPORT ENTITY       //
// //////////////////////////////////////////// //


    @PostMapping
    public void createSport(@RequestBody sports sports){
            sportService.createSport(sports);
    }



// ////////////////////////////////////////////// //
//       ALL PUT METHODS FOR SPORT ENTITY        //
// //////////////////////////////////////////// //

    //PUT method to update an existing sport
    // Example: PUT /api/sport/update/{id}
    @PutMapping("{id}")
    public void updateSportById(@PathVariable("id") Long id, @RequestBody sports sports) {
        sportService.updateSportById(sports, id); // Assuming you have a sportService to update sport by ID

    }



// ////////////////////////////////////////////// //
//       ALL DELETE METHODS FOR SPORT ENTITY     //
// //////////////////////////////////////////// //

    @DeleteMapping("{id}")
    public void deleteSportById(@PathVariable Long id){
        sportService.deleteSportById(id);

    }

    /* 
    @GetMapping("/refresh/{id}")
    public sports refreshSportById(@PathVariable("id") Long id) {
        return sportService.getFreshSport(id); // Call the service method to refresh the entity
    }*/

}
