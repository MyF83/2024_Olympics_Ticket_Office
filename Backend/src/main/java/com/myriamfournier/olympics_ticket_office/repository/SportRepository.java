package com.myriamfournier.olympics_ticket_office.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myriamfournier.olympics_ticket_office.pojo.sports;

@Repository
public interface SportRepository extends CrudRepository<sports, Long>{

/* 
    @Query("SELECT a FROM sports a") // JPA -> Java Persistence API
    List<sports> findAllSports();*/

    @Query("SELECT e FROM sports e LEFT JOIN FETCH e.sites")
    List<sports> findAllWithDetails();

    @Query("SELECT e FROM sports e LEFT JOIN FETCH e.sites")
    List<sports> findAllWithSites();

   

}
