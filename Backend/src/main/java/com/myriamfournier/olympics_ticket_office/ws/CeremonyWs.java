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

import com.myriamfournier.olympics_ticket_office.pojo.ceremonies;
import com.myriamfournier.olympics_ticket_office.service.CeremonyService;

@RequestMapping(ApiRegistration.API_REST
        + ApiRegistration.CEREMONY)
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CeremonyWs {


    @Autowired
    private CeremonyService ceremonyService;



// ///////////////////////////////////////////////// //
//       ALL GET METHODS FOR CEREMONY ENTITY        //
// /////////////////////////////////////////////// //


    //GET method to retrieve all ceremonies
    // Example: GET /api/ceremony/all   
    @GetMapping
    public List<ceremonies> getAllCeremoniesDefault() {
        return ceremonyService.getAllCeremonies(); // Assuming you have a ceremonyService to fetch all ceremonies
    }  

    @GetMapping("/sites")
    public List<ceremonies> getAllWithSites() {
        return ceremonyService.getAllWithSites(); // Assuming you have a ceremonyService to fetch all ceremonies
    }  


    //GET method to retrieve a ceremony by ID
    // Example: GET /api/ceremony/{id}
    @GetMapping("{id}")
    /*@ResponseBody  */     
    public ceremonies getCeremonyById(@PathVariable("id") Long id) {
        return ceremonyService.getCeremonyById(id); // Assuming you have a ceremonyService to fetch event by ID
    }



// ///////////////////////////////////////////////// //
//       ALL POST METHODS FOR CEREMONY ENTITY       //
// /////////////////////////////////////////////// //


    @PostMapping
    public void createCeremony(@RequestBody ceremonies ceremonies){
            ceremonyService.createCeremony(ceremonies);
    }



// ///////////////////////////////////////////////// //
//       ALL PUT METHODS FOR CEREMONY ENTITY        //
// /////////////////////////////////////////////// //

    //PUT method to update an existing ceremony
    // Example: PUT /api/ceremony/update/{id}
    @PutMapping("{id}")
    public void updateCeremonyById(@PathVariable("id") Long id, @RequestBody ceremonies ceremonies) {
        ceremonyService.updateCeremonyById(ceremonies, id); // Assuming you have a ceremonyService to update ceremony by ID

    }



// ///////////////////////////////////////////////// //
//       ALL DELETE METHODS FOR CEREMONY ENTITY     //
// /////////////////////////////////////////////// //

    @DeleteMapping("{id}")
    public void deleteCeremonyById(@PathVariable Long id){
        ceremonyService.deleteCeremonyById(id);

    }


}
