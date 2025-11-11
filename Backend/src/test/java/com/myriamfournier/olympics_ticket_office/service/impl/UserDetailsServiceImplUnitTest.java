package com.myriamfournier.olympics_ticket_office.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.myriamfournier.olympics_ticket_office.pojo.users;
import com.myriamfournier.olympics_ticket_office.pojo.roles;
import com.myriamfournier.olympics_ticket_office.repository.UserRepository;


@ExtendWith(MockitoExtension.class) 
public class UserDetailsServiceImplUnitTest {

    // Mock the repository dependencies that UserDetailsServiceImpl uses
    @Mock
    private UserRepository userRepository;

    // Inject the mocks into the service implementation we're testing
    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Test
    public void testLoadUserByUsername() {
        // Arrange
        String username = "testuser";
        users user = new users();
        user.setUsername(username);
        user.setPassword("password123");
        
        roles role = new roles();
        role.setName("USER");
        user.setRoles(role);
        
        when(userRepository.findUserByUsername(username)).thenReturn(user);

        // Act
        UserDetails result = userDetailsServiceImpl.loadUserByUsername(username);

        // Assert
        assertNotNull(result);
        assertEquals(username, result.getUsername());
        assertEquals("password123", result.getPassword());
        verify(userRepository, times(1)).findUserByUsername(username);
    }

    @Test
    public void testLoadUserByUsernameNotFound() {
        // Arrange
        String username = "nonexistentuser";
        when(userRepository.findUserByUsername(username)).thenReturn(null);

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsServiceImpl.loadUserByUsername(username);
        });
        
        verify(userRepository, times(1)).findUserByUsername(username);
    }

}
