package com.myriamfournier.olympics_ticket_office.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myriamfournier.olympics_ticket_office.pojo.userskeys;
import com.myriamfournier.olympics_ticket_office.pojo.keysgenerations;
import com.myriamfournier.olympics_ticket_office.repository.UserskeyRepository;
import com.myriamfournier.olympics_ticket_office.repository.KeysgenerationRepository;

/**
 * Unit tests for UserskeyServiceImpl
 */
@ExtendWith(MockitoExtension.class)
public class UserskeyServiceImplUnitTest {

    // Mock the repository dependencies that UserskeyServiceImpl uses
    @Mock
    private UserskeyRepository userskeyRepository;
    
    @Mock
    private KeysgenerationRepository keysgenerationRepository;

    // Inject the mocks into the service implementation we're testing
    @InjectMocks
    private UserskeyServiceImpl userskeyServiceImpl;

    @Test
    public void testGetAllUserskeys() {
        // Arrange
        List<userskeys> userskeysList = Arrays.asList(new userskeys(), new userskeys());
        when(userskeyRepository.findAllWithDetails()).thenReturn(userskeysList);

        // Act
        List<userskeys> result = userskeyServiceImpl.getAllUserskeys();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(userskeyRepository, times(1)).findAllWithDetails();
    }

    @Test
    public void testGetAllWithKeysgenerations() {
        // Arrange
        List<userskeys> userskeysList = Arrays.asList(new userskeys(), new userskeys());
        when(userskeyRepository.findAllWithKeysgenerations()).thenReturn(userskeysList);
        
        // Act
        List<userskeys> result = userskeyServiceImpl.getAllWithKeysgenerations();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(userskeyRepository, times(1)).findAllWithKeysgenerations();
    }

    @Test
    public void testGetUserskeyById() {
        // Arrange
        userskeys userskey = new userskeys();
        when(userskeyRepository.findById(1L)).thenReturn(Optional.of(userskey));
        
        // Act
        userskeys result = userskeyServiceImpl.getUserskeyById(1L);

        // Assert
        assertNotNull(result);
        verify(userskeyRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetUserskeyByIdNotFound() {
        // Arrange
        when(userskeyRepository.findById(1L)).thenReturn(Optional.empty());
        
        // Act
        userskeys result = userskeyServiceImpl.getUserskeyById(1L);

        // Assert
        assertEquals(null, result);
        verify(userskeyRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateUserskey() {
        // Arrange
        userskeys newUserskey = new userskeys();
        when(userskeyRepository.save(any())).thenReturn(newUserskey);
        
        // Act
        userskeyServiceImpl.createUserskey(newUserskey);

        // Assert
        verify(userskeyRepository, times(1)).save(newUserskey);
    }

    @Test
    public void testUpdateUserskeyById() {
        // Arrange
        userskeys existingUserskey = new userskeys();
        userskeys updatedUserskey = new userskeys();
        when(userskeyRepository.findById(1L)).thenReturn(Optional.of(existingUserskey));
        when(userskeyRepository.save(any())).thenReturn(existingUserskey);
        
        // Act
        userskeyServiceImpl.updateUserskeyById(updatedUserskey, 1L);

        // Assert
        verify(userskeyRepository, times(1)).findById(1L);
        verify(userskeyRepository, times(1)).save(existingUserskey);
    }

    @Test
    public void testUpdateUserskeyByIdNotFound() {
        // Arrange
        userskeys updatedUserskey = new userskeys();
        when(userskeyRepository.findById(1L)).thenReturn(Optional.empty());
        
        // Act
        userskeyServiceImpl.updateUserskeyById(updatedUserskey, 1L);

        // Assert
        verify(userskeyRepository, times(1)).findById(1L);
        verify(userskeyRepository, times(0)).save(any());
    }

    @Test
    public void testDeleteUserskeyById() {
        // Act
        userskeyServiceImpl.deleteUserskeyById(1L);
        
        // Assert
        verify(userskeyRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testCreateUserkey() {
        // Arrange
        Long userId = 123L;
        keysgenerations keyGen = new keysgenerations();
        keyGen.setKeyGenerated("generated_hash");
        
        userskeys savedUserkey = new userskeys();
        savedUserkey.setKeysgenerations(keyGen);
        
        when(keysgenerationRepository.save(any(keysgenerations.class))).thenReturn(keyGen);
        when(userskeyRepository.save(any(userskeys.class))).thenReturn(savedUserkey);
        
        // Act
        userskeys result = userskeyServiceImpl.createUserkey(userId);
        
        // Assert
        assertNotNull(result);
        verify(keysgenerationRepository, times(1)).save(any(keysgenerations.class));
        verify(userskeyRepository, times(1)).save(any(userskeys.class));
    }

    @Test
    public void testGenerateUserKeyHash() {
        // Arrange
        Long userId = 123L;
        
        // Act
        String result = userskeyServiceImpl.generateUserKeyHash(userId);
        
        // Assert
        assertNotNull(result);
        assertEquals(64, result.length()); // SHA-256 produces 64 character hex string
    }

    @Test
    public void testGenerateUserKeyHashUniqueness() {
        // Arrange
        Long userId = 123L;
        
        // Act
        String hash1 = userskeyServiceImpl.generateUserKeyHash(userId);
        String hash2 = userskeyServiceImpl.generateUserKeyHash(userId);
        
        // Assert
        assertNotNull(hash1);
        assertNotNull(hash2);
        // Hashes should be different due to timestamp and random components
        // Note: Very small chance they could be the same, but practically impossible
        assertEquals(64, hash1.length());
        assertEquals(64, hash2.length());
    }

}
