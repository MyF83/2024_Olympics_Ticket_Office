package com.myriamfournier.olympics_ticket_office.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myriamfournier.olympics_ticket_office.pojo.tickets;

@Repository
public interface TicketRepository extends CrudRepository<tickets, Long>{


    @Query("SELECT a FROM tickets a") // JPA -> Java Persistence API
    List<tickets> findAllTickets();


}
