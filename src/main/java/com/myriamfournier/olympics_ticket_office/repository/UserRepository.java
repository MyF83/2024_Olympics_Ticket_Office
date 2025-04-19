package com.myriamfournier.olympics_ticket_office.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myriamfournier.olympics_ticket_office.pojo.users;


@Repository
public interface UserRepository extends CrudRepository<users, Long> {
    // This interface will automatically provide CRUD operations for the users entity
    // You can add custom query methods here if needed


    @Query("SELECT a FROM users a") // JPA -> Java Persistence API
    List<users> findAllUsers();

    @Query("SELECT a FROM users a WHERE a.firstname=?1 AND a.lastname=?2") // JPA -> Java Persistence API
    void findUserByFirstnameAndLastname(String firstname, String lastname);

    @Query("SELECT u FROM users u WHERE u.username=?1") // Custom query to find users by username
    users findUserByUsername(String username);

 /*  @Query("SELECT u from users u WHERE u.username=?1") // Custom query to find users by username
    users findUserByUsername(String username); // Custom query to find users by username
 
    @Query("SELECT u FROM users u WHERE u.username=?1 AND u.lastname=?2")
    List<users> getUserByFirstnameAndLastname(String firstname, String lastname); // Custom query to find users by firstname and lastname
 
    
@Query("SELECT u FROM users u")
List<users> findAll(); // Custom query to find all users

@Query("SELECT u FROM users u WHERE u.id=?1")
 Optional<users> findById(Long id); // Custom query to find users by ID


@Query("SELECT u FROM users u WHERE u.email=?1")
Optional<users> findByEmail(String email); // Custom query to find users by email

@Query("SELECT u FROM users u WHERE u.firstName=?1")
Optional<users> getFirstname(String firstname); // Custom query to find users by his firstname

@Query("SELECT u FROM users u WHERE u.lastName=?1")
Optional<users> getLastname(String lastname); // Custom query to find users by his lastname

@Query("SELECT u FROM users u WHERE u.username=?1 AND u.lastname=?2")
Optional<users> getUserByFirstnameAndLastname(String firstname, String lastname); // Custom query to find users by firstname and lastname

@Query("SELECT u FROM users u WHERE u.password=?1")
Optional<users> findByPassword(String password); // Custom query to find users by password

@Query("SELECT u FROM users u WHERE u.role=?1")
Optional<users> findByRole(String role); // Custom query to find users by role
*/


}
