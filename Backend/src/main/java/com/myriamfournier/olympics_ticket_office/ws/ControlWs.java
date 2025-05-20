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

import com.myriamfournier.olympics_ticket_office.pojo.controls;
import com.myriamfournier.olympics_ticket_office.service.ControlService;

@RequestMapping(ApiRegistration.API_REST
        + ApiRegistration.CONTROL)
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ControlWs {


    @Autowired
    private ControlService controlService;



// /////////////////////////////////////////////////// //
//       ALL GET METHODS FOR CONTROL ENTITY           //
// ///////////////////////////////////////////////// //


    //GET method to retrieve all controls
    // Example: GET /api/control/all   
    @GetMapping
    public List<controls> getAllControlsDefault() {
        return controlService.getAllControls(); // Assuming you have a controlService to fetch all controls
    }  

    @GetMapping("/tickets")
    public List<controls> getAllWithTickets() {
        return controlService.getAllWithTickets(); // Assuming you have a controlService to fetch all controls
    }  
/* 
    @GetMapping("/userskeys")
    public List<controls> getAllWithUserskeys() {
        return controlService.getAllWithUserskeys(); // Assuming you have a controlService to fetch all controls
    }  

    @GetMapping("/saleskeys")
    public List<controls> getAllWithSaleskeys() {
        return controlService.getAllWithSaleskeys(); // Assuming you have a controlService to fetch all controls
    }  */


    //GET method to retrieve a control by ID
    // Example: GET /api/control/{id}
    @GetMapping("{id}")
    /*@ResponseBody  */     
    public controls getControlById(@PathVariable("id") Long id) {
        return controlService.getControlById(id); // Assuming you have a controlService to fetch control by ID
    }



// ///////////////////////////////////////////// //
//       ALL POST METHODS FOR CONTROL ENTITY       //
// /////////////////////////////////////////// //


    @PostMapping
    public void createControl(@RequestBody controls controls){
        controlService.createControl(controls);
    }



// /////////////////////////////////////////////////// //
//       ALL PUT METHODS FOR CONTROL ENTITY           //
// ///////////////////////////////////////////////// //

    //PUT method to update an existing control
    // Example: PUT /api/control/update/{id}
    @PutMapping("{id}")
    public void updateControlById(@PathVariable("id") Long id, @RequestBody controls controls) {
        controlService.updateControlById(controls, id); // Assuming you have a controlService to update control by ID

    }



// /////////////////////////////////////////////////// //
//       ALL DELETE METHODS FOR CONTROL ENTITY        //
// ///////////////////////////////////////////////// //

    @DeleteMapping("{id}")
    public void deleteControlById(@PathVariable Long id){
        controlService.deleteControlById(id);

    }


}
