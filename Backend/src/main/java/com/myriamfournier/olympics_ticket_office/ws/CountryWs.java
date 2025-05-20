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

import com.myriamfournier.olympics_ticket_office.pojo.countries;
import com.myriamfournier.olympics_ticket_office.service.CountryService;

@RequestMapping(ApiRegistration.API_REST
        + ApiRegistration.COUNTRY)
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CountryWs {


    @Autowired
    private CountryService countryService;



// //////////////////////////////////////////////// //
//       ALL GET METHODS FOR COUNTRY ENTITY        //
// ////////////////////////////////////////////// //


    //GET method to retrieve all countries
    // Example: GET /api/country/all   
    //@GetMapping("all")
    @GetMapping  
    //*@ResponseBody*/
    public List<countries> getAllCountries() {
        return countryService.getAllCountries(); // Assuming you have a countryService to fetch all countries
    }  


    //GET method to retrieve a country by ID
    // Example: GET /api/country/{id}
    @GetMapping("{id}")
    /*@ResponseBody  */     
    public countries getCountryById(@PathVariable("id") Long id) {
        return countryService.getCountryById(id); // Assuming you have a countryService to fetch country by ID
    }



// //////////////////////////////////////////////// //
//       ALL POST METHODS FOR COUNTRY ENTITY       //
// ////////////////////////////////////////////// //


    @PostMapping
    public void createCountry(@RequestBody countries countries){
            countryService.createCountry(countries);
    }



// //////////////////////////////////////////////// //
//       ALL PUT METHODS FOR COUNTRY ENTITY        //
// ////////////////////////////////////////////// //

    //PUT method to update an existing country
    // Example: PUT /api/cart/country/{id}
    @PutMapping("{id}")
    public void updateCountryById(@PathVariable("id") Long id, @RequestBody countries countries) {
        countryService.updateCountryById(countries, id); // Assuming you have a countryService to update country by ID

    }



// //////////////////////////////////////////////// //
//       ALL DELETE METHODS FOR COUNTRY ENTITY     //
// ////////////////////////////////////////////// //

    @DeleteMapping("{id}")
    public void deleteCountryById(@PathVariable Long id){
        countryService.deleteCountryById(id);

    }


}
