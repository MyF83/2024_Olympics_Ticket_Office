package com.myriamfournier.olympics_ticket_office.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myriamfournier.olympics_ticket_office.pojo.carts;
import com.myriamfournier.olympics_ticket_office.pojo.users;
import com.myriamfournier.olympics_ticket_office.pojo.offers;
import com.myriamfournier.olympics_ticket_office.repository.CartRepository;
import com.myriamfournier.olympics_ticket_office.repository.UserRepository;

/**
 * Unit tests for CartServiceImpl
 */
@ExtendWith(MockitoExtension.class)
@SuppressWarnings("null")
public class CartServiceImplUnitTest {

    // Mock the repository dependencies that CartServiceImpl uses
    @Mock
    private CartRepository cartRepository;
    
    @Mock
    private UserRepository userRepository;

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
        verify(cartRepository).findAllWithDetails();
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
        verify(cartRepository).findAllWithUsers();
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
        verify(cartRepository).findById(1L);
    }
      @Test
    public void testCreateCart() {
        // Arrange
        carts newCart = new carts();
        when(cartRepository.save(any(carts.class))).thenReturn(newCart);
        
        // Act
        cartServiceImpl.createCart(newCart);
        
        // Assert
        verify(cartRepository).save(newCart);
    }
      @Test
    public void testUpdateCartById() {
        // Arrange
        carts existingCart = new carts();
        existingCart.setDate(new java.sql.Timestamp(System.currentTimeMillis()));
        existingCart.setTotalAmount(100.0);
        
        carts updatedCart = new carts();
        updatedCart.setDate(new java.sql.Timestamp(System.currentTimeMillis()));
        updatedCart.setTotalAmount(150.0);
        
        when(cartRepository.findById(1L)).thenReturn(Optional.of(existingCart));
        when(cartRepository.save(any(carts.class))).thenReturn(existingCart);
        
        // Act
        cartServiceImpl.updateCartById(updatedCart, 1L);
        
        // Assert
        verify(cartRepository).findById(1L);
        verify(cartRepository).save(any(carts.class));
    }
      @Test
    public void testDeleteCartById() {
        // Arrange
        Long cartId = 1L;
        // Act
        cartServiceImpl.deleteCartById(cartId);     
        // Assert
        verify(cartRepository).deleteById(cartId);
    }
      @Test
    public void testMergeGuestCart() {
        // Arrange
        Long userId = 1L;
        List<com.myriamfournier.olympics_ticket_office.pojo.offers> items = Arrays.asList(
            new com.myriamfournier.olympics_ticket_office.pojo.offers()
        );
        List<carts> allCarts = Arrays.asList(new carts());
        when(cartRepository.findAllWithDetails()).thenReturn(allCarts);
        when(userRepository.findById(userId)).thenReturn(Optional.of(new com.myriamfournier.olympics_ticket_office.pojo.users()));
        
        // Act
        cartServiceImpl.mergeGuestCart(userId, items);
        
        // Assert
        verify(cartRepository).findAllWithDetails();
    }
      @Test
    public void testFindByUser() {
        // Arrange
        com.myriamfournier.olympics_ticket_office.pojo.users user = new com.myriamfournier.olympics_ticket_office.pojo.users();
        List<carts> userCarts = Arrays.asList(new carts());
        when(cartRepository.findByUsers(user)).thenReturn(userCarts);
        
        // Act
        List<carts> result = cartServiceImpl.findByUser(user);
        
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(cartRepository).findByUsers(user);
    }

    @Test
    public void testUpdateCartByIdWithNullCart() {
        // Arrange
        carts updatedCart = new carts();
        updatedCart.setDate(new java.sql.Timestamp(System.currentTimeMillis()));
        updatedCart.setTotalAmount(150.0);
        
        when(cartRepository.findById(1L)).thenReturn(Optional.empty());
        
        // Act
        cartServiceImpl.updateCartById(updatedCart, 1L);
        
        // Assert
        verify(cartRepository).findById(1L);
        verify(cartRepository, never()).save(any(carts.class));
    }

    @Test
    public void testGetCartByIdNotFound() {
        // Arrange
        when(cartRepository.findById(1L)).thenReturn(Optional.empty());
        
        // Act
        carts result = cartServiceImpl.getCartById(1L);
        
        // Assert
        assertNull(result);
        verify(cartRepository).findById(1L);
    }

    @Test
    public void testMergeGuestCartWithExistingCart() {
        // Arrange
        Long userId = 1L;
        users user = new users();
        user.setUser_id(userId);
        
        carts existingCart = new carts();
        existingCart.setUser(user);
        existingCart.setTotalAmount(100.0);
        
        List<carts> allCarts = Arrays.asList(existingCart);
        List<offers> items = Arrays.asList(new offers());
        
        when(cartRepository.findAllWithDetails()).thenReturn(allCarts);
        when(cartRepository.save(any(carts.class))).thenReturn(existingCart);
        
        // Act
        cartServiceImpl.mergeGuestCart(userId, items);
        
        // Assert
        verify(cartRepository).findAllWithDetails();
        verify(cartRepository).save(any(carts.class));
        verify(userRepository, never()).findById(userId);
    }

    @Test
    public void testMergeGuestCartWithNewUser() {
        // Arrange
        Long userId = 1L;
        users user = new users();
        user.setUser_id(userId);
        
        List<carts> allCarts = Arrays.asList(); // Empty list - no existing cart
        List<offers> items = Arrays.asList(new offers());
        
        when(cartRepository.findAllWithDetails()).thenReturn(allCarts);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(cartRepository.save(any(carts.class))).thenReturn(new carts());
        
        // Act
        cartServiceImpl.mergeGuestCart(userId, items);
        
        // Assert
        verify(cartRepository).findAllWithDetails();
        verify(userRepository).findById(userId);
        verify(cartRepository, times(2)).save(any(carts.class));
    }

    @Test
    public void testMergeGuestCartWithNullItems() {
        // Arrange
        Long userId = 1L;
        users user = new users();
        user.setUser_id(userId);
        
        List<carts> allCarts = Arrays.asList(); // Empty list - no existing cart
        
        when(cartRepository.findAllWithDetails()).thenReturn(allCarts);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(cartRepository.save(any(carts.class))).thenReturn(new carts());
        
        // Act
        cartServiceImpl.mergeGuestCart(userId, null);
        
        // Assert
        verify(cartRepository).findAllWithDetails();
        verify(userRepository).findById(userId);
        verify(cartRepository, times(1)).save(any(carts.class)); // Only once for cart creation
    }

    @Test
    public void testMergeGuestCartWithEmptyItems() {
        // Arrange
        Long userId = 1L;
        users user = new users();
        user.setUser_id(userId);
        
        List<carts> allCarts = Arrays.asList(); // Empty list - no existing cart
        List<offers> items = Arrays.asList(); // Empty items list
        
        when(cartRepository.findAllWithDetails()).thenReturn(allCarts);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(cartRepository.save(any(carts.class))).thenReturn(new carts());
        
        // Act
        cartServiceImpl.mergeGuestCart(userId, items);
        
        // Assert
        verify(cartRepository).findAllWithDetails();
        verify(userRepository).findById(userId);
        verify(cartRepository, times(1)).save(any(carts.class)); // Only once for cart creation
    }

    @Test
    public void testMergeGuestCartWithCartButNullUser() {
        // Arrange
        Long userId = 1L;
        
        carts cartWithoutUser = new carts();
        cartWithoutUser.setUser(null); // Cart without user
        
        List<carts> allCarts = Arrays.asList(cartWithoutUser);
        List<offers> items = Arrays.asList(new offers());
        
        users user = new users();
        user.setUser_id(userId);
        
        when(cartRepository.findAllWithDetails()).thenReturn(allCarts);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(cartRepository.save(any(carts.class))).thenReturn(new carts());
        
        // Act
        cartServiceImpl.mergeGuestCart(userId, items);
        
        // Assert
        verify(cartRepository).findAllWithDetails();
        verify(userRepository).findById(userId);
        verify(cartRepository, times(2)).save(any(carts.class)); // Create new cart + update with items
    }
}







