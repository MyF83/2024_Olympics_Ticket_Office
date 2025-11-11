package com.myriamfournier.olympics_ticket_office.service.impl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import com.myriamfournier.olympics_ticket_office.repository.SaleRepository;
import com.myriamfournier.olympics_ticket_office.repository.KeysgenerationRepository;
import com.myriamfournier.olympics_ticket_office.repository.SaleskeyRepository;
import com.myriamfournier.olympics_ticket_office.pojo.sales;


@ExtendWith(MockitoExtension.class)
public class SaleServiceImplUnitTest {

    // Mock the repository dependencies that SaleServiceImpl uses
    @Mock
    private SaleRepository saleRepository;
    
    @Mock
    private KeysgenerationRepository keysgenerationRepository;
    
    @Mock
    private SaleskeyRepository saleskeyRepository;

    // Inject the mocks into the service implementation we're testing
    @InjectMocks
    private SaleServiceImpl saleServiceImpl;

    @Test
    public void testGetAllSales() {
       // Arrange
		List<sales> expectedSales = Arrays.asList(new sales(), new sales());
        when(saleRepository.findAllWithDetails()).thenReturn(expectedSales);
		// Act
		List<sales> actualSales = saleServiceImpl.getAllSales();
		//Assert
		assertNotNull(actualSales);
		assertEquals(expectedSales.size(), actualSales.size());
		assertTrue(actualSales.containsAll(expectedSales));
        verify(saleRepository).findAllWithDetails();
    }

    @Test
    public void testGetAllWithCarts() {
        // Arrange
        List<sales> expectedSales = Arrays.asList(new sales(), new sales());
        when(saleRepository.findAllWithCarts()).thenReturn(expectedSales);
		// Act
		List<sales> actualSales = saleServiceImpl.getAllWithCarts();
		//Assert
		assertNotNull(actualSales);
		assertEquals(expectedSales.size(), actualSales.size());
		assertTrue(actualSales.containsAll(expectedSales));
        verify(saleRepository).findAllWithCarts();
    }

    @Test
    public void testGetAllWithSaleskeys() {
        // Arrange
        List<sales> expectedSales = Arrays.asList(new sales(), new sales());
        when(saleRepository.findAllWithSaleskeys()).thenReturn(expectedSales);
		// Act
		List<sales> actualSales = saleServiceImpl.getAllWithSaleskeys();
		//Assert
		assertNotNull(actualSales);
		assertEquals(expectedSales.size(), actualSales.size());
		assertTrue(actualSales.containsAll(expectedSales));
        verify(saleRepository).findAllWithSaleskeys();
    }

    @Test
    public void testGetSaleById() {
         // Arrange
        sales expectedSale = new sales();
        when(saleRepository.findById(1L)).thenReturn(Optional.of(expectedSale));
		// Act
		sales actualSale = saleServiceImpl.getSaleById(1L);
		//Assert
		assertNotNull(actualSale);
		assertEquals(expectedSale, actualSale);
		verify(saleRepository).findById(1L);
    }

    @Test
    public void testSetSale() {
        // Arrange
        sales inputSale = new sales();
        
		// Act
		sales actualSale = saleServiceImpl.setSale(inputSale);
		
		//Assert
		assertNotNull(actualSale);
		assertEquals(inputSale, actualSale); // The method returns the same object
    }

    @Test
    public void testCreateSale() {
        // Arrange
        sales testSale = new sales();
        when(keysgenerationRepository.existsByKeyGenerated(any())).thenReturn(false);
        
        // Act
        saleServiceImpl.createSale(testSale);
        
        // Assert
        verify(saleRepository).save(any());
        verify(keysgenerationRepository).save(any());
        verify(saleskeyRepository).save(any());
    }

    @Test
    public void testUpdateSaleById() {
        // Arrange
        sales existingSale = new sales();
        sales updateSale = new sales();
        when(saleRepository.findById(1L)).thenReturn(Optional.of(existingSale));
        
        // Act
        saleServiceImpl.updateSaleById(updateSale, 1L);
        
        // Assert
        verify(saleRepository).findById(1L);
        verify(saleRepository).save(any());
    }

    @Test
    public void testDeleteSaleById() {
        // Arrange
        Long saleId = 1L;
		// Act
		saleServiceImpl.deleteSaleById(saleId);
		//Assert
		verify(saleRepository).deleteById(saleId);
    }

    @Test
    public void testUpdateSaleByIdWithNullSale() {
        // Test the branch where getSaleById returns null
        // Arrange
        sales updateSale = new sales();
        when(saleRepository.findById(1L)).thenReturn(Optional.empty());
        
        // Act
        saleServiceImpl.updateSaleById(updateSale, 1L);
        
        // Assert
        verify(saleRepository).findById(1L);
        verify(saleRepository, never()).save(any()); // Should not save anything
    }

    @Test
    public void testCreateSaleWithExistingKey() {
        // Test the branch where key already exists in generateUniqueKey
        // Arrange
        sales testSale = new sales();
        when(keysgenerationRepository.existsByKeyGenerated(any()))
            .thenReturn(true)   // First key exists
            .thenReturn(false); // Second key (with counter) doesn't exist
        
        // Act
        saleServiceImpl.createSale(testSale);
        
        // Assert
        verify(keysgenerationRepository, times(2)).existsByKeyGenerated(any());
        verify(saleRepository).save(any());
        verify(keysgenerationRepository).save(any());
        verify(saleskeyRepository).save(any());
    }

}
