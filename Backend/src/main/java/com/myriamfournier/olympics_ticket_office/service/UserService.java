package com.myriamfournier.olympics_ticket_office.service;

import java.util.List;

import com.myriamfournier.olympics_ticket_office.pojo.users;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    List<users> getAllUsers();
    List<users> getAllWithRoles();
    List<users> getAllWithUserskeys();
    List<users> getAllWithUserselections();
    List<users> getAllWithPolicies();
    List<users> getAllWithCountries();

    users getUserById(Long id);

    String generateUniqueUsername(String firstname, String lastname);

    users getUserByUsername(String username);

    users getUserByFirstnameAndLastname(String firstname, String lastname);

    void createUser(users users);

    users updateUserById(users users, Long id);

    void deleteUserById(Long id);

    boolean isUsernameTaken(String username);

    UserDetails loadUserByUsername(String username);
}
