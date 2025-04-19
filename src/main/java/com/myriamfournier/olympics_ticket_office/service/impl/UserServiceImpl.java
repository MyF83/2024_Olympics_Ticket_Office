package com.myriamfournier.olympics_ticket_office.service.impl;

import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myriamfournier.olympics_ticket_office.pojo.users;
import com.myriamfournier.olympics_ticket_office.repository.UserRepository;
import com.myriamfournier.olympics_ticket_office.service.UserService;


@Service
public class UserServiceImpl implements UserService {
    // Implement the methods defined in UserService interface here
    // For example:
    // @Override
    // public User createUser(User user) {
    //     // Implementation code here
    //     return null;
    // }

    
    @Autowired
    private UserRepository userRepository; // Assuming you have a UserRepository interface


    // Implement the methods defined in UserService interface here
    
    @Override
    public List<users> getAllUsers() {
        return userRepository.findAllUsers();
    }


    @Override
    public users getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public users getUserByUsername(String username) {
        users user = userRepository.findUserByUsername(username);
        return user != null ? user : null; // Handle null check properly
    }
/* 
    @Override
    public users getUserByFirstName(String Firstname) {
        return userRepository.getFirstname(Firstname).orElse(null);
    }

    @Override
    public users getUserByLastName(String Lastname) {
        return userRepository.getLastname(Lastname).orElse(null);
    }
*/


    @Override
    public void createUser(users users) {
        userRepository.save(users);
    }


    @Override
    public void updateUserById(users users, Long id) {
            // un enregistement est immuable
            // impossible à modifier
            // de ce fait, on doit recuperer l'element, le modifier
            // le remettre
        users oldUser = getUserById(id);

        if(oldUser != null){
            oldUser.setFirstname(users.getFirstname());
            oldUser.setLastname(users.getLastname());
            oldUser.setUsername(users.getUsername());
            oldUser.setEmail(users.getEmail());
            oldUser.setPassword(users.getPassword());
            oldUser.setAddress(users.getAddress());
            oldUser.setCity(users.getCity());
            oldUser.setPostalCode(users.getPostalCode());
            userRepository.save(oldUser);
        }
    }




    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public users getUserByFirstnameAndLastname(String firstname, String lastname) {
        userRepository.findUserByFirstnameAndLastname(firstname, lastname);
        throw new UnsupportedOperationException("Unimplemented method 'getUserByFirstnameAndLastname'");
    }

  /* 
    @Override
    public List<users> getAllUsers() {
        return (List<users>) userRepository.findAll(); // Assuming you have a method to get all users
    }

    @Override
    public users getUserById(Long id) {
        return (users) userRepository.findById(id).orElse(null); // Assuming you have a method to get user by ID
    }

    @Override
    public users getUserByPassword(String password) {
        return userRepository.findByPassword(password).orElse(null); // Handle Optional properly
    }

    @Override
    public users getUserByRole(String role) {
        return userRepository.findByRole(role).orElse(null); // Handle Optional properly
    }   

    @Override
    public users getUserByFirstnameAndLastname(String firstname, String lastname) {
        return userRepository.getUserByFirstnameAndLastname(firstname, lastname).orElse(null); // Handle Optional properly
    }

    @Override
    public users getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null); // Handle Optional properly
    }
    */
}
