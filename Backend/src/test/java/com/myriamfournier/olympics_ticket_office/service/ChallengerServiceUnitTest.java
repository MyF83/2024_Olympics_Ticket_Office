package com.myriamfournier.olympics_ticket_office.service;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myriamfournier.olympics_ticket_office.repository.ChallengerRepository;
import com.myriamfournier.olympics_ticket_office.service.impl.ChallengerServiceImpl;
import com.myriamfournier.olympics_ticket_office.pojo.challengers;

/**
 * Unit tests for ChallengerServiceImpl
 */
@ExtendWith(MockitoExtension.class)
public class ChallengerServiceUnitTest {

    // Mock the repository dependencies that ChallengerServiceImpl uses
    @Mock
    private ChallengerRepository challengerRepository;

    // Inject the mocks into the service implementation we're testing
    @InjectMocks
    private ChallengerServiceImpl challengerServiceImpl;

    @Test
    public void testGetAllChallengers() {
        // Arrange
        List<challengers> challengersList = Arrays.asList(new challengers(), new challengers());
        when(challengerRepository.findAllWithDetails()).thenReturn(challengersList);
        
        // Act
        List<challengers> result = challengerServiceImpl.getAllChallengers();
        
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(challengerRepository, times(1)).findAllWithDetails();
    }

    @Test
    public void testGetAllWithCountries() {
        // Arrange
        List<challengers> challengersList = Arrays.asList(new challengers(), new challengers());
        when(challengerRepository.findAllWithCountries()).thenReturn(challengersList);
        
        // Act
        List<challengers> result = challengerServiceImpl.getAllWithCountries();
        
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(challengerRepository, times(1)).findAllWithCountries();
    }

    @Test
    public void testGetChallengerById() {
        // Arrange
        Long challengerId = 1L;
        challengers challenger = new challengers();
        when(challengerRepository.findById(challengerId)).thenReturn(Optional.of(challenger));
        
        // Act
        challengers result = challengerServiceImpl.getChallengerById(challengerId);
        
        // Assert
        assertNotNull(result);
        verify(challengerRepository, times(1)).findById(challengerId);
    }

    @Test
    public void testGetChallengerByIdNotFound() {
        // Arrange
        Long challengerId = 1L;
        when(challengerRepository.findById(challengerId)).thenReturn(Optional.empty());
        
        // Act
        challengers result = challengerServiceImpl.getChallengerById(challengerId);
        
        // Assert
        assertEquals(null, result);
        verify(challengerRepository, times(1)).findById(challengerId);
    }

    @Test
    public void testCreateChallenger() {
        // Arrange
        challengers challenger = new challengers();
        
        // Act
        challengerServiceImpl.createChallenger(challenger);
        
        // Assert
        verify(challengerRepository, times(1)).save(challenger);
    }

    @Test
    public void testUpdateChallengerById() {
        // Arrange
        Long challengerId = 1L;
        challengers existingChallenger = new challengers();
        challengers updatedChallenger = new challengers();
        when(challengerRepository.findById(challengerId)).thenReturn(Optional.of(existingChallenger));
        
        // Act
        challengerServiceImpl.updateChallengerById(updatedChallenger, challengerId);
        
        // Assert
        verify(challengerRepository, times(1)).findById(challengerId);
        verify(challengerRepository, times(1)).save(existingChallenger);
    }

    @Test
    public void testUpdateChallengerByIdNotFound() {
        // Arrange
        Long challengerId = 1L;
        challengers updatedChallenger = new challengers();
        when(challengerRepository.findById(challengerId)).thenReturn(Optional.empty());
        
        // Act
        challengerServiceImpl.updateChallengerById(updatedChallenger, challengerId);
        
        // Assert
        verify(challengerRepository, times(1)).findById(challengerId);
        verify(challengerRepository, times(0)).save(updatedChallenger);
    }

    @Test
    public void testDeleteChallengerById() {
        // Arrange
        Long challengerId = 1L;
        
        // Act
        challengerServiceImpl.deleteChallengerById(challengerId);
        
        // Assert
        verify(challengerRepository, times(1)).deleteById(challengerId);
    }
}
