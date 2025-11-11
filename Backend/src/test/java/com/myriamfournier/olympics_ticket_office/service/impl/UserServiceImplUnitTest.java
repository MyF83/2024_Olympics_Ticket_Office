package com.myriamfournier.olympics_ticket_office.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;

import com.myriamfournier.olympics_ticket_office.pojo.users;
import com.myriamfournier.olympics_ticket_office.pojo.countries;
import com.myriamfournier.olympics_ticket_office.pojo.policies;
import com.myriamfournier.olympics_ticket_office.pojo.userskeys;
import com.myriamfournier.olympics_ticket_office.repository.UserRepository;
import com.myriamfournier.olympics_ticket_office.repository.PolicyRepository;
import com.myriamfournier.olympics_ticket_office.repository.CountryRepository;
import com.myriamfournier.olympics_ticket_office.repository.UserskeyRepository;
import com.myriamfournier.olympics_ticket_office.repository.KeysgenerationRepository;
import com.myriamfournier.olympics_ticket_office.service.UserskeyService;
import com.myriamfournier.olympics_ticket_office.service.impl.UserServiceImpl;

/**
 * Unit tests for UserServiceImpl
 * 
 * This tests the actual business logic in UserServiceImpl to generate 
 * meaningful JaCoCo coverage reports.
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceImplUnitTest {

    @Mock
    private UserRepository userRepository;
    
    @Mock
    private PolicyRepository policyRepository;
    
    @Mock
    private CountryRepository countryRepository;
    
    @Mock
    private UserskeyRepository userskeyRepository;
    
    @Mock
    private KeysgenerationRepository keysgenerationRepository;
    
    @Mock
    private UserskeyService userskeyService;
    
    @Mock
    private ObjectProvider<PasswordEncoder> passwordEncoderProvider;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        // Only setup mocks that are commonly used
    }

    @Test
    public void testGetAllUsers() {
        // Arrange
        users user1 = new users();
        user1.setUser_id(1L);
        user1.setFirstname("John");
        user1.setLastname("Doe");
        
        users user2 = new users();
        user2.setUser_id(2L);
        user2.setFirstname("Jane");
        user2.setLastname("Smith");
        
        List<users> mockUsers = Arrays.asList(user1, user2);
        when(userRepository.findAllWithDetails()).thenReturn(mockUsers);
        
        // Act
        List<users> result = userServiceImpl.getAllUsers();
        
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFirstname());
        assertEquals("Jane", result.get(1).getFirstname());
        
        verify(userRepository).findAllWithDetails();
    }

    @Test
    public void testGetUserById() {
        // Arrange
        Long userId = 1L;
        users mockUser = new users();
        mockUser.setUser_id(userId);
        mockUser.setFirstname("John");
        mockUser.setLastname("Doe");
        
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        
        // Act
        users result = userServiceImpl.getUserById(userId);
        
        // Assert
        assertNotNull(result);
        assertEquals(userId, result.getUser_id());
        assertEquals("John", result.getFirstname());
        
        verify(userRepository).findById(userId);
    }

    @Test
    public void testGenerateUniqueUsername() {
        // Arrange
        String firstname = "John";
        String lastname = "Doe";
        
        // Mock that username doesn't exist
        when(userRepository.findUserByUsername("Doe-John")).thenReturn(null);
        
        // Act
        String result = userServiceImpl.generateUniqueUsername(firstname, lastname);
        
        // Assert
        assertNotNull(result);
        assertEquals("Doe-John", result);
        
        verify(userRepository).findUserByUsername("Doe-John");
    }

    @Test
    public void testGenerateUniqueUsernameWithConflict() {
        // Arrange
        String firstname = "John";
        String lastname = "Doe";
        
        users existingUser = new users();
        existingUser.setUsername("Doe-John");
        
        // Mock that first username exists, but second doesn't
        when(userRepository.findUserByUsername("Doe-John")).thenReturn(existingUser);
        when(userRepository.findUserByUsername("Doe-John-1")).thenReturn(null);
        
        // Act
        String result = userServiceImpl.generateUniqueUsername(firstname, lastname);
        
        // Assert
        assertNotNull(result);
        assertEquals("Doe-John-1", result);
        
        verify(userRepository).findUserByUsername("Doe-John");
        verify(userRepository).findUserByUsername("Doe-John-1");
    }

    @Test
    public void testIsUsernameTaken() {
        // Arrange
        String username = "Doe-John";
        users existingUser = new users();
        existingUser.setUsername(username);
        
        when(userRepository.findUserByUsername(username)).thenReturn(existingUser);
        
        // Act
        boolean result = userServiceImpl.isUsernameTaken(username);
        
        // Assert
        assertTrue(result);
        
        verify(userRepository).findUserByUsername(username);
    }

    @Test
    public void testDeleteUserById() {
        // Arrange
        Long userId = 1L;
        
        // Act
        userServiceImpl.deleteUserById(userId);
        
        // Assert
        verify(userRepository).deleteById(userId);
    }

    @Test
    public void testIsUsernameTakenReturnsFalse() {
        // Arrange
        String username = "nonexistent";
        
        when(userRepository.findUserByUsername(username)).thenReturn(null);
        
        // Act
        boolean result = userServiceImpl.isUsernameTaken(username);
        
        // Assert
        assertFalse(result);
        
        verify(userRepository).findUserByUsername(username);
    }

    @Test
    public void testCreateUserWithPolicy() {
        // Arrange
        users user = new users();
        user.setUser_id(1L);
        user.setFirstname("John");
        user.setLastname("Doe");
        
        policies policy = new policies();
        policy.setPolicy_id(1L);
        user.setPolicies(policy);
        
        userskeys userKey = new userskeys();
        userKey.setUserkey_id(1L);
        
        when(userRepository.save(any(users.class))).thenReturn(user);
        when(policyRepository.findById(1L)).thenReturn(Optional.of(policy));
        when(userskeyService.createUserkey(1L)).thenReturn(userKey);
        
        // Act
        userServiceImpl.createUser(user);
        
        // Assert
        verify(userRepository, times(2)).save(any(users.class));
        verify(policyRepository).findById(1L);
        verify(userskeyService).createUserkey(1L);
    }

    @Test
    public void testCreateUserWithoutPolicy() {
        // Arrange
        users user = new users();
        user.setUser_id(1L);
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setPolicies(null);
        
        userskeys userKey = new userskeys();
        userKey.setUserkey_id(1L);
        
        when(userRepository.save(any(users.class))).thenReturn(user);
        when(userskeyService.createUserkey(1L)).thenReturn(userKey);
        
        // Act
        userServiceImpl.createUser(user);
        
        // Assert
        verify(userRepository, times(2)).save(any(users.class));
        verify(userskeyService).createUserkey(1L);
    }

    @Test
    public void testCreateUserWithNonExistentPolicy() {
        // Arrange
        users user = new users();
        user.setUser_id(1L);
        user.setFirstname("John");
        user.setLastname("Doe");
        
        policies policy = new policies();
        policy.setPolicy_id(999L);
        user.setPolicies(policy);
        
        userskeys userKey = new userskeys();
        userKey.setUserkey_id(1L);
        
        when(userRepository.save(any(users.class))).thenReturn(user);
        when(policyRepository.findById(999L)).thenReturn(Optional.empty());
        when(userskeyService.createUserkey(1L)).thenReturn(userKey);
        
        // Act
        userServiceImpl.createUser(user);
        
        // Assert
        verify(userRepository, times(2)).save(any(users.class));
        verify(policyRepository).findById(999L);
        verify(userskeyService).createUserkey(1L);
    }

    @Test
    public void testUpdateUserByIdWithPassword() {
        // Arrange
        Long userId = 1L;
        users oldUser = new users();
        oldUser.setUser_id(userId);
        oldUser.setFirstname("Old");
        
        users updateUser = new users();
        updateUser.setFirstname("New");
        updateUser.setPassword("newPassword");
        updateUser.setUsername("newUsername");
        updateUser.setEmail("new@email.com");
        
        PasswordEncoder passwordEncoder = org.mockito.Mockito.mock(PasswordEncoder.class);
        when(passwordEncoderProvider.getObject()).thenReturn(passwordEncoder);
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedPassword");
        when(userRepository.findById(userId)).thenReturn(Optional.of(oldUser));
        when(userRepository.save(any(users.class))).thenReturn(oldUser);
        
        // Act
        users result = userServiceImpl.updateUserById(updateUser, userId);
        
        // Assert
        assertNotNull(result);
        verify(userRepository).findById(userId);
        verify(userRepository).save(any(users.class));
        verify(passwordEncoder).encode("newPassword");
    }

    @Test
    public void testUpdateUserByIdWithoutPassword() {
        // Arrange
        Long userId = 1L;
        users oldUser = new users();
        oldUser.setUser_id(userId);
        oldUser.setFirstname("Old");
        
        users updateUser = new users();
        updateUser.setFirstname("New");
        updateUser.setPassword(null);
        updateUser.setEmail("new@email.com");
        
        when(userRepository.findById(userId)).thenReturn(Optional.of(oldUser));
        when(userRepository.save(any(users.class))).thenReturn(oldUser);
        
        // Act
        users result = userServiceImpl.updateUserById(updateUser, userId);
        
        // Assert
        assertNotNull(result);
        verify(userRepository).findById(userId);
        verify(userRepository).save(any(users.class));
    }

    @Test
    public void testUpdateUserByIdWithEmptyPassword() {
        // Arrange
        Long userId = 1L;
        users oldUser = new users();
        oldUser.setUser_id(userId);
        oldUser.setFirstname("Old");
        
        users updateUser = new users();
        updateUser.setFirstname("New");
        updateUser.setPassword("");
        updateUser.setEmail("new@email.com");
        
        when(userRepository.findById(userId)).thenReturn(Optional.of(oldUser));
        when(userRepository.save(any(users.class))).thenReturn(oldUser);
        
        // Act
        users result = userServiceImpl.updateUserById(updateUser, userId);
        
        // Assert
        assertNotNull(result);
        verify(userRepository).findById(userId);
        verify(userRepository).save(any(users.class));
    }

    @Test
    public void testUpdateUserByIdWithCountry() {
        // Arrange
        Long userId = 1L;
        users oldUser = new users();
        oldUser.setUser_id(userId);
        oldUser.setFirstname("Old");
        
        countries country = new countries();
        country.setCountry_id(1L);
        
        users updateUser = new users();
        updateUser.setFirstname("New");
        updateUser.setCountries(country);
        
        when(userRepository.findById(userId)).thenReturn(Optional.of(oldUser));
        when(countryRepository.findById(1L)).thenReturn(Optional.of(country));
        when(userRepository.save(any(users.class))).thenReturn(oldUser);
        
        // Act
        users result = userServiceImpl.updateUserById(updateUser, userId);
        
        // Assert
        assertNotNull(result);
        verify(userRepository).findById(userId);
        verify(countryRepository).findById(1L);
        verify(userRepository).save(any(users.class));
    }

    @Test
    public void testUpdateUserByIdWithNullUser() {
        // Arrange
        Long userId = 1L;
        users updateUser = new users();
        updateUser.setFirstname("New");
        
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        
        // Act
        users result = userServiceImpl.updateUserById(updateUser, userId);
        
        // Assert
        assertNull(result);
        verify(userRepository).findById(userId);
    }

    @Test
    public void testUpdateUserByIdWithEmptyUsername() {
        // Arrange
        Long userId = 1L;
        users oldUser = new users();
        oldUser.setUser_id(userId);
        oldUser.setFirstname("Old");
        oldUser.setUsername("oldUsername");
        
        users updateUser = new users();
        updateUser.setFirstname("New");
        updateUser.setUsername("");
        
        when(userRepository.findById(userId)).thenReturn(Optional.of(oldUser));
        when(userRepository.save(any(users.class))).thenReturn(oldUser);
        
        // Act
        users result = userServiceImpl.updateUserById(updateUser, userId);
        
        // Assert
        assertNotNull(result);
        assertEquals("oldUsername", result.getUsername()); // Should keep old username
        verify(userRepository).findById(userId);
        verify(userRepository).save(any(users.class));
    }

    @Test
    public void testGetUserByUsername() {
        // Arrange
        String username = "testuser";
        users user = new users();
        user.setUsername(username);
        
        when(userRepository.findUserByUsername(username)).thenReturn(user);
        
        // Act
        users result = userServiceImpl.getUserByUsername(username);
        
        // Assert
        assertNotNull(result);
        assertEquals(username, result.getUsername());
        verify(userRepository).findUserByUsername(username);
    }

    @Test
    public void testGetAllWithRoles() {
        // Arrange
        users user1 = new users();
        user1.setUser_id(1L);
        List<users> mockUsers = Arrays.asList(user1);
        
        when(userRepository.findAllWithRoles()).thenReturn(mockUsers);
        
        // Act
        List<users> result = userServiceImpl.getAllWithRoles();
        
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(userRepository).findAllWithRoles();
    }

    @Test
    public void testGetAllWithUserskeys() {
        // Arrange
        users user1 = new users();
        user1.setUser_id(1L);
        List<users> mockUsers = Arrays.asList(user1);
        
        when(userRepository.findAllWithUserskeys()).thenReturn(mockUsers);
        
        // Act
        List<users> result = userServiceImpl.getAllWithUserskeys();
        
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(userRepository).findAllWithUserskeys();
    }

    @Test
    public void testGetAllWithUserselections() {
        // Arrange
        users user1 = new users();
        user1.setUser_id(1L);
        List<users> mockUsers = Arrays.asList(user1);
        
        when(userRepository.findAllWithUserselections()).thenReturn(mockUsers);
        
        // Act
        List<users> result = userServiceImpl.getAllWithUserselections();
        
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(userRepository).findAllWithUserselections();
    }

    @Test
    public void testGetAllWithPolicies() {
        // Arrange
        users user1 = new users();
        user1.setUser_id(1L);
        List<users> mockUsers = Arrays.asList(user1);
        
        when(userRepository.findAllWithPolicies()).thenReturn(mockUsers);
        
        // Act
        List<users> result = userServiceImpl.getAllWithPolicies();
        
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(userRepository).findAllWithPolicies();
    }

    @Test
    public void testGetAllWithCountries() {
        // Arrange
        users user1 = new users();
        user1.setUser_id(1L);
        List<users> mockUsers = Arrays.asList(user1);
        
        when(userRepository.findAllWithCountries()).thenReturn(mockUsers);
        
        // Act
        List<users> result = userServiceImpl.getAllWithCountries();
        
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(userRepository).findAllWithCountries();
    }

    @Test
    public void testLoadUserByUsername() {
        // Arrange
        String username = "testuser";
        users user = new users();
        user.setUsername(username);
        user.setPassword("password");
        
        when(userRepository.findUserByUsername(username)).thenReturn(user);
        
        // Act
        UserDetails result = userServiceImpl.loadUserByUsername(username);
        
        // Assert
        assertNotNull(result);
        assertEquals(username, result.getUsername());
        verify(userRepository).findUserByUsername(username);
    }
}
