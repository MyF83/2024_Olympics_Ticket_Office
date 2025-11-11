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

import com.myriamfournier.olympics_ticket_office.pojo.controls;
import com.myriamfournier.olympics_ticket_office.service.ControlService;


/**
 * Unit tests for CartWs REST controller
 */
@ExtendWith(MockitoExtension.class)
public class ControlWsUnitTest {

 @Mock
    private ControlService controlService;

    @InjectMocks
    private ControlWs controlWs;

    @Test
    public void testGetAllControlsDefault() {
        // Test implementation
        when(controlService.getAllControls()).thenReturn(Arrays.asList(new controls(), new controls()));
        List<controls> response = controlWs.getAllControlsDefault();
        assertNotNull(response);
        assertEquals(2, response.size());
        verify(controlService).getAllControls();
    }

    @Test
    public void testGetAllWithTickets() {
        // Test implementation
        when(controlService.getAllWithTickets()).thenReturn(Arrays.asList(new controls(), new controls()));
        List<controls> response = controlWs.getAllWithTickets();
        assertNotNull(response);
        assertEquals(2, response.size());
        verify(controlService).getAllWithTickets();
    }





    @Test
    public void testGetControlById() {
        // Test implementation
        controls mockControl = new controls();
        mockControl.setControl_id(1L);
        when(controlService.getControlById(1L)).thenReturn(mockControl);
        
        controls response = controlWs.getControlById(1L);
        
        assertNotNull(response);
        assertEquals(1L, response.getControl_id());
        verify(controlService).getControlById(1L);
    }

    @Test
    public void testCreateControl() {
        // Test implementation
        controls newControl = new controls();
        newControl.setControl_id(3L);
        
        doNothing().when(controlService).createControl(newControl);
        
        controlWs.createControl(newControl);
        
        verify(controlService).createControl(newControl);
    }

        @Test
    public void testUpdateControlById() {
        controls control = new controls();
        control.setControl_id(1L);
        
        // Mock void method
        doNothing().when(controlService).updateControlById(control, 1L);
        
        // Call void method
        controlWs.updateControlById(1L, control);
        
        // Verify service method was called
        verify(controlService).updateControlById(control, 1L);
    }

     @Test
     public void testDeleteControlById() {
     // Test implementation
        controlWs.deleteControlById(1L);
        verify(controlService).deleteControlById(1L);
    }
}