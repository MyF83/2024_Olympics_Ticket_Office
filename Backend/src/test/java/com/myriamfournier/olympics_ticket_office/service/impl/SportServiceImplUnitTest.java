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

import com.myriamfournier.olympics_ticket_office.pojo.sports;
import com.myriamfournier.olympics_ticket_office.repository.SportRepository;

@ExtendWith(MockitoExtension.class)
public class SportServiceImplUnitTest {

    // Mock the repository dependencies that SportServiceImpl uses
    @Mock
    private SportRepository sportRepository;

    // Inject the mocks into the service implementation we're testing
    @InjectMocks
    private SportServiceImpl sportServiceImpl;

    @Test
    public void testGetAllSports() {
        // Arrange
        List<sports> sportsList = Arrays.asList(new sports(), new sports());
        when(sportRepository.findAllWithDetails()).thenReturn(sportsList);
        
        // Act
        List<sports> result = sportServiceImpl.getAllSports();
        
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(sportRepository, times(1)).findAllWithDetails();
    }

    @Test
    public void testGetAllWithSites() {
        // Arrange
        List<sports> sportsList = Arrays.asList(new sports(), new sports());
        when(sportRepository.findAllWithSites()).thenReturn(sportsList);
        
        // Act
        List<sports> result = sportServiceImpl.getAllWithSites();
        
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(sportRepository, times(1)).findAllWithSites();
    }

    @Test
    public void testGetSportById() {
        // Arrange
        sports sport = new sports();
        when(sportRepository.findById(1L)).thenReturn(Optional.of(sport));
        
        // Act
        sports result = sportServiceImpl.getSportById(1L);
        
        // Assert
        assertNotNull(result);
        verify(sportRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetSportByIdNotFound() {
        // Arrange
        when(sportRepository.findById(1L)).thenReturn(Optional.empty());
        
        // Act
        sports result = sportServiceImpl.getSportById(1L);
        
        // Assert
        assertEquals(null, result);
        verify(sportRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateSport() {
        // Arrange
        sports sport = new sports();
        when(sportRepository.save(any())).thenReturn(sport);
        
        // Act
        sportServiceImpl.createSport(sport);
        
        // Assert
        verify(sportRepository, times(1)).save(sport);
    }

    @Test
    public void testUpdateSportById() {
        // Arrange
        sports existingSport = new sports();
        sports updatedSport = new sports();
        updatedSport.setName("Updated Name");
        updatedSport.setDescription("Updated Description");
        
        when(sportRepository.findById(1L)).thenReturn(Optional.of(existingSport));
        when(sportRepository.save(any())).thenReturn(existingSport);
        
        // Act
        sportServiceImpl.updateSportById(updatedSport, 1L);
        
        // Assert
        verify(sportRepository, times(1)).findById(1L);
        verify(sportRepository, times(1)).save(existingSport);
    }

    @Test
    public void testUpdateSportByIdNotFound() {
        // Arrange
        sports updatedSport = new sports();
        when(sportRepository.findById(1L)).thenReturn(Optional.empty());
        
        // Act
        sportServiceImpl.updateSportById(updatedSport, 1L);
        
        // Assert
        verify(sportRepository, times(1)).findById(1L);
        verify(sportRepository, times(0)).save(any());
    }

    @Test
    public void testDeleteSportById() {
        // Act
        sportServiceImpl.deleteSportById(1L);
        
        // Assert
        verify(sportRepository, times(1)).deleteById(1L);
    }

}
