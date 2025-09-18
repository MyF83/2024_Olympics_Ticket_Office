package com.myriamfournier.olympics_ticket_office.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myriamfournier.olympics_ticket_office.pojo.userselections;

@Repository
public interface UserSelectionRepository extends CrudRepository<userselections, Long>{

/* 
    @Query("SELECT a FROM userselections a") // JPA -> Java Persistence API
    List<userselections> findAllUserSelections();*/

    @Query("SELECT e FROM userselections e LEFT JOIN FETCH e.offers LEFT JOIN FETCH e.events")
    List<userselections> findAllWithDetails();

    @Query("SELECT e FROM userselections e LEFT JOIN FETCH e.offers")
    List<userselections> findAllWithOffers();
    
    @Query("SELECT e FROM userselections e LEFT JOIN FETCH e.events")
    List<userselections> findAllWithEvents();

   

}
