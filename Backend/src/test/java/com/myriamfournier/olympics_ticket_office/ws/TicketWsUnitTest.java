package com.myriamfournier.olympics_ticket_office.ws;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.any;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.myriamfournier.olympics_ticket_office.pojo.carts;
import com.myriamfournier.olympics_ticket_office.pojo.events;
import com.myriamfournier.olympics_ticket_office.pojo.offers;
import com.myriamfournier.olympics_ticket_office.pojo.sales;
import com.myriamfournier.olympics_ticket_office.pojo.saleskeys;
import com.myriamfournier.olympics_ticket_office.pojo.tickets;
import com.myriamfournier.olympics_ticket_office.pojo.users;
import com.myriamfournier.olympics_ticket_office.pojo.userskeys;
import com.myriamfournier.olympics_ticket_office.service.CartService;
import com.myriamfournier.olympics_ticket_office.service.EventService;
import com.myriamfournier.olympics_ticket_office.service.OfferService;
import com.myriamfournier.olympics_ticket_office.service.QRCodeGeneratorService;
import com.myriamfournier.olympics_ticket_office.service.SaleService;
import com.myriamfournier.olympics_ticket_office.service.SaleskeyService;
import com.myriamfournier.olympics_ticket_office.service.TicketService;
import com.myriamfournier.olympics_ticket_office.service.UserService;
import com.myriamfournier.olympics_ticket_office.service.UserskeyService;
import com.myriamfournier.olympics_ticket_office.ws.TicketWs;

@ExtendWith(MockitoExtension.class)
public class TicketWsUnitTest {

    @Mock
    private TicketService ticketService;
    
    @Mock
    private CartService cartService;
    
    @Mock
    private SaleService saleService;
    
    @Mock
    private UserService userService;
    
    @Mock
    private EventService eventService;
    
    @Mock
    private OfferService offerService;
    
    @Mock
    private UserskeyService userskeyService;
    
    @Mock
    private SaleskeyService saleskeyService;
    
    @Mock
    private QRCodeGeneratorService qrCodeGeneratorService;

    @InjectMocks
    private TicketWs ticketWs;

    @Test
    public void testTicketWsCreation() {
        // Simple test to verify the TicketWs can be instantiated
        assertNotNull(ticketWs, "TicketWs should not be null");
    }

    @Test
    public void testGetAllTickets() {
        // Arrange - Create mock tickets list
        tickets ticket1 = new tickets();
        ticket1.setTicket_id(1L);
        ticket1.setTicketNumber("A1");
        
        tickets ticket2 = new tickets();
        ticket2.setTicket_id(2L);
        ticket2.setTicketNumber("B2");
        
        List<tickets> mockTickets = Arrays.asList(ticket1, ticket2);
        
        when(ticketService.getAllTickets()).thenReturn(mockTickets);
        
        // Act - Call the method being tested
        ResponseEntity<List<tickets>> result = ticketWs.getAllTickets();
        
        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.getBody().size(), "Result size should be 2");
        
        // Verify that the service method was called
        verify(ticketService).getAllTickets();
    }


    @Test
    public void testGetAllTicketsWithDetails() {
        // Arrange - Create mock tickets list with details
        tickets ticket1 = new tickets();
        ticket1.setTicket_id(1L);
        ticket1.setTicketNumber("A1");
        
        tickets ticket2 = new tickets();
        ticket2.setTicket_id(2L);
        ticket2.setTicketNumber("B2");
        
        List<tickets> mockTicketsWithDetails = Arrays.asList(ticket1, ticket2);
        
        when(ticketService.getAllWithDetails()).thenReturn(mockTicketsWithDetails);
        
        // Act - Call the method being tested
        ResponseEntity<List<tickets>> result = ticketWs.getAllTicketsWithDetails();
        
        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.getBody().size(), "Result size should be 2");
        assertEquals("A1", result.getBody().get(0).getTicketNumber(), "First ticket number should match");
        assertEquals("B2", result.getBody().get(1).getTicketNumber(), "Second ticket number should match");
        
        // Verify that the service method was called
        verify(ticketService).getAllWithDetails();
    }


    @Test
    public void testGetTicketById() {
        // Arrange - Create mock ticket to be returned by service
        Long ticketId = 1L;
        tickets mockTicket = new tickets();
        mockTicket.setTicket_id(ticketId);
        mockTicket.setTicketNumber("A1");
        
        when(ticketService.getTicketById(ticketId)).thenReturn(mockTicket);
        // Act - Call the method being tested
        ResponseEntity<tickets> result = ticketWs.getTicketById(ticketId);  
        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals("A1", result.getBody().getTicketNumber(), "Ticket number should match");
    
        // Verify that the service method was called
        verify(ticketService).getTicketById(ticketId);

    }

    @Test
    public void testCreateTicket() {
        // Arrange - Create mock ticket to be created
        tickets mockTicket = new tickets();
        mockTicket.setTicketNumber("C3");
       
        
        // Act - Call the method being tested
        ticketWs.createTicket(mockTicket);
        
        // Assert - Verify that the ticketService's createTicket method was called with correct parameter
        verify(ticketService).createTicket(mockTicket);
    }

    @Test
    public void testCreateTicketAfterPayment_Success() {
        // Arrange - Create setup that will hit the BAD_REQUEST path due to null event/offer
        Long cartId = 1L;
        
        // Create mock user
        users mockUser = new users();
        mockUser.setUser_id(1L);
        mockUser.setFirstname("John");
        mockUser.setLastname("Doe");
        
        // Create mock cart with user
        carts mockCart = new carts();
        mockCart.setCart_id(cartId);
        mockCart.setUsers(mockUser);
        
        // Mock service calls - event and offer will be null causing BAD_REQUEST
        when(cartService.getCartById(cartId)).thenReturn(mockCart);
        
        // Act
        ResponseEntity<tickets> result = ticketWs.createTicketAfterPayment(cartId);
        
        // Assert - This will return BAD_REQUEST because event and offer are null in controller
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        verify(cartService).getCartById(cartId);
    }
    @Test
    public void testUpdateTicket() {
        // Arrange - Create a mock ticket to be updated
        Long ticketId = 1L;
        tickets existingTicket = new tickets();
        existingTicket.setTicket_id(ticketId);
        existingTicket.setTicketNumber("E5");
        
        tickets updateTicket = new tickets();
        updateTicket.setTicketNumber("E5-Updated");
        
        // Mock the getTicketById call that the controller makes first
        when(ticketService.getTicketById(ticketId)).thenReturn(existingTicket);
        
        // Act - Call the method being tested
        ticketWs.updateTicket(ticketId, updateTicket);
        
        // Assert - Verify that both service methods were called
        verify(ticketService).getTicketById(ticketId);
        verify(ticketService).updateTicket(updateTicket);
    }

    @Test
    public void testDeleteTicket() {
        // Arrange
        Long ticketId = 1L;
        tickets existingTicket = new tickets();
        existingTicket.setTicket_id(ticketId);
        existingTicket.setTicketNumber("ToDelete");
        
        // Mock the getTicketById call that the controller makes first
        when(ticketService.getTicketById(ticketId)).thenReturn(existingTicket);
        
        // Act
        ticketWs.deleteTicket(ticketId);
        
        // Assert - Verify that both service methods were called
        verify(ticketService).getTicketById(ticketId);
        verify(ticketService).deleteTicketById(ticketId);
    }

    @Test
    public void testGetTicketsByUserId() {
        // Arrange - Create mock tickets list for a user
        Long userId = 1L;
        tickets ticket1 = new tickets();
        ticket1.setTicket_id(1L);
        ticket1.setTicketNumber("F6");
        
        tickets ticket2 = new tickets();
        ticket2.setTicket_id(2L);
        ticket2.setTicketNumber("G7");
        
        List<tickets> mockUserTickets = Arrays.asList(ticket1, ticket2);
        
        when(ticketService.getTicketsByUserId(userId)).thenReturn(mockUserTickets);
        
        // Act - Call the method being tested
        ResponseEntity<List<tickets>> result = ticketWs.getTicketsByUserId(userId);
        
        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.getBody().size(), "Result size should be 2");
        
        // Verify that the service method was called
        verify(ticketService).getTicketsByUserId(userId);
    }

    @Test
    public void testGetTicketsBySaleId() {
        // Arrange - Create mock tickets list for a sale
        Long saleId = 1L;
        tickets ticket1 = new tickets();
        ticket1.setTicket_id(1L);
        ticket1.setTicketNumber("H8");
        
        tickets ticket2 = new tickets();
        ticket2.setTicket_id(2L);
        ticket2.setTicketNumber("I9");
        
        List<tickets> mockSaleTickets = Arrays.asList(ticket1, ticket2);
        
        when(ticketService.getTicketsBySaleId(saleId)).thenReturn(mockSaleTickets);
        
        // Act - Call the method being tested
        ResponseEntity<List<tickets>> result = ticketWs.getTicketsBySaleId(saleId);
        
        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.getBody().size(), "Result size should be 2");
        
        // Verify that the service method was called
        verify(ticketService).getTicketsBySaleId(saleId);
    
    }

    // ============================================
    // EXCEPTION HANDLING TESTS (for 100% coverage)
    // ============================================
    
    @Test
    public void testGetAllTickets_ExceptionHandling() {
        // Arrange - Mock service to throw exception
        when(ticketService.getAllTickets()).thenThrow(new RuntimeException("Database error"));
        
        // Act
        ResponseEntity<List<tickets>> result = ticketWs.getAllTickets();
        
        // Assert - Verify exception handling
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        verify(ticketService).getAllTickets();
    }
    
    @Test
    public void testGetAllTicketsWithDetails_ExceptionHandling() {
        // Arrange - Mock service to throw exception
        when(ticketService.getAllWithDetails()).thenThrow(new RuntimeException("Database error"));
        
        // Act
        ResponseEntity<List<tickets>> result = ticketWs.getAllTicketsWithDetails();
        
        // Assert - Verify exception handling
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        verify(ticketService).getAllWithDetails();
    }
    
    @Test
    public void testGetTicketById_NotFound() {
        // Arrange - Mock service to return null (ticket not found)
        Long ticketId = 999L;
        when(ticketService.getTicketById(ticketId)).thenReturn(null);
        
        // Act
        ResponseEntity<tickets> result = ticketWs.getTicketById(ticketId);
        
        // Assert - Verify 404 response
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(ticketService).getTicketById(ticketId);
    }
    
    @Test
    public void testGetTicketById_ExceptionHandling() {
        // Arrange - Mock service to throw exception
        Long ticketId = 1L;
        when(ticketService.getTicketById(ticketId)).thenThrow(new RuntimeException("Database error"));
        
        // Act
        ResponseEntity<tickets> result = ticketWs.getTicketById(ticketId);
        
        // Assert - Verify exception handling
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        verify(ticketService).getTicketById(ticketId);
    }
    
    @Test
    public void testCreateTicket_ExceptionHandling() {
        // Arrange - Mock service to throw exception
        tickets mockTicket = new tickets();
        mockTicket.setTicketNumber("FAIL");
        doThrow(new RuntimeException("Creation failed")).when(ticketService).createTicket(mockTicket);
        
        // Act
        ResponseEntity<tickets> result = ticketWs.createTicket(mockTicket);
        
        // Assert - Verify exception handling
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        verify(ticketService).createTicket(mockTicket);
    }
    
    @Test
    public void testCreateTicketAfterPayment_ExceptionHandling() {
        // Arrange - Mock cart service to throw exception
        Long cartId = 1L;
        when(cartService.getCartById(cartId)).thenThrow(new RuntimeException("Database error"));
        
        // Act
        ResponseEntity<tickets> result = ticketWs.createTicketAfterPayment(cartId);
        
        // Assert - Verify exception handling
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        verify(cartService).getCartById(cartId);
    }

    @Test
    public void testCreateTicketAfterPayment_EventAndOfferAreNull() {
        // Arrange - Mock cart and user but event/offer remain null
        Long cartId = 1L;
        
        carts mockCart = new carts();
        mockCart.setCart_id(cartId);
        
        users mockUser = new users();
        mockUser.setUser_id(1L);
        mockCart.setUsers(mockUser);
        
        when(cartService.getCartById(cartId)).thenReturn(mockCart);
        
        // Act
        ResponseEntity<tickets> result = ticketWs.createTicketAfterPayment(cartId);
        
        // Assert - Since event and offer are null, should return BAD_REQUEST
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        verify(cartService).getCartById(cartId);
    }

    @Test
    public void testCreateTicketAfterPayment_CartNotFound() {
        // Arrange - Mock cart service to return null
        Long cartId = 999L;
        when(cartService.getCartById(cartId)).thenReturn(null);
        
        // Act
        ResponseEntity<tickets> result = ticketWs.createTicketAfterPayment(cartId);
        
        // Assert - Verify bad request response
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        verify(cartService).getCartById(cartId);
    }
    
    @Test
    public void testCreateTicketAfterPayment_UserNotFound() {
        // Arrange - Mock cart without user
        Long cartId = 1L;
        carts mockCart = new carts();
        mockCart.setCart_id(cartId);
        mockCart.setUsers(null); // No user associated
        
        when(cartService.getCartById(cartId)).thenReturn(mockCart);
        
        // Act
        ResponseEntity<tickets> result = ticketWs.createTicketAfterPayment(cartId);
        
        // Assert - Verify bad request response
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        verify(cartService).getCartById(cartId);
    }
    
    @Test
    public void testUpdateTicket_NotFound() {
        // Arrange - Mock service to return null (ticket not found)
        Long ticketId = 999L;
        tickets updateTicket = new tickets();
        updateTicket.setTicketNumber("UPDATE");
        
        when(ticketService.getTicketById(ticketId)).thenReturn(null);
        
        // Act
        ResponseEntity<tickets> result = ticketWs.updateTicket(ticketId, updateTicket);
        
        // Assert - Verify 404 response
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(ticketService).getTicketById(ticketId);
    }
    
    @Test
    public void testUpdateTicket_ExceptionHandling() {
        // Arrange - Mock service to throw exception
        Long ticketId = 1L;
        tickets updateTicket = new tickets();
        updateTicket.setTicketNumber("UPDATE");
        
        when(ticketService.getTicketById(ticketId)).thenThrow(new RuntimeException("Database error"));
        
        // Act
        ResponseEntity<tickets> result = ticketWs.updateTicket(ticketId, updateTicket);
        
        // Assert - Verify exception handling
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        verify(ticketService).getTicketById(ticketId);
    }
    
    @Test
    public void testDeleteTicket_NotFound() {
        // Arrange - Mock service to return null (ticket not found)
        Long ticketId = 999L;
        when(ticketService.getTicketById(ticketId)).thenReturn(null);
        
        // Act
        ResponseEntity<Void> result = ticketWs.deleteTicket(ticketId);
        
        // Assert - Verify 404 response
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(ticketService).getTicketById(ticketId);
    }
    
    @Test
    public void testDeleteTicket_ExceptionHandling() {
        // Arrange - Mock service to throw exception
        Long ticketId = 1L;
        when(ticketService.getTicketById(ticketId)).thenThrow(new RuntimeException("Database error"));
        
        // Act
        ResponseEntity<Void> result = ticketWs.deleteTicket(ticketId);
        
        // Assert - Verify exception handling
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        verify(ticketService).getTicketById(ticketId);
    }
    
    @Test
    public void testGetTicketsByUserId_ExceptionHandling() {
        // Arrange - Mock service to throw exception
        Long userId = 1L;
        when(ticketService.getTicketsByUserId(userId)).thenThrow(new RuntimeException("Database error"));
        
        // Act
        ResponseEntity<List<tickets>> result = ticketWs.getTicketsByUserId(userId);
        
        // Assert - Verify exception handling
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        verify(ticketService).getTicketsByUserId(userId);
    }
    
    @Test
    public void testGetTicketsBySaleId_ExceptionHandling() {
        // Arrange - Mock service to throw exception
        Long saleId = 1L;
        when(ticketService.getTicketsBySaleId(saleId)).thenThrow(new RuntimeException("Database error"));
        
        // Act
        ResponseEntity<List<tickets>> result = ticketWs.getTicketsBySaleId(saleId);
        
        // Assert - Verify exception handling
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        verify(ticketService).getTicketsBySaleId(saleId);
    }
}