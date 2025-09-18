package com.myriamfournier.olympics_ticket_office.service;

import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import com.myriamfournier.olympics_ticket_office.Application;
import com.myriamfournier.olympics_ticket_office.pojo.tickets;
import com.myriamfournier.olympics_ticket_office.service.impl.TicketServiceImpl;

public interface TicketService{

    List<tickets> getAllTickets();
    // List<tickets> getAllWithUserskeys();
    // List<tickets> getAllWithSaleskeys();
    List<tickets> getAllWithSales();
    // List<tickets> getAllWithCarts();
    // List<tickets> getAllWithUsers();
    // List<tickets> getAllWithUserselections();

    tickets getTicketById(Long id);

    void createTicket(tickets tickets);

    void updateTicketById(tickets tickets, Long id);


    void deleteTicketById(Long id);

    String generateUnique40CharacterName();


    public static void main(String[] args) {
        // Start the Spring Boot application context
        ApplicationContext context = SpringApplication.run(Application.class, args);
    
        // Get the TicketService bean from the application context
        TicketService ticketService = context.getBean(TicketService.class);
    
        // Generate a unique 40-character name
        String name = ticketService.generateUnique40CharacterName();
 // QR code data
    String data = "Contente, ça marche !";

    // Generate the QR code
    ((TicketServiceImpl) ticketService).generateQRCode(data, name);
}           


}
