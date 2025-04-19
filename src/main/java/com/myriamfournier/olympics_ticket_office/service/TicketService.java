package com.myriamfournier.olympics_ticket_office.service;

import java.util.List;

import com.myriamfournier.olympics_ticket_office.pojo.tickets;

public interface TicketService{

    List<tickets> getAllTickets();

    tickets getTicketById(Long id);

    void createTicket(tickets tickets);

    void updateTicketById(tickets tickets, Long id);


    void deleteTicketById(Long id);


    


}
