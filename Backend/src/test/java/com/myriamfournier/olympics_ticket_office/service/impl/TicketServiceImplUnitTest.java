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

import com.myriamfournier.olympics_ticket_office.pojo.tickets;
import com.myriamfournier.olympics_ticket_office.repository.TicketRepository;


@ExtendWith(MockitoExtension.class)
public class TicketServiceImplUnitTest {

    // Mock the repository dependencies that TicketServiceImpl uses
    @Mock
    private TicketRepository ticketRepository;

    // Inject the mocks into the service implementation we're testing
    @InjectMocks
    private TicketServiceImpl ticketServiceImpl;

    @Test
    public void testGetAllTickets() {
        // Arrange
        List<tickets> ticketsList = Arrays.asList(new tickets(), new tickets());
        when(ticketRepository.findAll()).thenReturn(ticketsList);
        
        // Act
        List<tickets> result = ticketServiceImpl.getAllTickets();
        
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllWithSales() {
        // Arrange
        List<tickets> ticketsList = Arrays.asList(new tickets(), new tickets());
        when(ticketRepository.findAll()).thenReturn(ticketsList);
        
        // Act
        List<tickets> result = ticketServiceImpl.getAllWithSales();
        
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllWithDetails() {
        // Arrange
        List<tickets> ticketsList = Arrays.asList(new tickets(), new tickets());
        when(ticketRepository.findAll()).thenReturn(ticketsList);
        
        // Act
        List<tickets> result = ticketServiceImpl.getAllWithDetails();
        
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    public void testGetTicketsByUserId() {
        // Arrange
        Long userId = 1L;
        List<tickets> ticketsList = Arrays.asList(new tickets(), new tickets());
        when(ticketRepository.findByUserId(userId)).thenReturn(ticketsList);
        
        // Act
        List<tickets> result = ticketServiceImpl.getTicketsByUserId(userId);
        
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(ticketRepository, times(1)).findByUserId(userId);
    }

    @Test
    public void testGetTicketsBySaleId() {
        // Arrange
        Long saleId = 1L;
        List<tickets> ticketsList = Arrays.asList(new tickets(), new tickets());
        when(ticketRepository.findBySaleId(saleId)).thenReturn(ticketsList);
        
        // Act
        List<tickets> result = ticketServiceImpl.getTicketsBySaleId(saleId);
        
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(ticketRepository, times(1)).findBySaleId(saleId);
    }

    @Test
    public void testGetTicketById() {
        // Arrange
        tickets ticket = new tickets();
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        
        // Act
        tickets result = ticketServiceImpl.getTicketById(1L);
        
        // Assert
        assertNotNull(result);
        verify(ticketRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetTicketByIdNotFound() {
        // Arrange
        when(ticketRepository.findById(1L)).thenReturn(Optional.empty());
        
        // Act
        tickets result = ticketServiceImpl.getTicketById(1L);
        
        // Assert
        assertEquals(null, result);
        verify(ticketRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindByTicketNumber() {
        // Arrange
        tickets ticket = new tickets();
        when(ticketRepository.findByTicketNumber("TICKET123")).thenReturn(Optional.of(ticket));
        
        // Act
        tickets result = ticketServiceImpl.findByTicketNumber("TICKET123");
        
        // Assert
        assertNotNull(result);
        verify(ticketRepository, times(1)).findByTicketNumber("TICKET123");
    }

    @Test
    public void testFindByTicketNumberNotFound() {
        // Arrange
        when(ticketRepository.findByTicketNumber("TICKET123")).thenReturn(Optional.empty());
        
        // Act
        tickets result = ticketServiceImpl.findByTicketNumber("TICKET123");
        
        // Assert
        assertEquals(null, result);
        verify(ticketRepository, times(1)).findByTicketNumber("TICKET123");
    }

    @Test
    public void testCreateTicket() {
        // Arrange
        tickets newTicket = new tickets();
        when(ticketRepository.save(any())).thenReturn(newTicket);
        
        // Act
        ticketServiceImpl.createTicket(newTicket);
        
        // Assert
        verify(ticketRepository, times(1)).save(newTicket);
    }

    @Test
    public void testUpdateTicketById() {
        // Arrange
        tickets ticket = new tickets();
        when(ticketRepository.save(any())).thenReturn(ticket);
        
        // Act
        ticketServiceImpl.updateTicketById(ticket, 1L);
        
        // Assert
        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    public void testUpdateTicket() {
        // Arrange
        tickets ticket = new tickets();
        when(ticketRepository.save(any())).thenReturn(ticket);
        
        // Act
        ticketServiceImpl.updateTicket(ticket);
        
        // Assert
        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    public void testDeleteTicketById() {
        // Arrange & Act
        ticketServiceImpl.deleteTicketById(1L);
        
        // Assert
        verify(ticketRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGenerateUnique40CharacterName() {
        // Act
        String uniqueName = ticketServiceImpl.generateUnique40CharacterName();
        
        // Assert
        assertNotNull(uniqueName);
        assertEquals(40, uniqueName.length());
    }

}
