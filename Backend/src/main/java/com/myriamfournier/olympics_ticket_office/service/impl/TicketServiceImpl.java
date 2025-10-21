package com.myriamfournier.olympics_ticket_office.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myriamfournier.olympics_ticket_office.pojo.tickets;
import com.myriamfournier.olympics_ticket_office.repository.TicketRepository;
import com.myriamfournier.olympics_ticket_office.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<tickets> getAllTickets() {
        List<tickets> result = new ArrayList<>();
        ticketRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public List<tickets> getAllWithSales() {
        List<tickets> result = new ArrayList<>();
        ticketRepository.findAll().forEach(result::add);
        return result;
    }
    
    @Override
    public List<tickets> getAllWithDetails() {
        List<tickets> result = new ArrayList<>();
        ticketRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public List<tickets> getTicketsByUserId(Long userId) {
        return ticketRepository.findByUserId(userId);
    }
    
    @Override
    public List<tickets> getTicketsBySaleId(Long saleId) {
        return ticketRepository.findBySaleId(saleId);
    }

    @Override
    public tickets getTicketById(Long id) {
        Optional<tickets> ticket = ticketRepository.findById(id);
        return ticket.orElse(null);
    }

    @Override
    public tickets findByTicketNumber(String ticketNumber) {
        Optional<tickets> ticket = ticketRepository.findByTicketNumber(ticketNumber);
        return ticket.orElse(null);
    }

    @Override
    public void createTicket(tickets ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public Long saveCompleteTicket(tickets ticket, Long userId, Map<String, Object> ticketData) {
        // Note: tickets class doesn't have a setUser_id method, relationship is through sales
        tickets savedTicket = ticketRepository.save(ticket);
        return savedTicket.getTicket_id();
    }

    @Override
    public void updateTicketById(tickets ticket, Long id) {
        ticket.setTicket_id(id);
        ticketRepository.save(ticket);
    }
    
    @Override
    public void updateTicket(tickets ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicketById(Long id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public String generateUnique40CharacterName() {
        StringBuilder keyBuilder = new StringBuilder();
        while (keyBuilder.length() < 40) {
            keyBuilder.append(java.util.UUID.randomUUID().toString().replace("-", ""));
        }
        return keyBuilder.substring(0, 40);
    }
}