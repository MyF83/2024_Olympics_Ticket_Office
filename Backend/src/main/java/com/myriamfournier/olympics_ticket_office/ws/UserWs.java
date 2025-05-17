package com.myriamfournier.olympics_ticket_office.ws;

import org.springframework.http.HttpStatus;
import java.util.Optional;
import java.io.Console;
import java.util.List;
import com.myriamfournier.olympics_ticket_office.pojo.LoginRequest;
import com.myriamfournier.olympics_ticket_office.pojo.RegisterRequest;
import com.myriamfournier.olympics_ticket_office.pojo.policies;
import com.myriamfournier.olympics_ticket_office.repository.PolicyRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.myriamfournier.olympics_ticket_office.service.UserService;
import com.myriamfournier.olympics_ticket_office.repository.UserRepository;
import com.myriamfournier.olympics_ticket_office.pojo.users;
import org.springframework.security.crypto.password.PasswordEncoder;



// @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(ApiRegistration.API_REST + ApiRegistration.USER)
@RestController
public class UserWs {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PolicyRepository policyRepository;

    public UserWs(UserService userService) {
        this.userService = userService;
    }

    // CRUD operations for User entity
    // Create, Read, Update, Delete methods for User entity
    // Example: public User createUser(User user) { ... }
    // Example: public User getUserById(Long id) { ... }
    // Example: public User updateUser(Long id, User user) { ... }
    
 /*@GetMapping // Cannot have 2 methods with @GetMapping
    public ResponseEntity<String> defaultApiEndpoint() {
        return ResponseEntity.ok("User Page");
    }*/
// ///////////////////////////////////////////////// //
//       ALL GET METHODS FOR USER ENTITY            //
// /////////////////////////////////////////////// //


    //GET method to retrieve all users
    // Example: GET /api/user/all   
    @GetMapping
    public List<users> getAllUsersDefault() {
        return userService.getAllUsers(); // Assuming you have a userService to fetch all users
    } 
    
    
    @GetMapping("roles")
    public List<users> getAllWithRoles() {
        return userService.getAllWithRoles(); // Assuming you have a userService to fetch all users
    } 

    @GetMapping("userskeys")
    public List<users> getAllWithUserskeys() {
        return userService.getAllWithUserskeys(); // Assuming you have a userService to fetch all users
    } 

    @GetMapping("userselections")
    public List<users> getAllWithUserselections() {
        return userService.getAllWithUserselections(); // Assuming you have a userService to fetch all users
    } 

    @GetMapping("policies")
    public List<users> getAllWithPolicies() {
        return userService.getAllWithPolicies(); // Assuming you have a userService to fetch all users
    } 

    @GetMapping("countries")
    public List<users> getAllWithCountries() {
        return userService.getAllWithCountries(); // Assuming you have a userService to fetch all users
    } 


    //GET method to retrieve a user by ID
    // Example: GET /api/user/{id}
    @GetMapping("{id}")
    /*@ResponseBody  */     
    public users getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id); // Assuming you have a userService to fetch user by ID
        
    }


    //GET method to retrieve a user by firstname and lastname
    @GetMapping("firstname/{firstname}, lastname/{lastname}")
    @ResponseBody
    public users getUserByFirstnameAndLastname(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname) {
        return userService.getUserByFirstnameAndLastname(firstname, lastname); // Assuming you have a userService to fetch user by firstname and lastname
    }

    //GET method to retrieve a user by username
    // Example: GET /api/user/username/{username}
    // In this API, the username is used to identify the user
    // and is composed by the first name and last name of the user, 
    // separated by a hyphen (-).	
    @GetMapping("username/{username}")
    @ResponseBody
    public users getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username); // Assuming you have a userService to fetch user by username
    }   
    



    /* 
    //GET method to retrieve a user by username
    // Example: GET /api/user/username/{username}
    // In this API, the username is used to identify the user 
    // and is composed by the first name and last name of the user.
    @GetMapping("username/{userame}")
    // @ResponseBody
    public users getUserByUsername(@PathVariable("username") String username) {
        String[] parts = username.split("-");
        String firstname = parts[0];
        String lastname = parts[1];
        return userService.getUserByFirstnameAndLastname(firstname, lastname);
    }
    */
    
    /* 
        @GetMapping("{firstame}")
        public void getUserByFirstname(@PathVariable("fisrtname") String firstname, @RequestBody users user) {
            userService.getUserByFirstname(firstname); // Assuming you have a userService to update user by his firstname
    
        }
    
        @GetMapping("{lastname}")
        public void getUserByLastname(@PathVariable("lastname") String lastname, @RequestBody users user) {
            userService.getUserByLastname(lastname); // Assuming you have a userService to update user by his lastname
    
        }
    */
// ///////////////////////////////////////////////// //
//       ALL POST METHODS FOR USER ENTITY           //
// /////////////////////////////////////////////// //

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        System.err.println("registerUser called with request: " + request);
        // Check if the username is already taken
        String generatedUsername = userService.generateUniqueUsername(request.getFirstname(), request.getLastname());
        users user = new users();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setUsername(generatedUsername);
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        // Set other fields to null or defaults as needed

        // Fetch and set the accepted policy
        if (request.getPolicyId() != null) {
        policies acceptedPolicy = policyRepository.findById(request.getPolicyId()).orElse(null);
        user.setPolicies(acceptedPolicy);
    }
        System.err.println("Policy set on user: " + (user.getPolicies() != null ? user.getPolicies().getPolicy_id() : "null"));
        userService.createUser(user);
        return ResponseEntity.ok(user); // Optionally return a DTO with the generated username
    }

    @PostMapping
    public void createUser(@RequestBody users user){
            userService.createUser(user);
    }


    @PostMapping("/generate-username")
    public ResponseEntity<String> generateUsername(@RequestBody users user) {
        String username = userService.generateUniqueUsername(user.getFirstname(), user.getFirstname());
        user.setUsername(username);
        userService.createUser(user); // Save the user with the generated username
        return ResponseEntity.ok("Generated username: " + username);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.err.println("loginUser called with request: " + loginRequest);
        users user = userRepository.findByUsername(loginRequest.getUsername());
        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            // Use hashed password check
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }


   // @PostMapping
   // public void createUsername(@RequestBody users user){
   //         userService.createUsername(user);
   // }

// ///////////////////////////////////////////////// //
//       ALL PUT METHODS FOR USER ENTITY           //
// /////////////////////////////////////////////// //

    //PUT method to update an existing user
    // Example: PUT /api/user/update/{id}
    @PutMapping("{id}")
    public void updateUserById(@PathVariable("id") Long id, @RequestBody users user) {
        userService.updateUserById(user, id); // Assuming you have a userService to update user by ID

    }
/* 
    @PutMapping("{firstame}")
    public void updateUserFirstname(@PathVariable("fisrtname") String firstname, @RequestBody users user) {
        userService.updateUserFirstname(firstname); // Assuming you have a userService to update user by his firstname

    }

    @PutMapping("{lastname}")
    public void updateUserLastName(@PathVariable("lastname") String lastName, @RequestBody users user) {
        userService.updateUserLastName(lastName); // Assuming you have a userService to update user by his lastname

    }*/


// ///////////////////////////////////////////////// //
//       ALL DELETE METHODS FOR USER ENTITY         //
// /////////////////////////////////////////////// //

    @DeleteMapping("{id}")
    public void deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);

    }


    /* 

    //GET method to retrieve a user by email
    // Example: GET /api/user/email/{email} 
    @GetMapping("email/{email}")
    @ResponseBody
    public users getUserByEmail(@PathVariable("email") String email) {
        return userService.getUserByEmail(email); // Assuming you have a userService to fetch user by email
    }

    //GET method to retrieve a user by username
    // Example: GET /api/user/username/{username}
    // In this API, the username is used to identify the user 
    // and is composed by the first name and last name of the user.
    @GetMapping("username/{username}")
    @ResponseBody
    public users getUserByUsername(@PathVariable("username") String username) {
    String[] parts = username.split("-");
    String firstname = parts[0];
    String lastname = parts[1];
    return userService.getUserByFirstnameAndLastname(firstname, lastname);
    }

    //GET method to retrieve a user by password
    // Example: GET /api/user/password/{password}       
    @GetMapping("password/{password}")
    @ResponseBody
    public users getUserByPassword(@PathVariable("password") String password) {
        return userService.getUserByPassword(password); // Assuming you have a userService to fetch user by password
    }

    //GET method to retrieve a user by role
    // Example: GET /api/user/role/{role}               
    @GetMapping("role/{role}")
    @ResponseBody
    public users getUserByRole(@PathVariable("role") String role) {
        return userService.getUserByRole(role); // Assuming you have a userService to fetch user by role
    }



    

    

    //POST method to create a new user
    // Example: POST /api/user/create
    @PostMapping
    public void createUser(@RequestBody users user) {
        // Logic to create a new user
    }



    

    //DELETE method to delete a user by ID
    // Example: DELETE /api/user/delete/{id}        
    @DeleteMapping("{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        // Logic to delete a user by ID
    }
    

    */

}
