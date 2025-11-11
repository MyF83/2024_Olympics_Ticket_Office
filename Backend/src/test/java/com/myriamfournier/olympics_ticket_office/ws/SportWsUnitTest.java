package com.myriamfournier.olympics_ticket_office.ws;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myriamfournier.olympics_ticket_office.pojo.sports;
import com.myriamfournier.olympics_ticket_office.pojo.sites;
import com.myriamfournier.olympics_ticket_office.service.SportService;

@ExtendWith(MockitoExtension.class)
public class SportWsUnitTest {



    @Mock
    private SportService sportService;

    @InjectMocks
    private SportWs sportWs;

    @Test
    public void testGetAllSportsDefault() {
        // Arrange - Create mock sports list
        sports sport1 = new sports();
        sport1.setSport_id(1L);
        sport1.setName("Swimming");
        
        sports sport2 = new sports();
        sport2.setSport_id(2L);
        sport2.setName("Athletics");
        
        List<sports> mockSports = Arrays.asList(sport1, sport2);
        
        when(sportService.getAllSports()).thenReturn(mockSports);
        
        // Act - Call the method being tested
        List<sports> result = sportWs.getAllSportsDefault();
        
        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result size should be 2");
        assertEquals("Swimming", result.get(0).getName(), "First sport name should be 'Swimming'");
        assertEquals("Athletics", result.get(1).getName(), "Second sport name should be 'Athletics'");
        
        // Verify that the service method was called
        verify(sportService).getAllSports();
    }

    @Test
    public void testGetAllWithSites() {
        // Arrange - Create mock sports list with sites
        sports sport1 = new sports();
        sport1.setSport_id(1L);
        sport1.setName("Swimming");
        
        // Create a mock site for sport1
        sites site1 = new sites();
        site1.setName("Pool A");
        sport1.setSites(site1);

        sports sport2 = new sports();
        sport2.setSport_id(2L);
        sport2.setName("Athletics");
        
        // Create a mock site for sport2
        sites site2 = new sites();
        site2.setName("Stadium A");
        sport2.setSites(site2);

        List<sports> mockSports = Arrays.asList(sport1, sport2);

        when(sportService.getAllWithSites()).thenReturn(mockSports);

        // Act - Call the method being tested
        List<sports> result = sportWs.getAllWithSites();

        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result size should be 2");
        assertEquals("Swimming", result.get(0).getName(), "First sport name should be 'Swimming'");
        assertEquals("Athletics", result.get(1).getName(), "Second sport name should be 'Athletics'");
        assertNotNull(result.get(0).getSites(), "First sport should have a site");
        assertNotNull(result.get(1).getSites(), "Second sport should have a site");
        assertEquals("Pool A", result.get(0).getSites().getName(), "First sport site name should be 'Pool A'");
        assertEquals("Stadium A", result.get(1).getSites().getName(), "Second sport site name should be 'Stadium A'");

        // Verify that the service method was called
        verify(sportService).getAllWithSites();
    }

    @Test
    public void testGetSportById() {
        // Arrange - Create mock sport
        Long sportId = 1L;
        sports mockSport = new sports();
        mockSport.setSport_id(sportId);
        mockSport.setName("Swimming");

        when(sportService.getSportById(sportId)).thenReturn(mockSport);

        // Act - Call the method being tested
        sports result = sportWs.getSportById(sportId);

        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(sportId, result.getSport_id(), "Sport ID should match");
        assertEquals("Swimming", result.getName(), "Sport name should be 'Swimming'");

        // Verify that the service method was called
        verify(sportService).getSportById(sportId);
    }

    @Test
    public void testCreateSport() { 
        // Arrange - Create a mock sport
        sports mockSport = new sports();
        mockSport.setSport_id(1L);
        mockSport.setName("Swimming");
        
        // Act - Call the method being tested
        sportWs.createSport(mockSport);

        // Assert - Verify that the sportService's createSport method was called
        verify(sportService).createSport(mockSport);
    }

    @Test
    public void testUpdateSportById() {
        // Arrange - Create a mock sport
        Long sportId = 1L;
        sports mockSport = new sports();
        mockSport.setSport_id(sportId);
        mockSport.setName("Swimming Updated");
        
        // Act - Call the method being tested
        sportWs.updateSportById(sportId, mockSport);

        // Assert - Verify that the sportService's updateSportById method was called
        verify(sportService).updateSportById(mockSport, sportId);
    }

    @Test
    public void testDeleteSportById() {
        // Arrange - Create a mock sport ID
        Long sportId = 1L;
        
        // Act - Call the method being tested
        sportWs.deleteSportById(sportId);

        // Assert - Verify that the sportService's deleteSportById method was called
        verify(sportService).deleteSportById(sportId);
    }
}
