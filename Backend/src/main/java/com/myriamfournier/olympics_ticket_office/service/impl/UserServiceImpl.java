package com.myriamfournier.olympics_ticket_office.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myriamfournier.olympics_ticket_office.pojo.users;
import com.myriamfournier.olympics_ticket_office.pojo.userskeys;
import com.myriamfournier.olympics_ticket_office.pojo.keysgenerations;
import com.myriamfournier.olympics_ticket_office.repository.UserRepository;
import com.myriamfournier.olympics_ticket_office.repository.UserskeyRepository;
import com.myriamfournier.olympics_ticket_office.repository.KeysgenerationRepository;
import com.myriamfournier.olympics_ticket_office.repository.PolicyRepository;
import com.myriamfournier.olympics_ticket_office.service.UserService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.myriamfournier.olympics_ticket_office.pojo.policies;
import com.myriamfournier.olympics_ticket_office.pojo.roles;

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
    private KeysgenerationRepository keysgenerationRepository; // Assuming you have a KeysgenerationsRepository interface

    @Autowired
    private UserskeyRepository userskeyRepository; // Assuming you have a UserskeysRepository interface

    @Autowired
    private PolicyRepository policyRepository; // Repository for policies
   

/* 
    @Override
    public users getUserByFirstName(String Firstname) {
        return userRepository.getFirstname(Firstname).orElse(null);
    }

    @Override
    public users getUserByLastName(String Lastname) {
        return userRepository.getLastname(Lastname).orElse(null);
    }

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
*/

@Override
public void createUser(users user) {
    System.out.println("Creating user: " + user);

    // Step 1: Generate a unique 256-character key
    String uniqueKey;
    keysgenerations keysEntity;
    do {
        uniqueKey = generateUnique256CharacterKey();
        System.out.println("Generated unique key: " + uniqueKey);

        // Step 2: Check if the key already exists in the Keysgenerations table
        keysEntity = keysgenerationRepository.findByKeyGenerated(uniqueKey);
    } while (keysEntity != null);

    // Step 3: Save the key in the Keysgenerations table
    keysEntity = new keysgenerations(uniqueKey);
    keysEntity.setKeyGenerated(uniqueKey);
    keysgenerationRepository.save(keysEntity);
    System.out.println("Saved key in Keysgenerations table: " + keysEntity);

    // Step 4: Create a new Userskeys object and link it to the generated key
    userskeys userskeys = new userskeys();
    userskeys.setKey(keysEntity); // Link the key to the Userskeys object
    userskeyRepository.save(userskeys);
    System.out.println("Linked key to Userskeys object: " + userskeys);

    // Step 5: Link the Userskeys object to the User object
    user.setUserskeys(userskeys);

    // Step 6: Set the policy_id for the user (assuming a default policy exists)
    if (user.getPolicies() != null) {
        policies policy = policyRepository.findById(user.getPolicies().getPolicy_id()).orElse(null);
        if (policy != null) {
            user.setPolicies(policy);
        } else {
            System.out.println("Policy not found for ID: " + user.getPolicies().getPolicy_id());
        }
    }

    // Step 7: Set the default role_id for the user
    roles defaultRole = new roles();
    defaultRole.setRole_id(Long.valueOf(3)); // Default role ID for USER as Long
    user.setRoles(defaultRole);

    userRepository.save(user);
    System.out.println("User saved successfully: " + user);
}


    private String generateUnique256CharacterKey() {
        String baseKey = generate256CharacterKey();
        String uniqueKey = baseKey;
        int counter = 1;
    
        // Check if the key already exists in the database
        while (keysgenerationRepository.existsByKeyGenerated(uniqueKey)) {
            uniqueKey = baseKey + "-" + counter;
            counter++;
        }
    
        return uniqueKey;
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
            // (EN) A record is immutable,
            // (EN) impossible to modify.
            // (EN) Therefore, we must recover the element, modify it
            // (EN) put it back in base.
            // (FR) Un enregistement est immuable
            // (FR) impossible à modifier
            // (FR) De ce fait, on doit recuperer l'element, le modifier
            // (FR) le remettre en base.
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

    @Override
    public List<users> getAllUsers() {
        return userRepository.findAllWithDetails();
    }

    @Override
    public List<users> getAllWithRoles() {
        return userRepository.findAllWithRoles();
    }

    @Override
    public List<users> getAllWithUserskeys() {
        return userRepository.findAllWithUserskeys();
    }

    @Override
    public List<users> getAllWithUserselections() {
        return userRepository.findAllWithUserselections();
    }

    @Override
    public List<users> getAllWithPolicies() {
        return userRepository.findAllWithPolicies();
    }

    @Override
    public List<users> getAllWithCountries() {
        return userRepository.findAllWithCountries();
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

    System.out.println("Generating unique username for: " + firstname + " " + lastname);

    // Check if the username already exists in the database
    while (userRepository.findByUsername(username) != null) {
        System.out.println("Username already exists: " + username);
        username = baseUsername + "-" + counter;
        counter++;
    }

    System.out.println("Generated unique username: " + username);
    return username;
}

    @Override
    public users getUserByUsername(String username) {
        users user = userRepository.findUserByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("User not found with username : " + username);
        }
        // Just return the user entity, not a UserDetails object
        return user;
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return userRepository.findUserByUsername(username) != null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        users user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

}
