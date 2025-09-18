package com.myriamfournier.olympics_ticket_office.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myriamfournier.olympics_ticket_office.pojo.sales;

@Repository
public interface SaleRepository extends CrudRepository<sales, Long>{

/* 
    @Query("SELECT a FROM sales a") // JPA -> Java Persistence API
    List<sales> findAllSales();*/

    @Query("SELECT e FROM sales e LEFT JOIN FETCH e.carts LEFT JOIN FETCH e.saleskeys")
    List<sales> findAllWithDetails();

   // @Query("SELECT e FROM sales e LEFT JOIN FETCH e.users")
    // List<sales> findAllWithUsers();

    @Query("SELECT e FROM sales e LEFT JOIN FETCH e.carts")
    List<sales> findAllWithCarts();

    @Query("SELECT e FROM sales e LEFT JOIN FETCH e.saleskeys")
    List<sales> findAllWithSaleskeys();

    



}
