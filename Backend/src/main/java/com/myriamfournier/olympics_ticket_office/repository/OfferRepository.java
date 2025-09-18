package com.myriamfournier.olympics_ticket_office.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myriamfournier.olympics_ticket_office.pojo.offers;

@Repository
public interface OfferRepository extends CrudRepository<offers, Long>{


    @Query("SELECT a FROM offers a") // JPA -> Java Persistence API
    List<offers> findAllOffers();


}
