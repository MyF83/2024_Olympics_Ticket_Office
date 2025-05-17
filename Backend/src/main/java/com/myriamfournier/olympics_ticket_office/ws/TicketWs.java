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

import com.myriamfournier.olympics_ticket_office.pojo.tickets;
import com.myriamfournier.olympics_ticket_office.service.TicketService;

@RequestMapping(ApiRegistration.API_REST
        + ApiRegistration.TICKET)
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TicketWs {


    @Autowired
    private TicketService ticketService;



// /////////////////////////////////////////////// //
//       ALL GET METHODS FOR TICKET ENTITY        //
// ///////////////////////////////////////////// //


    //GET method to retrieve all tickets
    // Example: GET /api/ticket/all   
    @GetMapping
    public List<tickets> getAllTicketsDefault() {
        return ticketService.getAllTickets(); // Assuming you have a ticketService to fetch all tickets
    } 
    
    /* 
    @GetMapping("/userskeys")
    public List<tickets> getAllWithUserskeys() {
        return ticketService.getAllWithUserskeys(); // Assuming you have a ticketService to fetch all tickets
    }  

    @GetMapping("/saleskeys")
    public List<tickets> getAllWithSaleskeys() {
        return ticketService.getAllWithSaleskeys(); // Assuming you have a ticketService to fetch all tickets
    } */ 

    @GetMapping("/sales")
    public List<tickets> getAllWithSales() {
        return ticketService.getAllWithSales(); // Assuming you have a ticketService to fetch all tickets
    }  

    /* 
    @GetMapping("/carts")
    public List<tickets> getAllWithCarts() {
        return ticketService.getAllWithCarts(); // Assuming you have a ticketService to fetch all tickets
    }  

    @GetMapping("/users")
    public List<tickets> getAllWithUsers() {
        return ticketService.getAllWithUsers(); // Assuming you have a ticketService to fetch all tickets
    }  

    @GetMapping("/userselections")
    public List<tickets> getAllWithUserselections() {
        return ticketService.getAllWithUserselections(); // Assuming you have a ticketService to fetch all tickets
    } */ 


    //GET method to retrieve a ticket by ID
    // Example: GET /api/ticket/{id}
    @GetMapping("{id}")
    /*@ResponseBody  */     
    public tickets getTicketById(@PathVariable("id") Long id) {
        return ticketService.getTicketById(id); // Assuming you have a ticketService to fetch ticket by ID
    }



// /////////////////////////////////////////////// //
//       ALL POST METHODS FOR TICKET ENTITY       //
// ///////////////////////////////////////////// //


    @PostMapping
    public void createTicket(@RequestBody tickets tickets){
            ticketService.createTicket(tickets);
    }



// /////////////////////////////////////////////// //
//       ALL PUT METHODS FOR TICKET ENTITY        //
// ///////////////////////////////////////////// //

    //PUT method to update an existing ticket
    // Example: PUT /api/ticket/update/{id}
    @PutMapping("{id}")
    public void updateTicketById(@PathVariable("id") Long id, @RequestBody tickets tickets) {
        ticketService.updateTicketById(tickets, id); // Assuming you have a ticketService to update ticket by ID

    }



// /////////////////////////////////////////////// //
//       ALL DELETE METHODS FOR TICKET ENTITY     //
// ///////////////////////////////////////////// //

    @DeleteMapping("{id}")
    public void deleteTicketById(@PathVariable Long id){
        ticketService.deleteTicketById(id);

    }


}
