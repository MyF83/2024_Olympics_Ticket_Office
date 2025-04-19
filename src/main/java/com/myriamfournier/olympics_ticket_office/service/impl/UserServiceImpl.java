package com.myriamfournier.olympics_ticket_office.service.impl;

import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myriamfournier.olympics_ticket_office.pojo.users;
import com.myriamfournier.olympics_ticket_office.pojo.userskeys;
import com.myriamfournier.olympics_ticket_office.pojo.keysgenerations;
import com.myriamfournier.olympics_ticket_office.repository.UserRepository;
import com.myriamfournier.olympics_ticket_office.repository.UserskeyRepository;
import com.myriamfournier.olympics_ticket_office.repository.KeysgenerationRepository;
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

    @Autowired
    private KeysgenerationRepository keysgenerationsRepository; // Assuming you have a KeysgenerationsRepository interface

    @Autowired
    private UserskeyRepository userskeysRepository; // Assuming you have a UserskeysRepository interface
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
public String generateUniqueUsername(String firstname, String lastname) {
    String baseUsername = lastname + "-" + firstname;
    String username = baseUsername;
    int counter = 1;

    // Check if the username already exists in the database
    while (userRepository.findByUsername(username) != null) {
        username = baseUsername + "-" + counter;
        counter++;
    }

    return username;
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
        // save the user to the database
        userRepository.save(users);

        // Generate a unique 256-character key
        String uniqueKey = generate256CharacterKey();

        // Save the key in the Keysgenerations table
        keysgenerations keysEntity = new keysgenerations(uniqueKey);
        keysEntity.setKeyGenerated(uniqueKey);
            keysgenerationsRepository.save(keysEntity);

            
        // Link the key to the user in the Userskeys table
        userskeys userskeys = new userskeys(null, users, keysEntity);
             userskeys.setUser(users);
            userskeys.setKey(keysEntity);
            userskeysRepository.save(userskeys);
    }

    private String generate256CharacterKey() {
        StringBuilder keyBuilder = new StringBuilder();
        while (keyBuilder.length() < 256) {
            keyBuilder.append(java.util.UUID.randomUUID().toString().replace("-", ""));
        }
        return keyBuilder.substring(0, 256); // Ensure the key is exactly 256 characters
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
