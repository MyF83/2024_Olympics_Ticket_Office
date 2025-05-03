package com.myriamfournier.olympics_ticket_office.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myriamfournier.olympics_ticket_office.pojo.controls;

@Repository
public interface ControlRepository extends CrudRepository<controls, Long>{


    @Query("SELECT e FROM controls e LEFT JOIN FETCH e.tickets LEFT JOIN FETCH e.userskeys LEFT JOIN FETCH e.saleskeys")
    List<controls> findAllWithDetails();

    @Query("SELECT e FROM controls e LEFT JOIN FETCH e.tickets")
    List<controls> findAllWithTickets();
    
    @Query("SELECT e FROM controls e LEFT JOIN FETCH e.userskeys")
    List<controls> findAllWithUserskeys();
    
    @Query("SELECT e FROM controls e LEFT JOIN FETCH e.saleskeys")
    List<controls> findAllWithSaleskeys();
    
    

}
