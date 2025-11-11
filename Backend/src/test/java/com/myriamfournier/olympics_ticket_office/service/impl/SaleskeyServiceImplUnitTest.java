package com.myriamfournier.olympics_ticket_office.service.impl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

import java.sql.Date;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myriamfournier.olympics_ticket_office.repository.SaleskeyRepository;
import com.myriamfournier.olympics_ticket_office.repository.KeysgenerationRepository;
import com.myriamfournier.olympics_ticket_office.pojo.saleskeys;
import com.myriamfournier.olympics_ticket_office.pojo.keysgenerations;

@ExtendWith(MockitoExtension.class)
public class SaleskeyServiceImplUnitTest {

    // Mock the repository dependencies that SaleskeyServiceImpl uses
    @Mock
    private SaleskeyRepository saleskeyRepository;
    
    @Mock
    private KeysgenerationRepository keysgenerationRepository;

    // Inject the mocks into the service implementation we're testing
    @InjectMocks
    private SaleskeyServiceImpl saleskeyServiceImpl;

    @Test
    public void testGetAllSaleskeys() {
        // Arrange
        List<saleskeys> expectedSaleskeys = Arrays.asList(new saleskeys(), new saleskeys());
        when(saleskeyRepository.findAllWithDetails()).thenReturn(expectedSaleskeys);
        
        // Act
        List<saleskeys> actualSaleskeys = saleskeyServiceImpl.getAllSaleskeys();
        
        // Assert
        assertNotNull(actualSaleskeys);
        assertEquals(expectedSaleskeys.size(), actualSaleskeys.size());
        assertTrue(actualSaleskeys.containsAll(expectedSaleskeys));
        verify(saleskeyRepository).findAllWithDetails();
    }

    @Test
    public void testGetAllWithKeysgenerations() {
        // Arrange
        List<saleskeys> expectedSaleskeys = Arrays.asList(new saleskeys(), new saleskeys());
        when(saleskeyRepository.findAllWithKeysgenerations()).thenReturn(expectedSaleskeys);
        
        // Act
        List<saleskeys> actualSaleskeys = saleskeyServiceImpl.getAllWithKeysgenerations();
        
        // Assert
        assertNotNull(actualSaleskeys);
        assertEquals(expectedSaleskeys.size(), actualSaleskeys.size());
        assertTrue(actualSaleskeys.containsAll(expectedSaleskeys));
        verify(saleskeyRepository).findAllWithKeysgenerations();
    }

    @Test
    public void testGetSaleskeyById() {
        // Arrange
        saleskeys expectedSaleskey = new saleskeys();
        when(saleskeyRepository.findById(1L)).thenReturn(Optional.of(expectedSaleskey));
        
        // Act
        saleskeys actualSaleskey = saleskeyServiceImpl.getSaleskeyById(1L);
        
        // Assert
        assertNotNull(actualSaleskey);
        assertEquals(expectedSaleskey, actualSaleskey);
        verify(saleskeyRepository).findById(1L);
    }

    @Test
    public void testCreateSaleskey() {
        // Arrange
        saleskeys testSaleskey = new saleskeys();
        
        // Act
        saleskeyServiceImpl.createSaleskey(testSaleskey);
        
        // Assert
        verify(saleskeyRepository).save(any());
    }

    @Test
    public void testUpdateSaleskeyById() {
        // Arrange
        saleskeys existingSaleskey = new saleskeys();
        saleskeys updateSaleskey = new saleskeys();
        when(saleskeyRepository.findById(1L)).thenReturn(Optional.of(existingSaleskey));
        
        // Act
        saleskeyServiceImpl.updateSaleskeyById(updateSaleskey, 1L);
        
        // Assert
        verify(saleskeyRepository).findById(1L);
        verify(saleskeyRepository).save(any());
    }

    @Test
    public void testDeleteSaleskeyById() {
        // Arrange
        Long saleskeyId = 1L;
        
        // Act
        saleskeyServiceImpl.deleteSaleskeyById(saleskeyId);
        
        // Assert
        verify(saleskeyRepository).deleteById(saleskeyId);
    }

    // Additional tests to improve coverage

    @Test
    public void testGetSaleskeyById_NotFound() {
        // Arrange
        when(saleskeyRepository.findById(1L)).thenReturn(Optional.empty());
        
        // Act
        saleskeys actualSaleskey = saleskeyServiceImpl.getSaleskeyById(1L);
        
        // Assert
        assertNull(actualSaleskey);
        verify(saleskeyRepository).findById(1L);
    }

    @Test
    public void testUpdateSaleskeyById_WithNullExistingSaleskey() {
        // Arrange
        saleskeys updateSaleskey = new saleskeys();
        when(saleskeyRepository.findById(1L)).thenReturn(Optional.empty());
        
        // Act
        saleskeyServiceImpl.updateSaleskeyById(updateSaleskey, 1L);
        
        // Assert
        verify(saleskeyRepository).findById(1L);
        verify(saleskeyRepository, never()).save(any());
    }

    @Test
    public void testUpdateSaleskeyById_WithValidData() {
        // Arrange
        saleskeys existingSaleskey = new saleskeys();
        saleskeys updateSaleskey = new saleskeys();
        Date newDate = new Date(System.currentTimeMillis());
        updateSaleskey.setDate(newDate);
        
        when(saleskeyRepository.findById(1L)).thenReturn(Optional.of(existingSaleskey));
        
        // Act
        saleskeyServiceImpl.updateSaleskeyById(updateSaleskey, 1L);
        
        // Assert
        verify(saleskeyRepository).findById(1L);
        verify(saleskeyRepository).save(existingSaleskey);
        assertEquals(newDate, existingSaleskey.getDate());
    }

    @Test
    public void testCreateSalekey_Success() {
        // Arrange
        Long saleId = 123L;
        keysgenerations mockKeyGen = new keysgenerations();
        mockKeyGen.setKeyGenerated("mock-hash");
        
        when(keysgenerationRepository.save(any(keysgenerations.class))).thenReturn(mockKeyGen);
        when(saleskeyRepository.save(any(saleskeys.class))).thenAnswer(invocation -> invocation.getArgument(0));
        
        // Act
        saleskeys result = saleskeyServiceImpl.createSalekey(saleId);
        
        // Assert
        assertNotNull(result);
        assertNotNull(result.getDate());
        assertNotNull(result.getKeysgenerations());
        verify(keysgenerationRepository).save(any(keysgenerations.class));
        verify(saleskeyRepository).save(any(saleskeys.class));
    }

    @Test
    public void testCreateSalekey_WithKeyGenerationRepositoryError() {
        // Arrange
        Long saleId = 123L;
        when(keysgenerationRepository.save(any(keysgenerations.class)))
            .thenThrow(new RuntimeException("Database error"));
        
        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            saleskeyServiceImpl.createSalekey(saleId);
        });
        
        assertTrue(exception.getMessage().contains("Error creating sale key"));
        verify(keysgenerationRepository).save(any(keysgenerations.class));
        verify(saleskeyRepository, never()).save(any(saleskeys.class));
    }

    @Test
    public void testCreateSalekey_WithSaleskeyRepositoryError() {
        // Arrange
        Long saleId = 123L;
        keysgenerations mockKeyGen = new keysgenerations();
        mockKeyGen.setKeyGenerated("mock-hash");
        
        when(keysgenerationRepository.save(any(keysgenerations.class))).thenReturn(mockKeyGen);
        when(saleskeyRepository.save(any(saleskeys.class)))
            .thenThrow(new RuntimeException("Database error"));
        
        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            saleskeyServiceImpl.createSalekey(saleId);
        });
        
        assertTrue(exception.getMessage().contains("Error creating sale key"));
        verify(keysgenerationRepository).save(any(keysgenerations.class));
        verify(saleskeyRepository).save(any(saleskeys.class));
    }

    @Test
    public void testGenerateSaleKeyHash_Success() {
        // Arrange
        Long saleId = 456L;
        
        // Act
        String hash = saleskeyServiceImpl.generateSaleKeyHash(saleId);
        
        // Assert
        assertNotNull(hash);
        assertEquals(64, hash.length()); // SHA-256 produces 64 character hex string
        assertTrue(hash.matches("[a-f0-9]+"));
    }

    @Test
    public void testGenerateSaleKeyHash_UniqueHashes() {
        // Arrange
        Long saleId = 789L;
        
        // Act
        String hash1 = saleskeyServiceImpl.generateSaleKeyHash(saleId);
        String hash2 = saleskeyServiceImpl.generateSaleKeyHash(saleId);
        
        // Assert
        assertNotNull(hash1);
        assertNotNull(hash2);
        assertTrue(!hash1.equals(hash2)); // Should be different due to timestamp and random chars
    }

    @Test
    public void testGenerateSaleKeyHash_DifferentSaleIds() {
        // Arrange
        Long saleId1 = 100L;
        Long saleId2 = 200L;
        
        // Act
        String hash1 = saleskeyServiceImpl.generateSaleKeyHash(saleId1);
        String hash2 = saleskeyServiceImpl.generateSaleKeyHash(saleId2);
        
        // Assert
        assertNotNull(hash1);
        assertNotNull(hash2);
        assertTrue(!hash1.equals(hash2)); // Should be different for different sale IDs
    }

    @Test
    public void testGetAllSaleskeys_EmptyList() {
        // Arrange
        List<saleskeys> emptyList = new ArrayList<>();
        when(saleskeyRepository.findAllWithDetails()).thenReturn(emptyList);
        
        // Act
        List<saleskeys> actualSaleskeys = saleskeyServiceImpl.getAllSaleskeys();
        
        // Assert
        assertNotNull(actualSaleskeys);
        assertTrue(actualSaleskeys.isEmpty());
        verify(saleskeyRepository).findAllWithDetails();
    }

    @Test
    public void testGetAllWithKeysgenerations_EmptyList() {
        // Arrange
        List<saleskeys> emptyList = new ArrayList<>();
        when(saleskeyRepository.findAllWithKeysgenerations()).thenReturn(emptyList);
        
        // Act
        List<saleskeys> actualSaleskeys = saleskeyServiceImpl.getAllWithKeysgenerations();
        
        // Assert
        assertNotNull(actualSaleskeys);
        assertTrue(actualSaleskeys.isEmpty());
        verify(saleskeyRepository).findAllWithKeysgenerations();
    }
}
