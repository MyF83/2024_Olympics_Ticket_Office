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

import com.myriamfournier.olympics_ticket_office.pojo.challengers;
import com.myriamfournier.olympics_ticket_office.pojo.countries;
import com.myriamfournier.olympics_ticket_office.repository.ChallengerRepository;

/**
 * Unit tests for ChallengerServiceImpl
 */
@ExtendWith(MockitoExtension.class)
@SuppressWarnings("null")
public class ChallengerServiceImplUnitTest {

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
        verify(challengerRepository).findAllWithDetails();
    }

       @Test
    public void testGetAllWithCountries() {
        // Arrange
        countries mockCountry = new countries();
        challengers challenger1 = new challengers();
        challenger1.setCountries(mockCountry);
        challengers challenger2 = new challengers();
        challenger2.setCountries(mockCountry);
        
        List<challengers> challengersList = Arrays.asList(challenger1, challenger2);
        when(challengerRepository.findAllWithCountries()).thenReturn(challengersList);
        
        // Act
        List<challengers> result = challengerServiceImpl.getAllWithCountries();
        
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertNotNull(result.get(0).getCountries());
        assertNotNull(result.get(1).getCountries());
        verify(challengerRepository).findAllWithCountries();
    }

       @Test
    public void testGetChallengerById() {
        // Arrange
        challengers mockChallenger = new challengers();
        mockChallenger.setChallenger_id(1L);
        when(challengerRepository.findById(1L)).thenReturn(Optional.of(mockChallenger));
        
        // Act
        challengers result = challengerServiceImpl.getChallengerById(1L);
        
        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getChallenger_id());
        verify(challengerRepository).findById(1L);
    }

       @Test
    public void testCreateChallenger() {
        // Arrange
        challengers newChallenger = new challengers();
        newChallenger.setChallenger_id(1L);
        when(challengerRepository.save(any(challengers.class))).thenReturn(newChallenger);
        
        // Act
        challengerServiceImpl.createChallenger(newChallenger);
        
        // Assert
        verify(challengerRepository).save(newChallenger);
    }
    
    @Test
    public void testUpdateChallengerById() {
        // Arrange
        challengers existingChallenger = new challengers();
        existingChallenger.setChallenger_id(1L);
        existingChallenger.setName("Original Name");
        
        challengers updatedChallenger = new challengers();
        updatedChallenger.setName("Updated Name");
        
        when(challengerRepository.findById(1L)).thenReturn(Optional.of(existingChallenger));
        when(challengerRepository.save(any(challengers.class))).thenReturn(existingChallenger);
        
        // Act
        challengerServiceImpl.updateChallengerById(updatedChallenger, 1L);
        
        // Assert
        verify(challengerRepository).findById(1L);
        verify(challengerRepository).save(any(challengers.class));
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
