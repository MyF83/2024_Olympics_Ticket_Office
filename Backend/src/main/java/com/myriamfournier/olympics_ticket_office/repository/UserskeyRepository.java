package com.myriamfournier.olympics_ticket_office.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myriamfournier.olympics_ticket_office.pojo.userskeys;

@Repository
public interface UserskeyRepository extends CrudRepository<userskeys, Long>{

/* 
    @Query("SELECT a FROM userskeys a") // JPA -> Java Persistence API
    List<userskeys> findAllUserskeys();*/

    @Query("SELECT e FROM userskeys e  LEFT JOIN FETCH e.keysgenerations")
    List<userskeys> findAllWithDetails();


    
    @Query("SELECT e FROM userskeys e LEFT JOIN FETCH e.keysgenerations")
    List<userskeys> findAllWithKeysgenerations();

   

}
