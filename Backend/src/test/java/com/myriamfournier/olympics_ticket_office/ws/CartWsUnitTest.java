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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.myriamfournier.olympics_ticket_office.pojo.carts;
import com.myriamfournier.olympics_ticket_office.pojo.users;
import com.myriamfournier.olympics_ticket_office.pojo.MergeCartRequest;
import com.myriamfournier.olympics_ticket_office.pojo.offers;
import com.myriamfournier.olympics_ticket_office.service.CartService;
import com.myriamfournier.olympics_ticket_office.ws.CartWs;


/**
 * Unit tests for CartWs REST controller
 */
@ExtendWith(MockitoExtension.class)
public class CartWsUnitTest {

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartWs cartWs;

    @Test
    public void testCartWsCreation() {
        // Simple test to verify the CartWs can be instantiated
        assertNotNull(cartWs, "CartWs should not be null");
    }

    @Test
    public void testCreateCart() {
        // Arrange - Create a mock cart
        carts mockCart = new carts();
        mockCart.setCart_id(1L);
        mockCart.setDate(new Timestamp(System.currentTimeMillis()));
        mockCart.setTotalAmount(100.0f);
        
        // Act - Call the method being tested
        cartWs.createCart(mockCart);

        // Assert - Verify that the cartService's createCart method was called
        verify(cartService).createCart(mockCart);
    }

    @Test
    public void testGetAllCartsDefault() {
        // Arrange - Create mock carts list
        carts cart1 = new carts();
        cart1.setCart_id(1L);
        cart1.setTotalAmount(100.0f);
        
        carts cart2 = new carts();
        cart2.setCart_id(2L);
        cart2.setTotalAmount(50.0f);
        
        List<carts> mockCarts = Arrays.asList(cart1, cart2);
        when(cartService.getAllCarts()).thenReturn(mockCarts);

        // Act - Call the method being tested
        List<carts> result = cartWs.getAllCartsDefault();

        // Assert - Verify behavior and results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Should return 2 carts");
        assertEquals(1L, result.get(0).getCart_id(), "First cart ID should be 1");
        assertEquals(2L, result.get(1).getCart_id(), "Second cart ID should be 2");
        verify(cartService).getAllCarts();
    }

    @Test
    public void testGetCartById() {
        // Arrange - Create mock cart to be returned by service
        Long cartId = 1L;
        carts mockCart = new carts();
        mockCart.setCart_id(cartId);
        mockCart.setTotalAmount(100.0f);
        
        when(cartService.getCartById(cartId)).thenReturn(mockCart);

        // Act - Call the method being tested
        carts result = cartWs.getCartById(cartId);

        // Assert - Verify behavior and results
        assertNotNull(result, "Result should not be null");
        assertEquals(cartId, result.getCart_id(), "Cart ID should match the requested ID");
        assertEquals(100.0f, result.getTotalAmount(), "Total amount should match");
        verify(cartService).getCartById(cartId);
    }


    @Test
    public void testDeleteCartById() {
        // Arrange - Define the cart ID to delete
        Long cartId = 1L;

        // Act - Call the method being tested
        cartWs.deleteCartById(cartId);

        // Assert - Verify that the service method was called
        verify(cartService).deleteCartById(cartId);
    }

    @Test
    public void testUpdateCartById() {
        // Arrange - Create a mock cart to be updated
        Long cartId = 1L;
        carts mockCart = new carts();
        mockCart.setCart_id(cartId);
        mockCart.setTotalAmount(150.0f);
        
        // Act - Call the method being tested
        cartWs.updateCartById(cartId, mockCart);

        // Assert - Verify that the cartService's updateCartById method was called
        verify(cartService).updateCartById(mockCart, cartId);
    }


    @Test
    public void testGetAllWithUsers() {
        // Arrange - Create mock carts with users
        users user1 = new users();
        user1.setUser_id(1L);
        user1.setFirstname("John");
        
        carts cart1 = new carts();
        cart1.setCart_id(1L);
        cart1.setTotalAmount(100.0f);
        cart1.setUsers(user1);
        
        users user2 = new users();
        user2.setUser_id(2L);
        user2.setFirstname("Jane");
        
        carts cart2 = new carts();
        cart2.setCart_id(2L);
        cart2.setTotalAmount(50.0f);
        cart2.setUsers(user2);
        
        List<carts> mockCartsWithUsers = Arrays.asList(cart1, cart2);
        when(cartService.getAllWithUsers()).thenReturn(mockCartsWithUsers);
        
        // Act - Call the method being tested
        List<carts> result = cartWs.getAllWithUsers();
        
        // Assert - Verify behavior and results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Should return 2 carts with users");
        assertEquals(1L, result.get(0).getCart_id(), "First cart ID should be 1");
        assertEquals(2L, result.get(1).getCart_id(), "Second cart ID should be 2");
        assertEquals("John", result.get(0).getUsers().getFirstname(), "First user name should be John");
        assertEquals("Jane", result.get(1).getUsers().getFirstname(), "Second user name should be Jane");
        
        verify(cartService).getAllWithUsers();
    }


    @Test
    public void testMergeGuestCart() {
        // Arrange - Create mock merge cart request
        Long userId = 1L;
        offers offer1 = new offers();
        offer1.setOffer_id(1L);
        
        offers offer2 = new offers();
        offer2.setOffer_id(2L);
        
        List<offers> items = Arrays.asList(offer1, offer2);
        MergeCartRequest request = new MergeCartRequest(userId, items);

        // Act - Call the method being tested
        ResponseEntity<?> result = cartWs.mergeGuestCart(request);
        
        // Assert - Verify behavior and results
        assertNotNull(result, "Result should not be null");
        assertEquals(HttpStatus.OK, result.getStatusCode(), "Status should be OK");
        verify(cartService).mergeGuestCart(userId, items);
    }

    @Test
    public void testMergeGuestCartWithEmptyItems() {
        // Arrange - Create mock merge cart request with empty items
        Long userId = 2L;
        List<offers> emptyItems = Arrays.asList();
        MergeCartRequest request = new MergeCartRequest(userId, emptyItems);

        // Act - Call the method being tested
        ResponseEntity<?> result = cartWs.mergeGuestCart(request);
        
        // Assert - Verify behavior and results
        assertNotNull(result, "Result should not be null");
        assertEquals(HttpStatus.OK, result.getStatusCode(), "Status should be OK");
        verify(cartService).mergeGuestCart(userId, emptyItems);
    }
}