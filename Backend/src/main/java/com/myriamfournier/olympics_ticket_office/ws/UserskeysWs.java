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

import com.myriamfournier.olympics_ticket_office.pojo.userskeys;
import com.myriamfournier.olympics_ticket_office.service.UserskeyService;

@RequestMapping(ApiRegistration.API_REST
        + ApiRegistration.USERSKEY)
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserskeysWs {


    @Autowired
    private UserskeyService userskeysService;



// ///////////////////////////////////////////////////// //
//       ALL GET METHODS FOR USERSKEYS USERSKEYS        //
// /////////////////////////////////////////////////// //


    //GET method to retrieve all userskeys
    // Example: GET /api/usersekeys/all   
    @GetMapping
    public List<userskeys> getAllUserskeysDefault() {
        return userskeysService.getAllUserskeys(); // Assuming you have a userskeysService to fetch all userskeys
    }  

   

    @GetMapping("keys")
    public List<userskeys> getAllWithKeysgenerations() {
        return userskeysService.getAllWithKeysgenerations(); // Assuming you have a userskeysService to fetch all userskeys
    } 




    //GET method to retrieve a userskey by ID
    // Example: GET /api/userskeys/{id}
    @GetMapping("{id}")
    /*@ResponseBody  */     
    public userskeys getUserskeyById(@PathVariable("id") Long id) {
        return userskeysService.getUserskeyById(id); // Assuming you have a userskeysService to fetch userskey by ID
    }



// ////////////////////////////////////////////////// //
//       ALL POST METHODS FOR USERSKEYS ENTITY       //
// //////////////////////////////////////////////// //


    @PostMapping
    public void createUserskey(@RequestBody userskeys userskeys){
            userskeysService.createUserskey(userskeys);
    }



// ///////////////////////////////////////////////// //
//       ALL PUT METHODS FOR USERSKEYS ENTITY       //
// /////////////////////////////////////////////// //

    //PUT method to update an existing userskey
    // Example: PUT /api/userskey/update/{id}
    @PutMapping("{id}")
    public void updateUserskeyById(@PathVariable("id") Long id, @RequestBody userskeys userskeys) {
        userskeysService.updateUserskeyById(userskeys, id); // Assuming you have a userskeysService to update userskey by ID

    }



// ////////////////////////////////////////////////// //
//       ALL DELETE METHODS FOR USERSKEYS ENTITY     //
// //////////////////////////////////////////////// //

    @DeleteMapping("{id}")
    public void deleteUserskeyById(@PathVariable Long id){
        userskeysService.deleteUserskeyById(id);

    }


}
