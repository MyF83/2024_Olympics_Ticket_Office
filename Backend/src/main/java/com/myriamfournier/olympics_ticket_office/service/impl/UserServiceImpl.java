package com.myriamfournier.olympics_ticket_office.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myriamfournier.olympics_ticket_office.pojo.users;
import com.myriamfournier.olympics_ticket_office.pojo.userskeys;
import com.myriamfournier.olympics_ticket_office.pojo.countries;
import com.myriamfournier.olympics_ticket_office.pojo.keysgenerations;
import com.myriamfournier.olympics_ticket_office.repository.UserRepository;
import com.myriamfournier.olympics_ticket_office.repository.UserskeyRepository;
import com.myriamfournier.olympics_ticket_office.repository.CountryRepository;
import com.myriamfournier.olympics_ticket_office.repository.KeysgenerationRepository;
import com.myriamfournier.olympics_ticket_office.repository.PolicyRepository;
import com.myriamfournier.olympics_ticket_office.service.UserService;
import com.myriamfournier.olympics_ticket_office.service.UserskeyService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

import com.myriamfournier.olympics_ticket_office.pojo.policies;
import com.myriamfournier.olympics_ticket_office.pojo.roles;

@Service
public class UserServiceImpl implements UserService {
    // Implement the methods defined in UserService interface here
    // For example:
    // @Override
    // public Users createUser(Users users) {
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
   
    @Autowired
    private CountryRepository countryRepository; // Repository for countries
    
    @Autowired
    private UserskeyService userskeyService; // Service for user key generation
    
    // Use lazy injection to break circular dependency
    @Autowired
    private ObjectProvider<PasswordEncoder> passwordEncoderProvider; // For encoding passwords
    
    // Helper method to get the encoder when needed
    private PasswordEncoder getPasswordEncoder() {
        return passwordEncoderProvider.getObject();
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
public void createUser(users users) {
    System.out.println("Creating user: " + users);

    // Step 1: Save the user to the database first
    userRepository.save(users);
    System.out.println("User saved successfully: " + users);

    // Step 2: Generate user key using UserskeyService (SHA-256, 64 hex characters)
    userskeys userKey = userskeyService.createUserkey(users.getUser_id());
    System.out.println("User key generated: " + userKey);

    // Step 3: Link the user key to the user
    users.setUserskeys(userKey);

    // Step 4: Set the policy_id for the user (if provided)
    if (users.getPolicies() != null) {
        policies policy = policyRepository.findById(users.getPolicies().getPolicy_id()).orElse(null);
        if (policy != null) {
            users.setPolicies(policy);
        } else {
            System.out.println("Policy not found for ID: " + users.getPolicies().getPolicy_id());
        }
    }

    // Step 5: Set the default role_id for the user
    roles defaultRole = new roles();
    defaultRole.setRole_id(Long.valueOf(3)); // Default role ID for USER as Long
    users.setRoles(defaultRole);

    // Step 6: Update the user with the key relationship
    userRepository.save(users);
    System.out.println("User updated with user key: " + users);
}


@Override
public users updateUserById(users users, Long id) {
    users oldUser = getUserById(id);
    if (oldUser != null) {
        oldUser.setFirstname(users.getFirstname());
        oldUser.setLastname(users.getLastname());
        // Don't update username as it shouldn't be changed
        // Only set username from the update if it's not null or empty
        if (users.getUsername() != null && !users.getUsername().isEmpty()) {
            oldUser.setUsername(users.getUsername());
        }
        oldUser.setEmail(users.getEmail());
        // Encode password if provided
        if (users.getPassword() != null && !users.getPassword().isEmpty()) {
            String rawPassword = users.getPassword();
            String encodedPassword = getPasswordEncoder().encode(rawPassword);
            System.err.println("Password update - Raw: " + rawPassword + ", Encoded: " + encodedPassword);
            oldUser.setPassword(encodedPassword);
        }
        oldUser.setPhoneNumber(users.getPhoneNumber());
        oldUser.setAddress(users.getAddress());
        oldUser.setCity(users.getCity());
        oldUser.setPostalCode(users.getPostalCode());
        // Update country if provided
        if (users.getCountries() != null && users.getCountries().getCountry_id() != null) {
            countries countryEntity = countryRepository.findById(users.getCountries().getCountry_id()).orElse(null);
            oldUser.setCountries(countryEntity);
        }
        // ... update other fields as needed ...
        userRepository.save(oldUser);
    }
    return oldUser;
}


/* 
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
            oldUser.setPhoneNumber(users.getPhoneNumber());
            oldUser.setAddress(users.getAddress());
            oldUser.setCity(users.getCity());
            oldUser.setPostalCode(users.getPostalCode());
            userRepository.save(oldUser);
        }
    }
*/



    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public users getUserByFirstnameAndLastname(String firstname, String lastname) {
        userRepository.findUserByFirstnameAndLastname(firstname, lastname);
        // Since the repository method returns void, we can't return a user object
        // This method might need to be redesigned or removed
        return null;
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
    while (userRepository.findUserByUsername(username) != null) {
        System.out.println("Username already exists: " + username);
        username = baseUsername + "-" + counter;
        counter++;
    }

    System.out.println("Generated unique username: " + username);
    return username;
}

    @Override
    public users getUserByUsername(String username) {
        users users = userRepository.findUserByUsername(username);

        if(users == null) {
            throw new UsernameNotFoundException("User not found with username : " + username);
        }
        // Just return the user entity, not a UserDetails object
        return users;
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return userRepository.findUserByUsername(username) != null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        users users = userRepository.findUserByUsername(username);
        if (users == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(users.getUsername(), users.getPassword(), new ArrayList<>());
    }

}
