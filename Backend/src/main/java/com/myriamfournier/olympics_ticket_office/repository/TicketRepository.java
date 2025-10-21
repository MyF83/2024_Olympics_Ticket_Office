package com.myriamfournier.olympics_ticket_office.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myriamfournier.olympics_ticket_office.pojo.tickets;

@Repository
public interface TicketRepository extends CrudRepository<tickets, Long>{

    @Query("SELECT e FROM tickets e LEFT JOIN FETCH e.sales ")
    List<tickets> findAllWithDetails();
    
    @Query("SELECT e FROM tickets e LEFT JOIN FETCH e.sales")
    List<tickets> findAllWithSales();
    
    /**
     * Check if a ticket exists with the given file name
     * 
     * @param uniqueName The file name to check
     * @return true if a ticket exists with the file name
     */
    boolean existsByFileName(String uniqueName);
    
    /**
     * Find a ticket by its ticket number
     * 
     * @param ticketNumber The ticket number
     * @return Optional containing the ticket if found
     */
    Optional<tickets> findByTicketNumber(String ticketNumber);
    
    /**
     * Find all tickets for a specific user
     * 
     * @param userId The user ID
     * @return List of tickets for the user
     */
    @Query("SELECT t FROM tickets t JOIN t.sales s JOIN s.carts c JOIN c.users u WHERE u.user_id = :userId")
    List<tickets> findByUserId(@Param("userId") Long userId);

    /**
     * Find all tickets associated with a specific sale
     * 
     * @param saleId The sale ID
     * @return List of tickets for the sale
     */
    @Query("SELECT t FROM tickets t JOIN t.sales s WHERE s.sale_id = :saleId")
    List<tickets> findBySaleId(@Param("saleId") Long saleId);
}
