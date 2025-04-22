package com.myriamfournier.olympics_ticket_office.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myriamfournier.olympics_ticket_office.pojo.carts;

@Repository
public interface CartRepository extends CrudRepository<carts, Long>{


    @Query("SELECT a FROM carts a") // JPA -> Java Persistence API
    List<carts> findAllCarts();


}
