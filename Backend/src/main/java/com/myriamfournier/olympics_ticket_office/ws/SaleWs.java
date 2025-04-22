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

import com.myriamfournier.olympics_ticket_office.pojo.sales;
import com.myriamfournier.olympics_ticket_office.service.SaleService;

@RequestMapping(ApiRegistration.API_REST 
+ ApiRegistration.SALE)
@RestController
public class SaleWs {


    @Autowired
    private SaleService salesService;



// ///////////////////////////////////////////// //
//       ALL GET METHODS FOR SALE ENTITY        //
// /////////////////////////////////////////// //


    //GET method to retrieve all sales
    // Example: GET /api/sale/all   
    @GetMapping("all")
    /*@ResponseBody*/
    public List<sales> getAllSales() {
        return salesService.getAllSales(); // Assuming you have a salesService to fetch all sales
    }  


    //GET method to retrieve a sale by ID
    // Example: GET /api/sale/{id}
    @GetMapping("{id}")
    /*@ResponseBody  */     
    public sales getSaleById(@PathVariable("id") Long id) {
        return salesService.getSaleById(id); // Assuming you have a salesService to fetch sale by ID
    }



// ///////////////////////////////////////////// //
//       ALL POST METHODS FOR SALE ENTITY       //
// /////////////////////////////////////////// //


    @PostMapping
    public void createSale(@RequestBody sales sales){
            salesService.createSale(sales);
    }



// ///////////////////////////////////////////// //
//       ALL PUT METHODS FOR SALE ENTITY        //
// /////////////////////////////////////////// //

    //PUT method to update an existing sale
    // Example: PUT /api/sale/update/{id}
    @PutMapping("{id}")
    public void updateSaleById(@PathVariable("id") Long id, @RequestBody sales sales) {
        salesService.updateSaleById(sales, id); // Assuming you have a salesService to update sale by ID

    }



// ///////////////////////////////////////////// //
//       ALL DELETE METHODS FOR SALE ENTITY     //
// /////////////////////////////////////////// //

    @DeleteMapping("{id}")
    public void deleteSaleById(@PathVariable Long id){
        salesService.deleteSaleById(id);

    }


}
