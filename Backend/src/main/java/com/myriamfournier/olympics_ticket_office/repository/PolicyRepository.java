package com.myriamfournier.olympics_ticket_office.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myriamfournier.olympics_ticket_office.pojo.policies;

@Repository
public interface PolicyRepository extends CrudRepository<policies, Long>{


    @Query("SELECT a FROM policies a") // JPA -> Java Persistence API
    List<policies> findAllPolicies();

    @Query("SELECT p FROM policies p WHERE p.policy_id = :id ORDER BY p.version DESC")
    policies findLastVersionById(@Param("id") Long id);
    
    @Query("SELECT p FROM policies p ORDER BY p.version DESC")
    List<policies> findAllOrderByVersionDesc();

    @Query("SELECT p FROM policies p WHERE p.isActive = true")
    policies findActivePolicy();

    @Modifying
    @Transactional
    @Query("UPDATE policies p SET p.isActive = false")
    void updateAllSetIsActiveFalse();

    @Query("SELECT p FROM policies p ORDER BY p.version DESC")
    policies findLastVersion();



}
