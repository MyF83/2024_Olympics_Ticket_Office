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

import com.myriamfournier.olympics_ticket_office.pojo.ceremonies;
import com.myriamfournier.olympics_ticket_office.pojo.sites;
import com.myriamfournier.olympics_ticket_office.repository.CeremonyRepository;

/**
 * Unit tests for CeremonyServiceImpl
 */
@ExtendWith(MockitoExtension.class)
@SuppressWarnings("null")
public class CeremonyServiceImplUnitTest {

    // Mock the repository dependencies that CeremonyServiceImpl uses
    @Mock
    private CeremonyRepository ceremonyRepository;

    // Inject the mocks into the service implementation we're testing
    @InjectMocks
    private CeremonyServiceImpl ceremonyServiceImpl;

    @Test
    public void testGetAllCeremonies() {
        // Arrange
        List<ceremonies> ceremoniesList = Arrays.asList(new ceremonies(), new ceremonies());
        when(ceremonyRepository.findAllWithDetails()).thenReturn(ceremoniesList);
        
        // Act
        List<ceremonies> result = ceremonyServiceImpl.getAllCeremonies();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(ceremonyRepository).findAllWithDetails();
    }

    @Test
    public void testGetAllWithSites() {
        // Arrange
        sites mockSite = new sites();
        ceremonies ceremony1 = new ceremonies();
        ceremony1.setSites(mockSite);
        ceremonies ceremony2 = new ceremonies();
        ceremony2.setSites(mockSite);
        
        List<ceremonies> ceremoniesList = Arrays.asList(ceremony1, ceremony2);
        when(ceremonyRepository.findAllWithSites()).thenReturn(ceremoniesList);
        
        // Act
        List<ceremonies> result = ceremonyServiceImpl.getAllWithSites();
        
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertNotNull(result.get(0).getSites());
        assertNotNull(result.get(1).getSites());
        verify(ceremonyRepository).findAllWithSites();
    }

    @Test
    public void testGetCeremonyById() {
        // Arrange
        ceremonies mockCeremony = new ceremonies();
        mockCeremony.setCerem_id(1L);
        when(ceremonyRepository.findById(1L)).thenReturn(Optional.of(mockCeremony));
        
        // Act
        ceremonies result = ceremonyServiceImpl.getCeremonyById(1L);
        
        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getCerem_id());
        verify(ceremonyRepository).findById(1L);
    }

    @Test
    public void testCreateCeremony() {
        // Arrange
        ceremonies newCeremony = new ceremonies();
        newCeremony.setCerem_id(1L);
        when(ceremonyRepository.save(any(ceremonies.class))).thenReturn(newCeremony);
        
        // Act
        ceremonyServiceImpl.createCeremony(newCeremony);
        
        // Assert
        verify(ceremonyRepository).save(newCeremony);
    }

    @Test
    public void testUpdateCeremonyById() {
        // Arrange
        ceremonies existingCeremony = new ceremonies();
        existingCeremony.setCerem_id(1L);
        existingCeremony.setName("Original Name");
        existingCeremony.setDescription("Original Description");
        
        ceremonies updatedCeremony = new ceremonies();
        updatedCeremony.setName("Updated Name");
        updatedCeremony.setDescription("Updated Description");
        
        when(ceremonyRepository.findById(1L)).thenReturn(Optional.of(existingCeremony));
        when(ceremonyRepository.save(any(ceremonies.class))).thenReturn(existingCeremony);
        
        // Act
        ceremonyServiceImpl.updateCeremonyById(updatedCeremony, 1L);
        
        // Assert
        verify(ceremonyRepository).findById(1L);
        verify(ceremonyRepository).save(any(ceremonies.class));
    }


   @Test
    public void testDeleteCeremonyById(){
        // Arrange
        Long ceremonyId = 1L;
        
        // Act
        ceremonyServiceImpl.deleteCeremonyById(ceremonyId);
        
        // Assert
        verify(ceremonyRepository, times(1)).deleteById(ceremonyId);    
    }
}
