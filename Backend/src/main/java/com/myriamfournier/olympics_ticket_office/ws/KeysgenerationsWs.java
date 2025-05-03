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

import com.myriamfournier.olympics_ticket_office.pojo.keysgenerations;
import com.myriamfournier.olympics_ticket_office.service.KeysgenerationService;

@RequestMapping(ApiRegistration.API_REST
        + ApiRegistration.KEYSGENERATION)
@RestController
public class KeysgenerationsWs {
    public static final String API_REST = "/api";
    public static final String KEYSGENERATIONS = "/keysgenerations";

    @Autowired
    private KeysgenerationService keysgenerationService;



// //////////////////////////////////////////////////////// //
//       ALL GET METHODS FOR KEYSGENERATIONS ENTITY        //
// ////////////////////////////////////////////////////// //


    //GET method to retrieve all keysgenerations
    // Example: GET /api/keysgenerations/all   
    //@GetMapping("all")
    @GetMapping
    /*@ResponseBody*/
    public List<keysgenerations> getAllCarts() {
        return keysgenerationService.getAllKeysgenerations(); // Assuming you have a keysgenerationService to fetch all keysgenerations
    }  


    //GET method to retrieve a cart by ID
    // Example: GET /api/cart/{id}
    @GetMapping("{id}")
    /*@ResponseBody  */     
    public keysgenerations getKeysgenerationById(@PathVariable("id") Long id) {
        return keysgenerationService.getKeysgenerationById(id); // Assuming you have a keysgenerationService to fetch keysgeneration by ID
    }



// //////////////////////////////////////////////////////// //
//       ALL POST METHODS FOR KEYSGENERATIONS ENTITY       //
// ////////////////////////////////////////////////////// //


    @PostMapping
    public void createKeysgeneration(@RequestBody keysgenerations keysgenerations){
        keysgenerationService.createKeysgeneration(keysgenerations);
    }



// //////////////////////////////////////////////////////// //
//       ALL PUT METHODS FOR KEYSGENERATIONS ENTITY        //
// ////////////////////////////////////////////////////// //

    //PUT method to update an existing cart
    // Example: PUT /api/cart/update/{id}
    @PutMapping("{id}")
    public void updateKeysgenerationById(@PathVariable("id") Long id, @RequestBody keysgenerations keysgenerations) {
        keysgenerationService.updateKeysgenerationById(keysgenerations, id); // Assuming you have a keysgenerationsService to update keysgeneration by ID

    }



// //////////////////////////////////////////////////////// //
//       ALL DELETE METHODS FOR KEYSGENERATIONS ENTITY     //
// ////////////////////////////////////////////////////// //

    @DeleteMapping("{id}")
    public void deleteKeysgenerationById(@PathVariable Long id){
        keysgenerationService.deleteKeysgenerationById(id);

    }


}
