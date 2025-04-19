package com.myriamfournier.olympics_ticket_office.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myriamfournier.olympics_ticket_office.pojo.challengers;

@Repository
public interface ChallengerRepository extends CrudRepository<challengers, Long>{


    @Query("SELECT a FROM challengers a") // JPA -> Java Persistence API
    List<challengers> findAllChallengers();


}
