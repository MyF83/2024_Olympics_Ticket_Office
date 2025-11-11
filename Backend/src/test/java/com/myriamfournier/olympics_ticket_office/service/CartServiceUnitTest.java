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

import com.myriamfournier.olympics_ticket_office.repository.CartRepository;
import com.myriamfournier.olympics_ticket_office.service.impl.CartServiceImpl;
import com.myriamfournier.olympics_ticket_office.pojo.carts;

/**
 * Unit tests for CartServiceImpl
 */
@ExtendWith(MockitoExtension.class)
public class CartServiceUnitTest {

    // Mock the repository dependencies that CartServiceImpl uses
    @Mock
    private CartRepository cartRepository;

    // Inject the mocks into the service implementation we're testing
    @InjectMocks
    private CartServiceImpl cartServiceImpl;

    @Test
    public void testGetAllCarts() {
        // Arrange
        List<carts> cartsList = Arrays.asList(new carts(), new carts());
        when(cartRepository.findAllWithDetails()).thenReturn(cartsList);
        
        // Act
        List<carts> result = cartServiceImpl.getAllCarts();
        
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(cartRepository, times(1)).findAllWithDetails();
    }

    @Test
    public void testGetAllWithUsers() {
        // Arrange
        List<carts> cartsList = Arrays.asList(new carts(), new carts());
        when(cartRepository.findAllWithUsers()).thenReturn(cartsList);
        
        // Act
        List<carts> result = cartServiceImpl.getAllWithUsers();
        
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(cartRepository, times(1)).findAllWithUsers();
    }

    @Test
    public void testGetCartById() {
        // Arrange
        carts cart = new carts();
        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));
        
        // Act
        carts result = cartServiceImpl.getCartById(1L);
        
        // Assert
        assertNotNull(result);
        verify(cartRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetCartByIdNotFound() {
        // Arrange
        when(cartRepository.findById(1L)).thenReturn(Optional.empty());
        
        // Act
        carts result = cartServiceImpl.getCartById(1L);
        
        // Assert
        assertEquals(null, result);
        verify(cartRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateCart() {
        // Arrange
        carts cart = new carts();
        
        // Act
        cartServiceImpl.createCart(cart);
        
        // Assert
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    public void testUpdateCartById() {
        // Arrange
        carts existingCart = new carts();
        existingCart.setTotalAmount(100.0f); // Set non-null value
        carts updatedCart = new carts();
        updatedCart.setTotalAmount(150.0f); // Set non-null value
        when(cartRepository.findById(1L)).thenReturn(Optional.of(existingCart));
        
        // Act
        cartServiceImpl.updateCartById(updatedCart, 1L);
        
        // Assert
        verify(cartRepository, times(1)).findById(1L);
        verify(cartRepository, times(1)).save(existingCart);
    }

    @Test
    public void testUpdateCartByIdNotFound() {
        // Arrange
        carts updatedCart = new carts();
        when(cartRepository.findById(1L)).thenReturn(Optional.empty());
        
        // Act
        cartServiceImpl.updateCartById(updatedCart, 1L);
        
        // Assert
        verify(cartRepository, times(1)).findById(1L);
        verify(cartRepository, times(0)).save(updatedCart);
    }

    @Test
    public void testDeleteCartById() {
        // Arrange
        Long cartId = 1L;
        
        // Act
        cartServiceImpl.deleteCartById(cartId);
        
        // Assert
        verify(cartRepository, times(1)).deleteById(cartId);
    }
}
