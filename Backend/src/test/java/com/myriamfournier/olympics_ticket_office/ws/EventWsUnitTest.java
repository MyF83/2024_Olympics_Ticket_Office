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


import com.myriamfournier.olympics_ticket_office.pojo.events;
import com.myriamfournier.olympics_ticket_office.service.EventService;

@ExtendWith(MockitoExtension.class)
public class EventWsUnitTest {

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventWs eventWs;


    @Test
    public void testGetAllEventsDefault() { 
        // Arrange - Create mock events list
        events event1 = new events();
        event1.setEvent_id(1L);

        events event2 = new events();
        event2.setEvent_id(2L);

        List<events> mockEvents = Arrays.asList(event1, event2);

        when(eventService.getAllEvents()).thenReturn(mockEvents);

        // Act - Call the method being tested
        List<events> result = eventWs.getAllEventsDefault();

        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result size should be 2");

        // Verify that the service method was called
        verify(eventService).getAllEvents();
    }


    @Test
    public void testGetAllWithSports() {
        // Arrange - Create mock events list with sports    
        events event1 = new events();
        event1.setEvent_id(1L);

        events event2 = new events();
        event2.setEvent_id(2L);

        List<events> mockEvents = Arrays.asList(event1, event2);

        when(eventService.getAllWithSports()).thenReturn(mockEvents);

        // Act - Call the method being tested
        List<events> result = eventWs.getAllWithSports();

        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result size should be 2");

        // Verify that the service method was called
        verify(eventService).getAllWithSports();
    }   


    @Test
    public void testGetAllWithCeremonies() {
        // Arrange - Create mock events list with ceremonies    
        events event1 = new events();
        event1.setEvent_id(1L);

        events event2 = new events();
        event2.setEvent_id(2L);

        List<events> mockEvents = Arrays.asList(event1, event2);

        when(eventService.getAllWithCeremonies()).thenReturn(mockEvents);

        // Act - Call the method being tested
        List<events> result = eventWs.getAllWithCeremonies();

        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result size should be 2");

        // Verify that the service method was called
        verify(eventService).getAllWithCeremonies();
    }   


    @Test
    public void testGetAllWithChallenger1() {
        // Arrange - Create mock events list with challenger1    
        events event1 = new events();
        event1.setEvent_id(1L);

        events event2 = new events();
        event2.setEvent_id(2L);

        List<events> mockEvents = Arrays.asList(event1, event2);

        when(eventService.getAllWithChallenger1()).thenReturn(mockEvents);

        // Act - Call the method being tested
        List<events> result = eventWs.getAllWithChallenger1();

        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result size should be 2");

        // Verify that the service method was called
        verify(eventService).getAllWithChallenger1();
    }

    @Test
    public void testGetAllWithChallenger2() {
        // Arrange - Create mock events list with challenger2    
        events event1 = new events();
        event1.setEvent_id(1L);

        events event2 = new events();
        event2.setEvent_id(2L);

        List<events> mockEvents = Arrays.asList(event1, event2);

        when(eventService.getAllWithChallenger2()).thenReturn(mockEvents);

        // Act - Call the method being tested
        List<events> result = eventWs.getAllWithChallenger2();

        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result size should be 2");

        // Verify that the service method was called
        verify(eventService).getAllWithChallenger2();
    }   

    @Test
    public void testGetAllEvents()  { 
        // Arrange - Create mock events list
        events event1 = new events();
        event1.setEvent_id(1L);

        events event2 = new events();
        event2.setEvent_id(2L);

        List<events> mockEvents = Arrays.asList(event1, event2);

        when(eventService.getAllEvents()).thenReturn(mockEvents);

        // Act - Call the method being tested
        List<events> result = eventWs.getAllEvents();

        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result size should be 2");

        // Verify that the service method was called
        verify(eventService).getAllEvents();
    }   

    @Test
    public void testGetEventById() {
        // Arrange - Create a mock event
        Long eventId = 1L;
        events mockEvent = new events();
        mockEvent.setEvent_id(eventId);

        when(eventService.getEventById(eventId)).thenReturn(mockEvent);

        // Act - Call the method being tested
        events result = eventWs.getEventById(eventId);

        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(eventId, result.getEvent_id(), "Event ID should match");

        // Verify that the service method was called
        verify(eventService).getEventById(eventId);
    }   


    @Test
    public void testCreateEvent() {
        // Arrange - Create a mock event
        events newEvent = new events();
        newEvent.setEvent_id(1L);

        // Act - Call the method being tested
        eventWs.createEvent(newEvent);

        // Assert - Verify that the service method was called
        verify(eventService).createEvent(newEvent);
    }

    @Test
    public void testUpdateEventById() {
        // Arrange - Create a mock event
        Long eventId = 1L;
        events updatedEvent = new events();
        updatedEvent.setEvent_id(eventId);

        // Act - Call the method being tested
        eventWs.updateEventById(eventId, updatedEvent);

        // Assert - Verify that the service method was called with correct parameter order
        verify(eventService).updateEventById(eventId, updatedEvent);
    }

    @Test
    public void testDeleteEventById() {
        // Arrange - Create a mock event ID
        Long eventId = 1L;

        // Act - Call the method being tested
        eventWs.deleteEventById(eventId);

        // Assert - Verify that the service method was called
        verify(eventService).deleteEventById(eventId);
    }   
}
