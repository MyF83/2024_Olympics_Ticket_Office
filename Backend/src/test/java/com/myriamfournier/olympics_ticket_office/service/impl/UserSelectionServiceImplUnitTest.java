package com.myriamfournier.olympics_ticket_office.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myriamfournier.olympics_ticket_office.pojo.userselections;
import com.myriamfournier.olympics_ticket_office.pojo.events;
import com.myriamfournier.olympics_ticket_office.pojo.offers;
import com.myriamfournier.olympics_ticket_office.repository.UserSelectionRepository;



@ExtendWith(MockitoExtension.class)
public class UserSelectionServiceImplUnitTest {

    // Mock the repository dependencies that UserSelectionServiceImpl uses
    @Mock
    private UserSelectionRepository userSelectionRepository;

    // Inject the mocks into the service implementation we're testing
    @InjectMocks
    private UserSelectionServiceImpl userSelectionServiceImpl;

    @Test
    public void testGetAllUserSelections() {
        // Arrange
        List<userselections> userSelectionsList = Arrays.asList(new userselections(), new userselections());
        when(userSelectionRepository.findAllWithDetails()).thenReturn(userSelectionsList);
        
        // Act
        List<userselections> result = userSelectionServiceImpl.getAllUserSelections();
        
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(userSelectionRepository, times(1)).findAllWithDetails();
    }

    @Test
    public void testGetAllWithOffers() {
        // Arrange
        List<userselections> userSelectionsList = Arrays.asList(new userselections(), new userselections());
        when(userSelectionRepository.findAllWithOffers()).thenReturn(userSelectionsList);
        
        // Act
        List<userselections> result = userSelectionServiceImpl.getAllWithOffers();
        
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(userSelectionRepository, times(1)).findAllWithOffers();
    }

    @Test
    public void testGetAllWithEvents() {
        // Arrange
        List<userselections> userSelectionsList = Arrays.asList(new userselections(), new userselections());
        when(userSelectionRepository.findAllWithEvents()).thenReturn(userSelectionsList);
        
        // Act
        List<userselections> result = userSelectionServiceImpl.getAllWithEvents();
        
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(userSelectionRepository, times(1)).findAllWithEvents();
    }

    @Test
    public void testGetUserSelectionById() {
        // Arrange
        Long userId = 1L;
        userselections userSelection = new userselections();
        when(userSelectionRepository.findById(userId)).thenReturn(Optional.of(userSelection));
        
        // Act
        userselections result = userSelectionServiceImpl.getUserSelectionById(userId);
        
        // Assert
        assertNotNull(result);
        verify(userSelectionRepository, times(1)).findById(userId);
    }

    @Test
    public void testGetUserSelectionByIdNotFound() {
        // Arrange
        Long userId = 1L;
        when(userSelectionRepository.findById(userId)).thenReturn(Optional.empty());
        
        // Act
        userselections result = userSelectionServiceImpl.getUserSelectionById(userId);
        
        // Assert
        assertEquals(null, result);
        verify(userSelectionRepository, times(1)).findById(userId);
    }

    @Test
    public void testCreateUserSelection() {
        // Arrange
        userselections userSelection = new userselections();
        when(userSelectionRepository.save(any())).thenReturn(userSelection);
        
        // Act
        userSelectionServiceImpl.createUserSelection(userSelection);
        
        // Assert
        verify(userSelectionRepository, times(1)).save(userSelection);
    }

    @Test
    public void testUpdateUserSelectionById() {
        // Arrange
        Long userId = 1L;
        userselections existingSelection = new userselections();
        userselections updatedSelection = new userselections();
        updatedSelection.setNbPersons(5);
        
        when(userSelectionRepository.findById(userId)).thenReturn(Optional.of(existingSelection));
        when(userSelectionRepository.save(any())).thenReturn(existingSelection);
        
        // Act
        userSelectionServiceImpl.updateUserSelectionById(updatedSelection, userId);
        
        // Assert
        verify(userSelectionRepository, times(1)).findById(userId);
        verify(userSelectionRepository, times(1)).save(existingSelection);
    }

    @Test
    public void testUpdateUserSelectionByIdNotFound() {
        // Arrange
        Long userId = 1L;
        userselections updatedSelection = new userselections();
        when(userSelectionRepository.findById(userId)).thenReturn(Optional.empty());
        
        // Act
        userSelectionServiceImpl.updateUserSelectionById(updatedSelection, userId);
        
        // Assert
        verify(userSelectionRepository, times(1)).findById(userId);
        verify(userSelectionRepository, times(0)).save(any());
    }

    @Test
    public void testDeleteUserSelectionById() {
        // Act
        userSelectionServiceImpl.deleteUserSelectionById(1L);
        
        // Assert
        verify(userSelectionRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testCalculateAmount() {
        // Arrange
        userselections userSelection = new userselections();
        userSelection.setNbPersons(4);
        userSelection.setSeat_class("1");
        
        events event = new events();
        event.setPricec1(100.0f);
        userSelection.setEvents(event);
        
        offers offer = new offers();
        offer.setNbSpectators(2);
        offer.setRate(20); // 20% discount
        userSelection.setOffers(offer);
        
        // Act
        Float result = userSelectionServiceImpl.calculateAmount(userSelection);
        
        // Assert
        assertNotNull(result);
        // Expected: 2 extra persons * 100 + 2 offer persons * 100 * 0.8 = 200 + 160 = 360
        assertEquals(360.0f, result, 0.01f);
    }

    @Test
    public void testCalculateAmountWithMissingData() {
        // Arrange
        userselections userSelection = new userselections();
        // Missing required data
        
        // Act
        Float result = userSelectionServiceImpl.calculateAmount(userSelection);
        
        // Assert
        assertEquals(0.0f, result);
    }

    @Test
    public void testUpdateUserSelectionByIdWithAllFields() {
        // Arrange
        Long userId = 1L;
        userselections existingSelection = new userselections();
        
        events event = new events();
        offers offer = new offers();
        
        userselections updatedSelection = new userselections();
        updatedSelection.setNbPersons(5);
        updatedSelection.setAmount(100.0f);
        updatedSelection.setSeat_class("2");
        updatedSelection.setEvents(event);
        updatedSelection.setOffers(offer);
        
        when(userSelectionRepository.findById(userId)).thenReturn(Optional.of(existingSelection));
        when(userSelectionRepository.save(any())).thenReturn(existingSelection);
        
        // Act
        userSelectionServiceImpl.updateUserSelectionById(updatedSelection, userId);
        
        // Assert
        verify(userSelectionRepository, times(1)).findById(userId);
        verify(userSelectionRepository, times(1)).save(existingSelection);
    }

    @Test
    public void testUpdateUserSelectionByIdWithNullFields() {
        // Arrange
        Long userId = 1L;
        userselections existingSelection = new userselections();
        existingSelection.setNbPersons(3);
        existingSelection.setAmount(50.0f);
        
        userselections updatedSelection = new userselections();
        // All fields are null
        
        when(userSelectionRepository.findById(userId)).thenReturn(Optional.of(existingSelection));
        when(userSelectionRepository.save(any())).thenReturn(existingSelection);
        
        // Act
        userSelectionServiceImpl.updateUserSelectionById(updatedSelection, userId);
        
        // Assert
        verify(userSelectionRepository, times(1)).findById(userId);
        verify(userSelectionRepository, times(1)).save(existingSelection);
        // The existing values should be preserved since update fields are null
    }

    @Test
    public void testUpdateUserSelectionByIdWithPartialFields() {
        // Arrange
        Long userId = 1L;
        userselections existingSelection = new userselections();
        
        userselections updatedSelection = new userselections();
        updatedSelection.setNbPersons(5); // Only set this field
        // Other fields are null
        
        when(userSelectionRepository.findById(userId)).thenReturn(Optional.of(existingSelection));
        when(userSelectionRepository.save(any())).thenReturn(existingSelection);
        
        // Act
        userSelectionServiceImpl.updateUserSelectionById(updatedSelection, userId);
        
        // Assert
        verify(userSelectionRepository, times(1)).findById(userId);
        verify(userSelectionRepository, times(1)).save(existingSelection);
    }

    @Test
    public void testCalculateAmountWithDifferentSeatClasses() {
        // Test with seat class "2"
        userselections userSelection = new userselections();
        userSelection.setNbPersons(2);
        userSelection.setSeat_class("2");
        
        events event = new events();
        event.setPricec1(100.0f);
        event.setPricec2(80.0f);
        event.setPricec3(60.0f);
        userSelection.setEvents(event);
        
        offers offer = new offers();
        offer.setNbSpectators(1);
        offer.setRate(10); // 10% discount
        userSelection.setOffers(offer);
        
        // Act
        Float result = userSelectionServiceImpl.calculateAmount(userSelection);
        
        // Assert
        assertNotNull(result);
        // Expected: 1 extra person * 80 + 1 offer person * 80 * 0.9 = 80 + 72 = 152
        assertEquals(152.0f, result, 0.01f);
    }

    @Test
    public void testCalculateAmountWithSeatClass3() {
        // Test with seat class "3"
        userselections userSelection = new userselections();
        userSelection.setNbPersons(3);
        userSelection.setSeat_class("3");
        
        events event = new events();
        event.setPricec1(100.0f);
        event.setPricec2(80.0f);
        event.setPricec3(60.0f);
        userSelection.setEvents(event);
        
        offers offer = new offers();
        offer.setNbSpectators(2);
        offer.setRate(25); // 25% discount
        userSelection.setOffers(offer);
        
        // Act
        Float result = userSelectionServiceImpl.calculateAmount(userSelection);
        
        // Assert
        assertNotNull(result);
        // Expected: 1 extra person * 60 + 2 offer persons * 60 * 0.75 = 60 + 90 = 150
        assertEquals(150.0f, result, 0.01f);
    }

    @Test
    public void testCalculateAmountWithInvalidSeatClass() {
        // Test with invalid seat class - should default to price1
        userselections userSelection = new userselections();
        userSelection.setNbPersons(2);
        userSelection.setSeat_class("invalid");
        
        events event = new events();
        event.setPricec1(100.0f);
        event.setPricec2(80.0f);
        event.setPricec3(60.0f);
        userSelection.setEvents(event);
        
        offers offer = new offers();
        offer.setNbSpectators(1);
        offer.setRate(0); // No discount
        userSelection.setOffers(offer);
        
        // Act
        Float result = userSelectionServiceImpl.calculateAmount(userSelection);
        
        // Assert
        assertNotNull(result);
        // Expected: 1 extra person * 100 + 1 offer person * 100 * 1.0 = 100 + 100 = 200
        assertEquals(200.0f, result, 0.01f);
    }

    @Test
    public void testCalculateAmountWithNullEvent() {
        // Test with null event
        userselections userSelection = new userselections();
        userSelection.setNbPersons(2);
        userSelection.setSeat_class("1");
        userSelection.setEvents(null);
        
        offers offer = new offers();
        offer.setNbSpectators(1);
        offer.setRate(0);
        userSelection.setOffers(offer);
        
        // Act
        Float result = userSelectionServiceImpl.calculateAmount(userSelection);
        
        // Assert
        assertEquals(0.0f, result);
    }

    @Test
    public void testCalculateAmountWithNullOffer() {
        // Test with null offer
        userselections userSelection = new userselections();
        userSelection.setNbPersons(2);
        userSelection.setSeat_class("1");
        
        events event = new events();
        event.setPricec1(100.0f);
        userSelection.setEvents(event);
        userSelection.setOffers(null);
        
        // Act
        Float result = userSelectionServiceImpl.calculateAmount(userSelection);
        
        // Assert
        assertEquals(0.0f, result);
    }

    @Test
    public void testCalculateAmountWithZeroExtraPersons() {
        // Test when nb_persons <= offer.nb_spectators (no extra persons)
        userselections userSelection = new userselections();
        userSelection.setNbPersons(1);
        userSelection.setSeat_class("1");
        
        events event = new events();
        event.setPricec1(100.0f);
        userSelection.setEvents(event);
        
        offers offer = new offers();
        offer.setNbSpectators(2); // More spectators than persons
        offer.setRate(20); // 20% discount
        userSelection.setOffers(offer);
        
        // Act
        Float result = userSelectionServiceImpl.calculateAmount(userSelection);
        
        // Assert
        assertNotNull(result);
        // Expected: 0 extra persons + 2 offer persons * 100 * 0.8 = 0 + 160 = 160
        assertEquals(160.0f, result, 0.01f);
    }

}
