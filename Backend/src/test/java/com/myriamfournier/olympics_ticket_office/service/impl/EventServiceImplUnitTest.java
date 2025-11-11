package com.myriamfournier.olympics_ticket_office.service.impl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myriamfournier.olympics_ticket_office.repository.EventRepository;
import com.myriamfournier.olympics_ticket_office.pojo.events;


@ExtendWith(MockitoExtension.class)
public class EventServiceImplUnitTest {


    // Mock the repository dependencies that CartServiceImpl uses
    @Mock
    private EventRepository eventRepository;



    // Inject the mocks into the service implementation we're testing
    @InjectMocks
    private EventServiceImpl eventServiceImpl;

    @Test
    public void testGetAllEvents() {
        // Arrange
        List<com.myriamfournier.olympics_ticket_office.pojo.events> eventsList = Arrays.asList(new com.myriamfournier.olympics_ticket_office.pojo.events(), new com.myriamfournier.olympics_ticket_office.pojo.events());
        when(eventRepository.findAllWithDetails()).thenReturn(eventsList);
        // Act
        List<com.myriamfournier.olympics_ticket_office.pojo.events> result = eventServiceImpl.getAllEvents();
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

      @Test
    public void testGetAllWithSports() {
        // Arrange
        List<com.myriamfournier.olympics_ticket_office.pojo.events> eventsList = Arrays.asList(new com.myriamfournier.olympics_ticket_office.pojo.events(), new com.myriamfournier.olympics_ticket_office.pojo.events());
        when(eventRepository.findAllWithSports()).thenReturn(eventsList);
        // Act
        List<com.myriamfournier.olympics_ticket_office.pojo.events> result = eventServiceImpl.getAllWithSports();
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

      @Test
    public void testGetAllWithCeremonies() {
        // Arrange
        List<com.myriamfournier.olympics_ticket_office.pojo.events> eventsList = Arrays.asList(new com.myriamfournier.olympics_ticket_office.pojo.events(), new com.myriamfournier.olympics_ticket_office.pojo.events());
        when(eventRepository.findAllWithCeremonies()).thenReturn(eventsList);
        // Act
        List<com.myriamfournier.olympics_ticket_office.pojo.events> result = eventServiceImpl.getAllWithCeremonies();
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

      @Test
    public void testGetAllWithChallenger1() {
        // Arrange
        List<com.myriamfournier.olympics_ticket_office.pojo.events> eventsList = Arrays.asList(new com.myriamfournier.olympics_ticket_office.pojo.events(), new com.myriamfournier.olympics_ticket_office.pojo.events());
        when(eventRepository.findAllWithChallenger1()).thenReturn(eventsList);  
        // Act
        List<com.myriamfournier.olympics_ticket_office.pojo.events> result = eventServiceImpl.getAllWithChallenger1();
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size()); 
      }

      @Test
    public void testGetAllWithChallenger2() {
        // Arrange
        List<com.myriamfournier.olympics_ticket_office.pojo.events> eventsList = Arrays.asList(new com.myriamfournier.olympics_ticket_office.pojo.events(), new com.myriamfournier.olympics_ticket_office.pojo.events());
        when(eventRepository.findAllWithChallenger2()).thenReturn(eventsList);
        // Act
        List<com.myriamfournier.olympics_ticket_office.pojo.events> result = eventServiceImpl.getAllWithChallenger2();
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

      

      @Test
    public void testGetEventById() {
        // Arrange
        com.myriamfournier.olympics_ticket_office.pojo.events mockEvent = new com.myriamfournier.olympics_ticket_office.pojo.events();
        when(eventRepository.findById(1L)).thenReturn(Optional.of(mockEvent));
        // Act
        com.myriamfournier.olympics_ticket_office.pojo.events result = eventServiceImpl.getEventById(1L);
        // Assert
        assertNotNull(result);
        assertEquals(mockEvent, result);
    }

      @Test
    public void testCreateEvent() {
        // Arrange
        com.myriamfournier.olympics_ticket_office.pojo.events newEvent = new com.myriamfournier.olympics_ticket_office.pojo.events();
        when(eventRepository.save(any(events.class))).thenReturn(newEvent);
        // Act
        eventServiceImpl.createEvent(newEvent);
        // Assert
        verify(eventRepository).save(newEvent);
    }

      @Test
    public void testUpdateEventById() {
        // Arrange
        com.myriamfournier.olympics_ticket_office.pojo.events existingEvent = new com.myriamfournier.olympics_ticket_office.pojo.events();
        when(eventRepository.findById(1L)).thenReturn(Optional.of(existingEvent));
        when(eventRepository.save(any(events.class))).thenReturn(existingEvent);
        // Act
        eventServiceImpl.updateEventById(1L, existingEvent); 
        // Assert
        verify(eventRepository).save(any(events.class));
    }

      @Test
    public void testDeleteEventById() {
        // Arrange
        Long eventId = 1L;
        // Act
        eventServiceImpl.deleteEventById(eventId);  
        // Assert
        verify(eventRepository).deleteById(eventId);
    }

    @Test
    public void testUpdateEventByIdWithNullEvent() {
        // Test the branch where getEventById returns null
        // Arrange
        com.myriamfournier.olympics_ticket_office.pojo.events updateEvent = new com.myriamfournier.olympics_ticket_office.pojo.events();
        when(eventRepository.findById(1L)).thenReturn(Optional.empty());
        
        // Act
        eventServiceImpl.updateEventById(1L, updateEvent);
        
        // Assert
        verify(eventRepository).findById(1L);
        verify(eventRepository, never()).save(any(events.class)); // Should not save anything
    }

}
