package com.myriamfournier.olympics_ticket_office.ws;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Timestamp;
import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myriamfournier.olympics_ticket_office.pojo.userselections;
import com.myriamfournier.olympics_ticket_office.service.UserSelectionService;
import com.myriamfournier.olympics_ticket_office.ws.UserSelectionWs;


@ExtendWith(MockitoExtension.class)
public class UserSelectionWsUnitTest {

 @Mock
    private UserSelectionService userSelectionService;

    @InjectMocks
    private UserSelectionWs userSelectionWs;

    @Test
    public void testUserSelectionWsCreation() {
        // Simple test to verify the UserSelectionWs can be instantiated
        assertNotNull(userSelectionWs, "UserSelectionWs should not be null");
    }


    @Test
    public void testgetAllUserSelectionsDefault() {
        // Arrange - Create mock user selections list
        userselections selection1 = new userselections();
        selection1.setUsersel_id(1L);

        
        userselections selection2 = new userselections();
        selection2.setUsersel_id(2L);

        
        List<userselections> mockSelections = Arrays.asList(selection1, selection2);
        
        when(userSelectionService.getAllUserSelections()).thenReturn(mockSelections);
        
        // Act - Call the method being tested
        List<userselections> result = userSelectionWs.getAllUserSelectionsDefault();
        
        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result size should be 2");
       
        // Verify that the service method was called
        verify(userSelectionService).getAllUserSelections();
    }

    @Test
    public void testgetAllWithOffers() {
        // Arrange - Create mock user selections list with offers
        userselections selection1 = new userselections();
        selection1.setUsersel_id(1L);
 
        
        userselections selection2 = new userselections();
        selection2.setUsersel_id(2L);

        
        List<userselections> mockSelectionsWithOffers = Arrays.asList(selection1, selection2);
        
        when(userSelectionService.getAllWithOffers()).thenReturn(mockSelectionsWithOffers);
        
        // Act - Call the method being tested
        List<userselections> result = userSelectionWs.getAllWithOffers();
        
        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result size should be 2");
        
        // Verify that the service method was called
        verify(userSelectionService).getAllWithOffers();
    }

    @Test
    public void testGetAllWithEvents() {
        // Arrange - Create mock user selections list with events
        userselections selection1 = new userselections();
        selection1.setUsersel_id(1L);
 
        
        userselections selection2 = new userselections();
        selection2.setUsersel_id(2L);

        
        List<userselections> mockSelectionsWithEvents = Arrays.asList(selection1, selection2);
        
        when(userSelectionService.getAllWithEvents()).thenReturn(mockSelectionsWithEvents);
        
        // Act - Call the method being tested
        List<userselections> result = userSelectionWs.getAllWithEvents();
        
        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result size should be 2");
        
        // Verify that the service method was called
        verify(userSelectionService).getAllWithEvents();
    }


    @Test
    public void testGetUserSelectionById() {
        // Arrange - Create mock user selection
        Long selectionId = 1L;
        userselections mockSelection = new userselections();
        mockSelection.setUsersel_id(selectionId);
        
        when(userSelectionService.getUserSelectionById(selectionId)).thenReturn(mockSelection);
        
        // Act - Call the method being tested
        userselections result = userSelectionWs.getUserSelectionById(selectionId);
        
        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(selectionId, result.getUsersel_id(), "User selection ID should match");
        
        // Verify that the service method was called
        verify(userSelectionService).getUserSelectionById(selectionId);
    }

    @Test
    public void testCreateUserSelection() {
        // Arrange - Create mock user selection to be created
        userselections mockSelection = new userselections();
        mockSelection.setUsersel_id(1L);
        
        // Act - Call the method being tested
        userSelectionWs.createUserSelection(mockSelection);
        
        // Assert - Verify that the service method was called with correct parameter
        verify(userSelectionService).createUserSelection(mockSelection);
    }

    @Test
    public void testUpdateUserSelectionById() {
        // Arrange - Create mock user selection to be updated
        Long selectionId = 1L;
        userselections mockSelection = new userselections();
        mockSelection.setUsersel_id(selectionId);

        // Act - Call the method being tested
        userSelectionWs.updateUserSelectionById(selectionId, mockSelection);

        // Assert - Verify that the service method was called with correct parameters
        verify(userSelectionService).updateUserSelectionById(mockSelection, selectionId);
        }


        @Test
    public void testDeleteUserSelectionById() {
        // Arrange - Set up selection ID for deletion
        Long selectionId = 1L;

        // Act - Call the method being tested
        userSelectionWs.deleteUserSelectionById(selectionId);

        // Assert - Verify that the service method was called with correct parameter
        verify(userSelectionService).deleteUserSelectionById(selectionId);
    }
}
