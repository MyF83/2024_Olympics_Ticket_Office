package com.myriamfournier.olympics_ticket_office.ws;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myriamfournier.olympics_ticket_office.service.UserService;

import java.util.List;

import com.myriamfournier.olympics_ticket_office.pojo.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RequestMapping(ApiRegistration.API_REST
        + ApiRegistration.USER)
@RestController
public class UserWs {

    @Autowired
    private UserService userService;
    // CRUD operations for User entity
    // Create, Read, Update, Delete methods for User entity
    // Example: public User createUser(User user) { ... }
    // Example: public User getUserById(Long id) { ... }
    // Example: public User updateUser(Long id, User user) { ... }
    
 @GetMapping
    public ResponseEntity<String> defaultApiEndpoint() {
        return ResponseEntity.ok("User Page");
    }
// ///////////////////////////////////////////////// //
//       ALL GET METHODS FOR USER ENTITY            //
// /////////////////////////////////////////////// //


    //GET method to retrieve all users
    // Example: GET /api/user/all   
    @GetMapping("all")
    @ResponseBody
    public List<users> getAllUsers() {
        return userService.getAllUsers(); // Assuming you have a userService to fetch all users
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


    @PostMapping
    public void createUser(@RequestBody users user){
            userService.createUser(user);
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
