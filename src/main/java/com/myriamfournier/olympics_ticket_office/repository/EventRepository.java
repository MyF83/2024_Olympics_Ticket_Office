package com.myriamfournier.olympics_ticket_office.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myriamfournier.olympics_ticket_office.pojo.events;

@Repository
public interface EventRepository extends CrudRepository<events, Long>{


    @Query("SELECT a FROM events a") // JPA -> Java Persistence API
    List<events> findAllEvents();


}
