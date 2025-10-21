package com.myriamfournier.olympics_ticket_office.service;

import java.util.List;
import java.util.Map;

import com.myriamfournier.olympics_ticket_office.pojo.tickets;


public interface TicketService{

    List<tickets> getAllTickets();
    // List<tickets> getAllWithUserskeys();
    // List<tickets> getAllWithSaleskeys();
    List<tickets> getAllWithSales();
    // List<tickets> getAllWithCarts();
    // List<tickets> getAllWithUsers();
    // List<tickets> getAllWithUserselections();
    
    List<tickets> getTicketsByUserId(Long userId);
    
    List<tickets> getTicketsBySaleId(Long saleId);
    
    List<tickets> getAllWithDetails();

    tickets getTicketById(Long id);
    
    tickets findByTicketNumber(String ticketNumber);

    void createTicket(tickets tickets);
    
    /**
     * Save a complete ticket with related entities (sale, cart, etc.)
     * @param ticket The ticket to save
     * @param userId The ID of the user associated with the ticket
     * @param ticketData Additional data for the ticket (from frontend)
     * @return The ID of the saved ticket
     */
    Long saveCompleteTicket(tickets ticket, Long userId, Map<String, Object> ticketData);

    void updateTicketById(tickets tickets, Long id);
    
    void updateTicket(tickets tickets);


    void deleteTicketById(Long id);

    String generateUnique40CharacterName();





}
