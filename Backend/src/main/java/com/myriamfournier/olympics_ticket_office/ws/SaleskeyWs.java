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

import com.myriamfournier.olympics_ticket_office.pojo.saleskeys;
import com.myriamfournier.olympics_ticket_office.service.SaleskeyService;

@RequestMapping(ApiRegistration.API_REST
        + ApiRegistration.SALESKEY)
@RestController
public class SaleskeyWs {


    @Autowired
    private SaleskeyService saleskeyService;



// ///////////////////////////////////////////////// //
//       ALL GET METHODS FOR SALESKEY ENTITY        //
// /////////////////////////////////////////////// //


    //GET method to retrieve all sales
    // Example: GET /api/sale/all   
    @GetMapping
    public List<saleskeys> getAllSaleskeysDefault() {
        return saleskeyService.getAllSaleskeys(); // Assuming you have a saleskeyService to fetch all saleskeys
    }  

    @GetMapping("/keysgenerations")
    public List<saleskeys> getAllWithKeysgenerations() {
        return saleskeyService.getAllWithKeysgenerations(); // Assuming you have a saleskeyService to fetch all saleskeys
    } 


    //GET method to retrieve a sale by ID
    // Example: GET /api/sale/{id}
    @GetMapping("{id}")
    /*@ResponseBody  */     
    public saleskeys getSaleskeyById(@PathVariable("id") Long id) {
        return saleskeyService.getSaleskeyById(id); // Assuming you have a saleskeyService to fetch saleskey by ID
    }



// ///////////////////////////////////////////////// //
//       ALL POST METHODS FOR SALESKEY ENTITY       //
// /////////////////////////////////////////////// //


    @PostMapping
    public void createSaleskey(@RequestBody saleskeys saleskeys){
            saleskeyService.createSaleskey(saleskeys);
    }



// ///////////////////////////////////////////////// //
//       ALL PUT METHODS FOR SALESKEY ENTITY        //
// /////////////////////////////////////////////// //

    //PUT method to update an existing saleskey
    // Example: PUT /api/saleskey/update/{id}
    @PutMapping("{id}")
    public void updateSaleskeyById(@PathVariable("id") Long id, @RequestBody saleskeys saleskeys) {
        saleskeyService.updateSaleskeyById(saleskeys, id); // Assuming you have a saleskeyService to update saleskey by ID

    }



// ///////////////////////////////////////////////// //
//       ALL DELETE METHODS FOR SALESKEY ENTITY     //
// /////////////////////////////////////////////// //

    @DeleteMapping("{id}")
    public void deleteSaleskeyById(@PathVariable Long id){
        saleskeyService.deleteSaleskeyById(id);

    }


}
