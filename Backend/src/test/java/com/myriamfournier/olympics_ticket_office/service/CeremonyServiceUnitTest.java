package com.myriamfournier.olympics_ticket_office.service;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myriamfournier.olympics_ticket_office.repository.CeremonyRepository;
import com.myriamfournier.olympics_ticket_office.service.impl.CeremonyServiceImpl;
import com.myriamfournier.olympics_ticket_office.pojo.ceremonies;

/**
 * Unit tests for CeremonyServiceImpl
 */
@ExtendWith(MockitoExtension.class)
public class CeremonyServiceUnitTest {

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
        verify(ceremonyRepository, times(1)).findAllWithDetails();
    }

    @Test
    public void testGetAllCeremoniesNotFound() {
        // Arrange
        when(ceremonyRepository.findAllWithDetails()).thenReturn(Arrays.asList());
        
        // Act
        List<ceremonies> result = ceremonyServiceImpl.getAllCeremonies();
        
        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
        verify(ceremonyRepository, times(1)).findAllWithDetails();
    }

    @Test
    public void testGetAllWithSites() {
        // Arrange
        List<ceremonies> ceremoniesList = Arrays.asList(new ceremonies(), new ceremonies());
        when(ceremonyRepository.findAllWithSites()).thenReturn(ceremoniesList);
        
        // Act
        List<ceremonies> result = ceremonyServiceImpl.getAllWithSites();
        
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(ceremonyRepository, times(1)).findAllWithSites();
    }

    @Test
    public void testGetAllWithSitesNotFound() {
        // Arrange
        when(ceremonyRepository.findAllWithSites()).thenReturn(Arrays.asList());
        
        // Act
        List<ceremonies> result = ceremonyServiceImpl.getAllWithSites();
        
        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
        verify(ceremonyRepository, times(1)).findAllWithSites();
    }   

    @Test
    public void testGetCeremonyById() {
        // Arrange
        Long ceremonyId = 1L;
        ceremonies ceremony = new ceremonies();
        when(ceremonyRepository.findById(ceremonyId)).thenReturn(Optional.of(ceremony));
        
        // Act
        ceremonies result = ceremonyServiceImpl.getCeremonyById(ceremonyId);
        
        // Assert
        assertNotNull(result);
        verify(ceremonyRepository, times(1)).findById(ceremonyId);
    }

    @Test
    public void testGetCeremonyByIdNotFound() {
        // Arrange
        Long ceremonyId = 1L;
        when(ceremonyRepository.findById(ceremonyId)).thenReturn(Optional.empty());
        
        // Act
        ceremonies result = ceremonyServiceImpl.getCeremonyById(ceremonyId);
        
        // Assert
        assertNull(result);
        verify(ceremonyRepository, times(1)).findById(ceremonyId);
    }

    @Test
    public void testCreateCeremony() {
        // Arrange
        ceremonies ceremony = new ceremonies();
        
        // Act
        ceremonyServiceImpl.createCeremony(ceremony);
        
        // Assert
        verify(ceremonyRepository, times(1)).save(ceremony);
    }

    @Test
    public void testUpdateCeremonyById() {
        // Arrange
        Long ceremonyId = 1L;
        ceremonies existingCeremony = new ceremonies();
        ceremonies updatedCeremony = new ceremonies();
        when(ceremonyRepository.findById(ceremonyId)).thenReturn(Optional.of(existingCeremony));
        
        // Act
        ceremonyServiceImpl.updateCeremonyById(updatedCeremony, ceremonyId);
        
        // Assert
        verify(ceremonyRepository, times(1)).findById(ceremonyId);
        verify(ceremonyRepository, times(1)).save(existingCeremony);
    }

    @Test
    public void testUpdateCeremonyByIdNotFound() {
        // Arrange
        Long ceremonyId = 1L;
        ceremonies updatedCeremony = new ceremonies();
        when(ceremonyRepository.findById(ceremonyId)).thenReturn(Optional.empty());
        
        // Act
        ceremonyServiceImpl.updateCeremonyById(updatedCeremony, ceremonyId);
        
        // Assert
        verify(ceremonyRepository, times(1)).findById(ceremonyId);
        verify(ceremonyRepository, times(0)).save(updatedCeremony);
    }

    @Test
    public void testDeleteCeremonyById() {
        // Arrange
        Long ceremonyId = 1L;
        
        // Act
        ceremonyServiceImpl.deleteCeremonyById(ceremonyId);
        
        // Assert
        verify(ceremonyRepository, times(1)).deleteById(ceremonyId);
    }
}
