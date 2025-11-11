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

import com.myriamfournier.olympics_ticket_office.repository.ControlRepository;
import com.myriamfournier.olympics_ticket_office.service.impl.ControlServiceImpl;
import com.myriamfournier.olympics_ticket_office.pojo.controls;

/**
 * Unit tests for ControlServiceImpl
 */
@ExtendWith(MockitoExtension.class)
public class ControlServiceUnitTest {

    // Mock the repository dependencies that ControlServiceImpl uses
    @Mock
    private ControlRepository controlRepository;

    // Inject the mocks into the service implementation we're testing
    @InjectMocks
    private ControlServiceImpl controlServiceImpl;

    @Test
    public void testGetAllControls() {
        // Arrange
        List<controls> controlsList = Arrays.asList(new controls(), new controls());
        when(controlRepository.findAllWithDetails()).thenReturn(controlsList);
        
        // Act
        List<controls> result = controlServiceImpl.getAllControls();
        
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(controlRepository, times(1)).findAllWithDetails();
    }

    @Test
    public void testGetAllWithTickets() {
        // Arrange
        List<controls> controlsList = Arrays.asList(new controls(), new controls());
        when(controlRepository.findAllWithTickets()).thenReturn(controlsList);
        
        // Act
        List<controls> result = controlServiceImpl.getAllWithTickets();
        
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(controlRepository, times(1)).findAllWithTickets();
    }

    @Test
    public void testGetControlById() {
        // Arrange
        controls control = new controls();
        when(controlRepository.findById(1L)).thenReturn(Optional.of(control));
        
        // Act
        controls result = controlServiceImpl.getControlById(1L);
        
        // Assert
        assertNotNull(result);
        verify(controlRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetControlByIdNotFound() {
        // Arrange
        when(controlRepository.findById(1L)).thenReturn(Optional.empty());
        
        // Act
        controls result = controlServiceImpl.getControlById(1L);
        
        // Assert
        assertEquals(null, result);
        verify(controlRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateControl() {
        // Arrange
        controls control = new controls();
        
        // Act
        controlServiceImpl.createControl(control);
        
        // Assert
        verify(controlRepository, times(1)).save(control);
    }

    @Test
    public void testUpdateControlById() {
        // Arrange
        controls existingControl = new controls();
        controls updatedControl = new controls();
        when(controlRepository.findById(1L)).thenReturn(Optional.of(existingControl));
        
        // Act
        controlServiceImpl.updateControlById(updatedControl, 1L);
        
        // Assert
        verify(controlRepository, times(1)).findById(1L);
        verify(controlRepository, times(1)).save(existingControl);
    }

    @Test
    public void testUpdateControlByIdNotFound() {
        // Arrange
        controls updatedControl = new controls();
        when(controlRepository.findById(1L)).thenReturn(Optional.empty());
        
        // Act
        controlServiceImpl.updateControlById(updatedControl, 1L);
        
        // Assert
        verify(controlRepository, times(1)).findById(1L);
        verify(controlRepository, times(0)).save(updatedControl);
    }

    @Test
    public void testDeleteControlById() {
        // Arrange
        Long controlId = 1L;
        
        // Act
        controlServiceImpl.deleteControlById(controlId);
        
        // Assert
        verify(controlRepository, times(1)).deleteById(controlId);
    }
}
