package com.myriamfournier.olympics_ticket_office.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myriamfournier.olympics_ticket_office.pojo.countries;

@Repository
public interface CountryRepository extends CrudRepository<countries, Long>{


    @Query("SELECT a FROM countries a") // JPA -> Java Persistence API
    List<countries> findAllCountries();

    Object findByIdWithDetails(Object any);

    


}
