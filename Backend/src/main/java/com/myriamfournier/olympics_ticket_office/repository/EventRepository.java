package com.myriamfournier.olympics_ticket_office.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myriamfournier.olympics_ticket_office.pojo.events;

@Repository
public interface EventRepository extends CrudRepository<events, Long>{


    @Query("SELECT e FROM events e LEFT JOIN FETCH e.sports LEFT JOIN FETCH e.ceremonies LEFT JOIN FETCH e.challenger1 LEFT JOIN FETCH e.challenger2")
    List<events> findAllWithDetails();

    @Query("SELECT e FROM events e LEFT JOIN FETCH e.sports")
    List<events> findAllWithSports();
    
    @Query("SELECT e FROM events e LEFT JOIN FETCH e.ceremonies")
    List<events> findAllWithCeremonies();
    
    @Query("SELECT e FROM events e LEFT JOIN FETCH e.challenger1")
    List<events> findAllWithChallenger1();
    
    @Query("SELECT e FROM events e LEFT JOIN FETCH e.challenger2")
    List<events> findAllWithChallenger2();

    

}
