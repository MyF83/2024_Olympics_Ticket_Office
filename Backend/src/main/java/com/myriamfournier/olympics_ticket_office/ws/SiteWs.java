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

import com.myriamfournier.olympics_ticket_office.pojo.sites;
import com.myriamfournier.olympics_ticket_office.service.SiteService;

@RequestMapping(ApiRegistration.API_REST
        + ApiRegistration.SITE)
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SiteWs {


    @Autowired
    private SiteService siteService;



// ///////////////////////////////////////////// //
//       ALL GET METHODS FOR SITE ENTITY        //
// /////////////////////////////////////////// //


    //GET method to retrieve all sites
    // Example: GET /api/site/all   
    @GetMapping
    public List<sites> getAllSitesDefault() {
        return siteService.getAllSites(); // Assuming you have a siteService to fetch all sites
    }  


    @GetMapping("/countries")
    public List<sites> getAllWithCountries() {
        return siteService.getAllWithCountries(); // Assuming you have a siteService to fetch all sites
    }  

    //GET method to retrieve a site by ID
    // Example: GET /api/site/{id}
    @GetMapping("{id}")
    /*@ResponseBody  */     
    public sites getSiteById(@PathVariable("id") Long id) {
        return siteService.getSiteById(id); // Assuming you have a siteService to fetch site by ID
    }



// ///////////////////////////////////////////// //
//       ALL POST METHODS FOR SITE ENTITY       //
// /////////////////////////////////////////// //


    @PostMapping
    public void createSite(@RequestBody sites sites){
            siteService.createSite(sites);
    }



// ///////////////////////////////////////////// //
//       ALL PUT METHODS FOR SITE ENTITY        //
// /////////////////////////////////////////// //

    //PUT method to update an existing site
    // Example: PUT /api/site/update/{id}
    @PutMapping("{id}")
    public void updateSiteById(@PathVariable("id") Long id, @RequestBody sites sites) {
       siteService.updateSiteById(sites, id); // Assuming you have a siteService to update site by ID

    }



// ///////////////////////////////////////////// //
//       ALL DELETE METHODS FOR SITE ENTITY     //
// /////////////////////////////////////////// //

    @DeleteMapping("{id}")
    public void deleteSiteById(@PathVariable Long id){
        siteService.deleteSiteById(id);

    }


}
