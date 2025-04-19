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

import com.myriamfournier.olympics_ticket_office.pojo.events;
import com.myriamfournier.olympics_ticket_office.service.EventService;

@RequestMapping(ApiRegistration.API_REST
        + ApiRegistration.EVENT)
@RestController
public class EventWs {


    @Autowired
    private EventService eventService;



// ///////////////////////////////////////////////// //
//       ALL GET METHODS FOR EVENT ENTITY            //
// /////////////////////////////////////////////// //


    //GET method to retrieve all events
    // Example: GET /api/event/all   
    @GetMapping("all")
    /*@ResponseBody*/
    public List<events> getAllEvents() {
        return eventService.getAllEvents(); // Assuming you have a eventService to fetch all events
    }  


    //GET method to retrieve a event by ID
    // Example: GET /api/event/{id}
    @GetMapping("{id}")
    /*@ResponseBody  */     
    public events getEventById(@PathVariable("id") Long id) {
        return eventService.getEventById(id); // Assuming you have a eventService to fetch event by ID
    }



// ///////////////////////////////////////////////// //
//       ALL POST METHODS FOR EVENT ENTITY           //
// /////////////////////////////////////////////// //


    @PostMapping
    public void createEvent(@RequestBody events event){
            eventService.createEvent(event);
    }



// ///////////////////////////////////////////////// //
//       ALL PUT METHODS FOR EVENT ENTITY           //
// /////////////////////////////////////////////// //

    //PUT method to update an existing event
    // Example: PUT /api/event/update/{id}
    @PutMapping("{id}")
    public void updateEventById(@PathVariable("id") Long id, @RequestBody events event) {
        eventService.updateEventById(event, id); // Assuming you have a eventService to update event by ID

    }



// ///////////////////////////////////////////////// //
//       ALL DELETE METHODS FOR EVENT ENTITY         //
// /////////////////////////////////////////////// //

    @DeleteMapping("{id}")
    public void deleteEventById(@PathVariable Long id){
        eventService.deleteEventById(id);

    }


}
