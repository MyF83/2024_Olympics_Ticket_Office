package com.myriamfournier.olympics_ticket_office.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myriamfournier.olympics_ticket_office.pojo.saleskeys;

@Repository
public interface SaleskeyRepository extends CrudRepository<saleskeys, Long>{

/* 
    @Query("SELECT a FROM saleskeys a") // JPA -> Java Persistence API
    List<saleskeys> findAllSaleskeys();
*/

    @Query("SELECT e FROM saleskeys e LEFT JOIN FETCH e.keysgenerations")
    List<saleskeys> findAllWithDetails();

    @Query("SELECT e FROM saleskeys e LEFT JOIN FETCH e.keysgenerations")
    List<saleskeys> findAllWithKeysgenerations();

    

}
