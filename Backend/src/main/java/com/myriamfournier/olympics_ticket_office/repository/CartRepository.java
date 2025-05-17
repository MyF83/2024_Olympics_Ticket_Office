package com.myriamfournier.olympics_ticket_office.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myriamfournier.olympics_ticket_office.pojo.carts;

@Repository
public interface CartRepository extends CrudRepository<carts, Long>{

    /* 
    @Query("SELECT a FROM carts a") // JPA -> Java Persistence API
    List<carts> findAllCarts();*/

    /* 
    @Query("SELECT e FROM carts e LEFT JOIN FETCH e.users LEFT JOIN FETCH e.userselections")
    List<carts> findAllWithDetails();

    @Query("SELECT e FROM carts e LEFT JOIN FETCH e.users")
    List<carts> findAllWithUsers();
    
    @Query("SELECT e FROM carts e LEFT JOIN FETCH e.userselections")
    List<carts> findAllWithUserselections();*/

    @Query("SELECT e FROM carts e LEFT JOIN FETCH e.users")
    List<carts> findAllWithDetails();

    @Query("SELECT e FROM carts e LEFT JOIN FETCH e.users")
    List<carts> findAllWithUsers();
    
  
 

}
