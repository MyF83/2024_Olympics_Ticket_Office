package com.myriamfournier.olympics_ticket_office.ws;

import org.springframework.http.HttpStatus;
import java.util.Optional;
import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.myriamfournier.olympics_ticket_office.pojo.LoginRequest;
import com.myriamfournier.olympics_ticket_office.pojo.RegisterRequest;
import com.myriamfournier.olympics_ticket_office.pojo.policies;
import com.myriamfournier.olympics_ticket_office.repository.PolicyRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMethod;

import com.myriamfournier.olympics_ticket_office.service.CartService;
import com.myriamfournier.olympics_ticket_office.service.UserService;
import com.myriamfournier.olympics_ticket_office.repository.UserRepository;
import com.myriamfournier.olympics_ticket_office.pojo.users;
import com.myriamfournier.olympics_ticket_office.pojo.carts;

//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.myriamfournier.olympics_ticket_office.configuration.JwtUtils;


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

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CartService cartService; 

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
    

@GetMapping("/carts/user")
public ResponseEntity<?> getUserCarts() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    users users = userRepository.findByUsername(username);
    if (users == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
    // Fetch carts for this user
    List<carts> carts = cartService.findByUser(users); // or cartRepository.findByUser(user)
    return ResponseEntity.ok(carts);
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
        users users = new users();
        users.setFirstname(request.getFirstname());
        users.setLastname(request.getLastname());
        users.setUsername(generatedUsername);
        users.setEmail(request.getEmail());
        users.setPassword(passwordEncoder.encode(request.getPassword()));
        // Set other fields to null or defaults as needed

        // Fetch and set the accepted policy
        if (request.getPolicyId() != null) {
        policies acceptedPolicy = policyRepository.findById(request.getPolicyId()).orElse(null);
        users.setPolicies(acceptedPolicy);
    }
        System.err.println("Policy set on user: " + (users.getPolicies() != null ? users.getPolicies().getPolicy_id() : "null"));
        userService.createUser(users);
        return ResponseEntity.ok(users); // Optionally return a DTO with the generated username
    }

    @PostMapping
    public void createUser(@RequestBody users users){
            userService.createUser(users);
    }


    @PostMapping("/generate-username")
    public ResponseEntity<String> generateUsername(@RequestBody users users) {
        String username = userService.generateUniqueUsername(users.getFirstname(), users.getFirstname());
        users.setUsername(username);
        userService.createUser(users); // Save the user with the generated username
        return ResponseEntity.ok("Generated username: " + username);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.err.println("loginUser called with request: " + loginRequest);
        users users = userRepository.findByUsername(loginRequest.getUsername());
        if (users != null && passwordEncoder.matches(loginRequest.getPassword(), users.getPassword())) {
            // Generate JWT token (implement jwtUtil as needed)
        String token = jwtUtils.generateToken(users.getUsername()); // or pass user details as needed

        // Build response with token and user info
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user_id", users.getUser_id());
        response.put("firstname", users.getFirstname());
        response.put("lastname", users.getLastname());
        response.put("username", users.getUsername());
        response.put("email", users.getEmail());
            return ResponseEntity.ok(response);
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
    //@PutMapping("{id}")
    //public void updateUserById(@PathVariable("id") Long id, @RequestBody users user) {
      //  userService.updateUserById(user, id); // Assuming you have a userService to update user by ID

    //}
    @PutMapping("{id}")
public ResponseEntity<users> updateUserById(@PathVariable("id") Long id, @RequestBody users users) {
     // Check for ID consistency first
    if (users.getUser_id() != null && !users.getUser_id().equals(id)) {
        return ResponseEntity.badRequest().build();
    }
    users updatedUser = userService.updateUserById(users, id);
    if (updatedUser == null) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(updatedUser);
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
