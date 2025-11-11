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

import com.myriamfournier.olympics_ticket_office.pojo.ceremonies;
import com.myriamfournier.olympics_ticket_office.service.CeremonyService;
import com.myriamfournier.olympics_ticket_office.service.ControlService;


/**
 * Unit tests for CartWs REST controller
 */
@ExtendWith(MockitoExtension.class)
public class CeremonyWsUnitTest {



     @Mock
    private CeremonyService ceremonyService;

    @InjectMocks
    private CeremonyWs ceremonyWs;

    @Test
    public void testgetAllCeremoniesDefault  (){
        // Arrange - Create mock ceremonies list
        ceremonies ceremony1 = new ceremonies();        
        ceremony1.setCerem_id(1L);
        ceremonies ceremony2 = new ceremonies();        
        ceremony2.setCerem_id(2L);
        List<ceremonies> mockCeremonies = Arrays.asList(ceremony1, ceremony2);
        when(ceremonyService.getAllCeremonies()).thenReturn(mockCeremonies);

        // Act
        List<ceremonies> result = ceremonyWs.getAllCeremoniesDefault();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(ceremonyService).getAllCeremonies();
    }
    
    @Test
    public void testgetAllWithSites  (){
        // Arrange - Create mock ceremonies list with sites 
        ceremonies ceremony1 = new ceremonies();        
        ceremony1.setCerem_id(1L);
        ceremonies ceremony2 = new ceremonies();   
        ceremony2.setCerem_id(2L);        
        List<ceremonies> mockCeremonies = Arrays.asList(ceremony1, ceremony2);
        when(ceremonyService.getAllWithSites()).thenReturn(mockCeremonies);
        // Act
        List<ceremonies> result = ceremonyWs.getAllWithSites();
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

        @Test
    public void testgetCeremonyById  (){
        // Arrange - Create mock ceremony
        ceremonies ceremony = new ceremonies();       
        ceremony.setCerem_id(1L);     
        when(ceremonyService.getCeremonyById(1L)).thenReturn(ceremony);     
        // Act
        ceremonies result = ceremonyWs.getCeremonyById(1L);
        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getCerem_id());
        verify(ceremonyService).getCeremonyById(1L);

    }

        @Test
    public void testcreateCeremony  (){
        // Arrange - Create mock ceremony to be created
        ceremonies ceremony = new ceremonies();
        ceremony.setCerem_id(1L);
        doNothing().when(ceremonyService).createCeremony(ceremony);
        // Act
        ceremonyWs.createCeremony(ceremony);
        // Assert
        verify(ceremonyService).createCeremony(ceremony);

    }

        @Test
    public void testupdateCeremonyById  (){
        // Arrange - Create mock ceremony to be updated
        ceremonies ceremony = new ceremonies();
        ceremony.setCerem_id(1L);
        doNothing().when(ceremonyService).updateCeremonyById(ceremony, 1L);
        // Act
        ceremonyWs.updateCeremonyById(1L, ceremony);
        // Assert
        verify(ceremonyService).updateCeremonyById(ceremony, 1L);
    }

        @Test
    public void testdeleteCeremonyById  (){
        // Arrange
        doNothing().when(ceremonyService).deleteCeremonyById(1L);
        // Act
        ceremonyWs.deleteCeremonyById(1L);
        // Assert
        verify(ceremonyService).deleteCeremonyById(1L);
    }



}
