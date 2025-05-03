package com.myriamfournier.olympics_ticket_office.service;

import java.util.List;

import com.myriamfournier.olympics_ticket_office.pojo.users;


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
 /*    
    users getUserByFirstnameAndLastname(String firstname, String lastname);

    users getUserByFirstName(String firstname);

    users getUserByLastName(String lastname);
*/
    void createUser(users users);

    void updateUserById(users users, Long id);
/* 
    void updateUserFirstname(users users, String Firstname);

    void updateUserLastname(users users, String Lastname);
*/

    void deleteUserById(Long id);

   

    

    /* 
     default users getUserById(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
    }

     default users getUserByPassword(String password) {
        throw new UnsupportedOperationException("Unimplemented method 'getUserByPassword'");
    }
     default users getUserByRole(String role) {
        throw new UnsupportedOperationException("Unimplemented method 'getUserByRole'");
    }
     default users getUserByFirstnameAndLastname(String firstname, String lastname) {
        throw new UnsupportedOperationException("Unimplemented method 'getUserByFirstnameAndLastname'");
    }

     default users getUserByEmail(String email) {
        throw new UnsupportedOperationException("Unimplemented method 'getUserByEmail'");
    }
    
 */

    


}
