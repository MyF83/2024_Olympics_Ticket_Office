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

import com.myriamfournier.olympics_ticket_office.pojo.controls;
import com.myriamfournier.olympics_ticket_office.pojo.tickets;
import com.myriamfournier.olympics_ticket_office.repository.ControlRepository;

/**
 * Unit tests for ControlServiceImpl
 */
@ExtendWith(MockitoExtension.class)
@SuppressWarnings("null")
public class ControlServiceImplUnitTest {

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
        verify(controlRepository).findAllWithDetails();
    }

    @Test
    public void testGetAllWithTickets() {
        // Arrange
        tickets mockTicket = new tickets();
        controls control1 = new controls();
        control1.setTickets(mockTicket);
        controls control2 = new controls();
        control2.setTickets(mockTicket);
        
        List<controls> controlsList = Arrays.asList(control1, control2);
        when(controlRepository.findAllWithTickets()).thenReturn(controlsList);
        
        // Act
        List<controls> result = controlServiceImpl.getAllWithTickets();
        
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertNotNull(result.get(0).getTickets());
        assertNotNull(result.get(1).getTickets());
        verify(controlRepository).findAllWithTickets();
    }

    @Test
    public void testGetControlById() {
        // Arrange
        controls mockControl = new controls();
        mockControl.setControl_id(1L);
        when(controlRepository.findById(1L)).thenReturn(Optional.of(mockControl));
        
        // Act
        controls result = controlServiceImpl.getControlById(1L);
        
        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getControl_id());
        verify(controlRepository).findById(1L);
    }

    @Test
    public void testCreateControl() {
        // Arrange
        controls newControl = new controls();
        newControl.setControl_id(1L);
        when(controlRepository.save(any(controls.class))).thenReturn(newControl);
        
        // Act
        controlServiceImpl.createControl(newControl);
        
        // Assert
        verify(controlRepository).save(newControl);
    }

    @Test
    public void testUpdateControlById() {
        // Arrange
        controls existingControl = new controls();
        existingControl.setControl_id(1L);
        existingControl.setScancode("original-code");
        
        controls updatedControl = new controls();
        updatedControl.setScancode("updated-code");
        
        when(controlRepository.findById(1L)).thenReturn(Optional.of(existingControl));
        when(controlRepository.save(any(controls.class))).thenReturn(existingControl);
        
        // Act
        controlServiceImpl.updateControlById(updatedControl, 1L);
        
        // Assert
        verify(controlRepository).findById(1L);
        verify(controlRepository).save(any(controls.class));
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
