package com.myriamfournier.olympics_ticket_office.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myriamfournier.olympics_ticket_office.pojo.sites;

@Repository
public interface SiteRepository extends CrudRepository<sites, Long>{


    @Query("SELECT a FROM sites a") // JPA -> Java Persistence API
    List<sites> findAllSites();


}
