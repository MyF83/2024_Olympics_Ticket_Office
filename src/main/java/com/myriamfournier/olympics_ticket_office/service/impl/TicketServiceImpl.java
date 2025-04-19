package com.myriamfournier.olympics_ticket_office.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myriamfournier.olympics_ticket_office.pojo.tickets;
import com.myriamfournier.olympics_ticket_office.pojo.userskeys;
import com.myriamfournier.olympics_ticket_office.pojo.saleskeys;
import com.myriamfournier.olympics_ticket_office.repository.TicketRepository;
import com.myriamfournier.olympics_ticket_office.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService{

@Autowired
    private TicketRepository ticketRepository; // Assuming you have a TicketRepository interface


    // Implement the methods defined in TicketService interface here
    
    @Override
    public List<tickets> getAllTickets() {
        return ticketRepository.findAllTickets();
    }


    @Override
    public tickets getTicketById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }



    @Override
    public void createTicket(tickets tickets) {
        // Fetch the user key
        String userKey = ((userskeys) tickets.getUserskeys().getKey()).getKeyGenerated();

        // Fetch the sale key
        String saleKey = ((saleskeys) tickets.getSaleskeys().getKey()).getKeyGenerated();

        // Assemble the keys
        String keyAssembly = userKey + saleKey;

        // Set the assembled key in the ticket
        tickets.setKeyAssembly(keyAssembly);

        // Save the ticket to the database
        ticketRepository.save(tickets);
    }


    @Override
    public void updateTicketById(tickets tickets, Long id) {
            // un enregistement est immuable
            // impossible à modifier
            // de ce fait, on doit recuperer l'element, le modifier
            // le remettre
            tickets oldTicket = getTicketById(id);

        if(oldTicket != null){
            oldTicket.setDate(tickets.getDate());
            oldTicket.setKeyAssembly(tickets.getKeyAssembly());
            ticketRepository.save(oldTicket);
        }
    }




    @Override
    public void deleteTicketById(Long id) {
        ticketRepository.deleteById(id);
    }

  
 

}
