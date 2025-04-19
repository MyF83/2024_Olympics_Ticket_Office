package com.myriamfournier.olympics_ticket_office.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myriamfournier.olympics_ticket_office.pojo.ceremonies;

@Repository
public interface CeremonyRepository extends CrudRepository<ceremonies, Long>{


    @Query("SELECT a FROM ceremonies a") // JPA -> Java Persistence API
    List<ceremonies> findAllCeremonies();


}
