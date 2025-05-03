package com.myriamfournier.olympics_ticket_office.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myriamfournier.olympics_ticket_office.pojo.policies;

@Repository
public interface PolicyRepository extends CrudRepository<policies, Long>{


    @Query("SELECT a FROM policies a") // JPA -> Java Persistence API
    List<policies> findAllPolicies();

    


}
