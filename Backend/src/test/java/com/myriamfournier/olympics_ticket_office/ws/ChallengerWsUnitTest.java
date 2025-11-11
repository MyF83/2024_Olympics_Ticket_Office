package com.myriamfournier.olympics_ticket_office.ws;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myriamfournier.olympics_ticket_office.pojo.challengers;
import com.myriamfournier.olympics_ticket_office.service.ChallengerService;


/**
 * Unit tests for ChallengerWs REST controller
 */
@ExtendWith(MockitoExtension.class)
public class ChallengerWsUnitTest {



     @Mock
    private ChallengerService challengerService;

    @InjectMocks
    private ChallengerWs challengerWs;

    

    @Test
    public void testGetAllChallengersDefault() {       
        // Arrange - Create mock challengers list
        challengers challenger1 = new challengers();        
        challenger1.setChallenger_id(1L);
        challengers challenger2 = new challengers();        
        challenger2.setChallenger_id(2L);
        List<challengers> mockChallengers = Arrays.asList(challenger1, challenger2);
        when(challengerService.getAllChallengers()).thenReturn(mockChallengers);

        // Act
        List<challengers> result = challengerWs.getAllChallengersDefault();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(challengerService).getAllChallengers();
            
    }
    @Test
    public void testgetAllWithCountries() {       
        // Arrange - Create mock challengers list with countries
        challengers challenger1 = new challengers();        
        challenger1.setChallenger_id(1L);
        challengers challenger2 = new challengers();        
        challenger2.setChallenger_id(2L);
        List<challengers> mockChallengers = Arrays.asList(challenger1, challenger2);
        when(challengerService.getAllWithCountries()).thenReturn(mockChallengers);

        // Act
        List<challengers> result = challengerWs.getAllWithCountries();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(challengerService).getAllWithCountries();
    }


    @Test
    public void testgetChallengerById() {       
        // Arrange - Create mock challenger
        challengers mockChallenger = new challengers();        
        mockChallenger.setChallenger_id(1L);
        when(challengerService.getChallengerById(1L)).thenReturn(mockChallenger);

        // Act
        challengers result = challengerWs.getChallengerById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getChallenger_id());
        verify(challengerService).getChallengerById(1L);
    }


    @Test
    public void testcreateChallenger() {       
        // Arrange - Create mock challenger to be created
        challengers newChallenger = new challengers();        
        newChallenger.setChallenger_id(1L);
        
        // Mock void method
        doNothing().when(challengerService).createChallenger(newChallenger);

        // Act - Call void method
        challengerWs.createChallenger(newChallenger);

        // Assert - Verify service method was called
        verify(challengerService).createChallenger(newChallenger);
    }

    @Test
    public void testupdateChallengerById() {       
        // Arrange - Create mock challenger to be updated
        challengers updatedChallenger = new challengers();        
        updatedChallenger.setChallenger_id(1L);
        
        // Mock void method
        doNothing().when(challengerService).updateChallengerById(updatedChallenger, 1L);

        // Act - Call void method
        challengerWs.updateChallengerById(1L, updatedChallenger);

        // Assert - Verify service method was called with correct parameters
        verify(challengerService).updateChallengerById(updatedChallenger, 1L);
    }


    @Test
    public void testdeleteChallengerById() {       
        // Arrange - No return value for delete operation
        doNothing().when(challengerService).deleteChallengerById(1L);

        // Act
        challengerWs.deleteChallengerById(1L);

        // Assert
        verify(challengerService).deleteChallengerById(1L);
    }
}


    