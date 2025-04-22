package com.myriamfournier.olympics_ticket_office.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myriamfournier.olympics_ticket_office.pojo.keysgenerations;

@Repository
public interface KeysgenerationRepository extends CrudRepository<keysgenerations, Long>{


    @Query("SELECT a FROM keysgenerations a") // JPA -> Java Persistence API
    List<keysgenerations> findAllKeysgenerations();

    boolean existsByKeyGenerated(String uniqueKey);


}
