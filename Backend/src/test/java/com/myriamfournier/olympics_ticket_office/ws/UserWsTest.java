package com.myriamfournier.olympics_ticket_office.ws;

import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.myriamfournier.olympics_ticket_office.pojo.users;
import com.myriamfournier.olympics_ticket_office.service.UserService;
import com.myriamfournier.olympics_ticket_office.ws.UserWs;



// This class UserWsTest contains tests for the UserWs class,
// which is a web service API-Rest-Contoller (@RestController) for managing user-related operations in the Olympics Ticket Office application.

@ExtendWith(MockitoExtension.class)
public class UserWsTest {


    @Mock
    public UserService userService; // Mocked UserService to be used in the tests

    @InjectMocks
    public UserWs userWs; // InjectMocks for the UserWs class being tested

    @Test
    public void testCreateUser() {
        // Test the createUser method in UserWs
        // You can use Mockito to verify interactions with userService
        users mockUser = new users(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        mockUser.setUser_id(1L);
        mockUser.setFirstname("John");
        mockUser.setLastname("Doe");
        mockUser.setUsername("Doe-John"); // Assuming username is a combination of last and first name
        mockUser.setEmail("olympicgamesfan@gmail.com");
        mockUser.setPassword("password123");    
        mockUser.setPhonenumber("1234567890");
        mockUser.setAddress("1 Place de la Liberté");
        mockUser.setCreationDate(new java.sql.Timestamp(System.currentTimeMillis()));
        mockUser.setRoles(null); // Assuming roles is set to null for this test
        mockUser.setUserskeys(null); // Assuming userskeys is set to null for this test         
        mockUser.setUserselections(null); // Assuming userselections is set to null for this test
        mockUser.setPolicies(null); // Assuming policies is set to null for this test
        mockUser.setPolicySignDate(new java.sql.Timestamp(System.currentTimeMillis()));
        mockUser.setCity("Paris");
        mockUser.setPostalCode("75001");
        mockUser.setCountries(null); // Assuming countries is set to null for this test
    
    
        // Act: Call the method being tested
        userWs.createUser(mockUser);

        // Assert: Verify that the userService's createUser method was called
        org.mockito.Mockito.verify(userService).createUser(mockUser);
    }

    
    @Test
    public void testGetAllUsers() {
        // Mock the behavior of userService by creating a mock list of users
        //List<users> mockUsersList = List.of(
        //    new users("John", "Doe", "Doe-John", "olympicgamesfan@gmail.com", "password123", "1234567890", "1 Place de la Liberté", null, null, null, null, null, null, null, null),
        //    new users("Jane", "Smith", "Smith-Jane", "janethequeen@gmail.com", "password456", "0987654321", "2 Place de la Liberté", null, null, null, null, null, null, null, null),
        //    new users("Alice", "Johnson", "Johnson-Alice", "missjohnson@hotmail.com", "password789", "1122334455", "3 Place de la Liberté", null, null, null, null, null, null, null, null)
        //);
        users user1 = new users(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        user1.setFirstname("John");
        user1.setLastname("Doe");
        user1.setUsername("Doe-John");
        user1.setEmail("olympicgamesfan@gmail.com");
        user1.setPassword("password123");
        user1.setPhonenumber("1234567890");
        user1.setAddress("1 Place de la Liberté");

        users user2 = new users(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        user2.setFirstname("Jane");
        user2.setLastname("Smith");
        user2.setUsername("Smith-Jane");
        user2.setEmail("janethequeen@gmail.com");
        user2.setPassword("password456");
        user2.setPhonenumber("0987654321");
        user2.setAddress("2 Place de la Liberté");

        users user3 = new users(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        user3.setFirstname("Alice");
        user3.setLastname("Johnson");
        user3.setUsername("Johnson-Alice");
        user3.setEmail("missjohnson@hotmail.com");
        user3.setPassword("password789");
        user3.setPhonenumber("1122334455");
        user3.setAddress("3 Place de la Liberté");

        List<users> mockUsersList = List.of(user1, user2, user3);
        when(userService.getAllUsers()).thenReturn(mockUsersList);

        // Act: Call the method being tested
        List<users> usersList = userWs.getAllUsersDefault();

        // Assert: Verify the behavior and the result
        org.mockito.Mockito.verify(userService).getAllUsers();
        org.junit.jupiter.api.Assertions.assertNotNull(usersList);
        org.junit.jupiter.api.Assertions.assertEquals(3, usersList.size());
        org.junit.jupiter.api.Assertions.assertEquals("John", usersList.get(0).getFirstname());
        
    }


    // To test after having tested the createUser method
    @Test
    public void testGetUserById() {
        // Create a mock user object to be returned by the mocked userService
        Long userId = 1L;
        users mockUser = new users(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        mockUser.setFirstname("John");
        mockUser.setLastname("Doe");
        mockUser.setUsername("Doe-John"); // Assuming username is a combination of last and first name
        mockUser.setEmail("olympicgamesfan@gmail.com");
        mockUser.setPassword("password123");    
        mockUser.setPhonenumber("1234567890");
        mockUser.setAddress("1 Place de la Liberté");
        mockUser.setCreationDate(new java.sql.Timestamp(System.currentTimeMillis()));
        mockUser.setRoles(null); // Assuming roles is set to null for this test
        mockUser.setUserskeys(null); // Assuming userskeys is set to null for this test         
        mockUser.setUserselections(null); // Assuming userselections is set to null for this test
        mockUser.setPolicies(null); // Assuming policies is set to null for this test
        mockUser.setPolicySignDate(new java.sql.Timestamp(System.currentTimeMillis()));
        mockUser.setCity("Paris");
        mockUser.setPostalCode("75001");
        mockUser.setCountries(null); // Assuming countries is set to null for this test
        when(userService.getUserById(userId)).thenReturn(mockUser);
        
        // Act: Call the method being tested
        users user = userWs.getUserById(userId);

       // Assert: Verify the behavior and the result
        org.mockito.Mockito.verify(userService).getUserById(userId);
        org.junit.jupiter.api.Assertions.assertNotNull(user);
        org.junit.jupiter.api.Assertions.assertEquals("John", user.getFirstname());
        org.junit.jupiter.api.Assertions.assertEquals("Doe", user.getLastname());
    }

    
}
