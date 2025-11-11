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

import com.myriamfournier.olympics_ticket_office.pojo.sales;
import com.myriamfournier.olympics_ticket_office.service.SaleService;

@ExtendWith(MockitoExtension.class)
public class SaleWsUnitTest {


    @Mock
    private SaleService saleService;

    @InjectMocks
    private SaleWs saleWs;

    @Test
    public void testGetAllSalesDefault() {
        // Arrange - Create mock sales list
        sales sale1 = new sales();
        sale1.setSale_id(1L);
  
        
        sales sale2 = new sales();
        sale2.setSale_id(2L);

        
        List<sales> mockSales = Arrays.asList(sale1, sale2);
        
        when(saleService.getAllSales()).thenReturn(mockSales);
        
        // Act - Call the method being tested
        List<sales> result = saleWs.getAllSalesDefault();
        
        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result size should be 2");
        
        
        // Verify that the service method was called
        verify(saleService).getAllSales();

    }


    @Test
    public void testSaleWsCreation() {
        // Simple test to verify the SaleWs can be instantiated
        assertNotNull(saleWs, "SaleWs should not be null"); 
    }
    
    @Test
    public void testGetAllWithCarts() {
        // Arrange - Create mock sales list with carts
        sales sale1 = new sales();
        sale1.setSale_id(1L);
        
        sales sale2 = new sales();
        sale2.setSale_id(2L);

        
        List<sales> mockSales = Arrays.asList(sale1, sale2);
        
        when(saleService.getAllWithCarts()).thenReturn(mockSales);
        
        // Act - Call the method being tested
        List<sales> result = saleWs.getAllWithCarts();
        
        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result size should be 2");
        
        // Verify that the service method was called
        verify(saleService).getAllWithCarts();
    }   


    @Test
    public void testGetAllWithSaleskeys() {
        // Arrange - Create mock sales list with saleskeys
        sales sale1 = new sales();
        sale1.setSale_id(1L);
        
        sales sale2 = new sales();
        sale2.setSale_id(2L);

        
        List<sales> mockSales = Arrays.asList(sale1, sale2);
        
        when(saleService.getAllWithSaleskeys()).thenReturn(mockSales);
        
        // Act - Call the method being tested
        List<sales> result = saleWs.getAllWithSaleskeys();
        
        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result size should be 2");
        
        // Verify that the service method was called
        verify(saleService).getAllWithSaleskeys();
    }


    @Test
    public void testGetSaleById() {
        // Arrange - Create mock sale to be returned by service
        Long saleId = 1L;
        sales mockSale = new sales();
        mockSale.setSale_id(saleId);
        
        when(saleService.getSaleById(saleId)).thenReturn(mockSale);

        // Act - Call the method being tested
        sales result = saleWs.getSaleById(saleId);

        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(saleId, result.getSale_id(), "Sale ID should match the requested ID");

        // Verify that the service method was called
        verify(saleService).getSaleById(saleId);
    }   

    @Test
    public void testCreateSale() {
        // Arrange - Create a mock sale
        sales mockSale = new sales();
        mockSale.setSale_id(1L);
        
        // Act - Call the method being tested
        saleWs.createSale(mockSale);

        // Assert - Verify that the saleService's createSale method was called
        verify(saleService).createSale(mockSale);
    }



    @Test
    public void testUpdateSaleById() {
        // Arrange - Create mock sale to be updated
        Long saleId = 1L;
        sales updateSale = new sales();
        updateSale.setSale_id(saleId);

        // Act - Call the method being tested
        saleWs.updateSaleById(saleId, updateSale);

        // Assert - Verify that the service method was called
        verify(saleService).updateSaleById(updateSale, saleId);
    }

    @Test
    public void testDeleteSaleById() {  
        // Arrange - Create mock sale ID to be deleted
        Long saleId = 1L;

        // Act - Call the method being tested
        saleWs.deleteSaleById(saleId);

        // Assert - Verify that the service method was called
        verify(saleService).deleteSaleById(saleId);
    }

}
