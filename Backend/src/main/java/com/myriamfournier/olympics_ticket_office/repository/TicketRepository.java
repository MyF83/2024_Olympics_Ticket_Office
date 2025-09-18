package com.myriamfournier.olympics_ticket_office.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myriamfournier.olympics_ticket_office.pojo.tickets;

@Repository
public interface TicketRepository extends CrudRepository<tickets, Long>{

/* 
    @Query("SELECT a FROM tickets a") // JPA -> Java Persistence API
    List<tickets> findAllTickets();*/

   /*  @Query("SELECT e FROM tickets e LEFT JOIN FETCH e.userskeys LEFT JOIN FETCH e.saleskeys LEFT JOIN FETCH e.sales LEFT JOIN FETCH e.carts LEFT JOIN FETCH e.users LEFT JOIN FETCH e.userselections")
    List<tickets> findAllWithDetails();*/

    @Query("SELECT e FROM tickets e  LEFT JOIN FETCH e.sales ")
    List<tickets> findAllWithDetails();

    // @Query("SELECT e FROM tickets e LEFT JOIN FETCH e.userskeys")
    // List<tickets> findAllWithUserskeys();
    
    // @Query("SELECT e FROM tickets e LEFT JOIN FETCH e.saleskeys")
    // List<tickets> findAllWithSaleskeys();
    
    @Query("SELECT e FROM tickets e LEFT JOIN FETCH e.sales")
    List<tickets> findAllWithSales();
    
   // @Query("SELECT e FROM tickets e LEFT JOIN FETCH e.carts")
    // List<tickets> findAllWithCarts();

    // @Query("SELECT e FROM tickets e LEFT JOIN FETCH e.users")
    // List<tickets> findAllWithUsers();

    // @Query("SELECT e FROM tickets e LEFT JOIN FETCH e.userselections")
    // List<tickets> findAllWithUserselections();

    

    boolean existsByFileName(String uniqueName);


}
