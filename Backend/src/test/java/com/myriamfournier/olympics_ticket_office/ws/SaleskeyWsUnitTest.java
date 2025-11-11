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

import com.myriamfournier.olympics_ticket_office.pojo.saleskeys;
import com.myriamfournier.olympics_ticket_office.pojo.keysgenerations;
import com.myriamfournier.olympics_ticket_office.service.SaleskeyService;

@ExtendWith(MockitoExtension.class)
public class SaleskeyWsUnitTest {

    @Mock
    private SaleskeyService saleskeyService;

    @InjectMocks
    private SaleskeyWs saleskeyWs;

    @Test
    public void testGetAllSaleskeysDefault() {
        // Arrange - Create mock saleskeys list
        saleskeys saleskey1 = new saleskeys();
        saleskey1.setSalekey_id(1L);

        saleskeys saleskey2 = new saleskeys();
        saleskey2.setSalekey_id(2L);

        List<saleskeys> mockSaleskeys = Arrays.asList(saleskey1, saleskey2);

        when(saleskeyService.getAllSaleskeys()).thenReturn(mockSaleskeys);

        // Act - Call the method being tested
        List<saleskeys> result = saleskeyWs.getAllSaleskeysDefault();

        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result size should be 2");

        // Verify that the service method was called
        verify(saleskeyService).getAllSaleskeys();
    }

    @Test
    public void testGetAllWithKeysgenerations() {
        // Arrange - Create mock saleskeys list with keysgenerations    
        saleskeys saleskey1 = new saleskeys();
        saleskey1.setSalekey_id(1L);
        
        // Create a mock keysgenerations for saleskey1
        keysgenerations keygen1 = new keysgenerations();
        keygen1.setKeyGenerated("generated-key-1");
        saleskey1.setKeysgenerations(keygen1);

        saleskeys saleskey2 = new saleskeys();
        saleskey2.setSalekey_id(2L);
        
        // Create a mock keysgenerations for saleskey2
        keysgenerations keygen2 = new keysgenerations();
        keygen2.setKeyGenerated("generated-key-2");
        saleskey2.setKeysgenerations(keygen2);

        List<saleskeys> mockSaleskeys = Arrays.asList(saleskey1, saleskey2);

        when(saleskeyService.getAllWithKeysgenerations()).thenReturn(mockSaleskeys);

        // Act - Call the method being tested
        List<saleskeys> result = saleskeyWs.getAllWithKeysgenerations();

        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result size should be 2");
        assertNotNull(result.get(0).getKeysgenerations(), "First saleskey should have keysgenerations");
        assertNotNull(result.get(1).getKeysgenerations(), "Second saleskey should have keysgenerations");
        assertEquals("generated-key-1", result.get(0).getKeysgenerations().getKeyGenerated(), "First saleskey should have correct generated key");
        assertEquals("generated-key-2", result.get(1).getKeysgenerations().getKeyGenerated(), "Second saleskey should have correct generated key");

        // Verify that the service method was called
        verify(saleskeyService).getAllWithKeysgenerations();
    }

    @Test
    public void testGetSaleskeyById() {
        // Arrange - Create a mock saleskey
        Long saleskeyId = 1L;
        saleskeys mockSaleskey = new saleskeys();
        mockSaleskey.setSalekey_id(saleskeyId);

        when(saleskeyService.getSaleskeyById(saleskeyId)).thenReturn(mockSaleskey);

        // Act - Call the method being tested
        saleskeys result = saleskeyWs.getSaleskeyById(saleskeyId);

        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(saleskeyId, result.getSalekey_id(), "Saleskey ID should match the requested ID");

        // Verify that the service method was called
        verify(saleskeyService).getSaleskeyById(saleskeyId);
    }


    @Test
    public void testCreateSaleskey() {
        // Arrange - Create a mock saleskey to be created
        saleskeys mockSaleskey = new saleskeys();
        mockSaleskey.setSalekey_id(1L);

        // Act - Call the method being tested
        saleskeyWs.createSaleskey(mockSaleskey);

        // Assert - Verify that the saleskeyService's createSaleskey method was called
        verify(saleskeyService).createSaleskey(mockSaleskey);   

    }

    @Test
    public void testUpdateSaleskeyById() {
        // Arrange - Create mock saleskey to be updated
        Long saleskeyId = 1L;
        saleskeys updateSaleskey = new saleskeys();
        updateSaleskey.setSalekey_id(saleskeyId);

        // Act - Call the method being tested
        saleskeyWs.updateSaleskeyById(saleskeyId, updateSaleskey);

        // Assert - Verify that the saleskeyService's updateSaleskeyById method was called
        verify(saleskeyService).updateSaleskeyById(updateSaleskey, saleskeyId);
    }

    @Test
    public void testDeleteSaleskeyById() {
        // Arrange - Create a mock saleskey ID
        Long saleskeyId = 1L;

        // Act - Call the method being tested
        saleskeyWs.deleteSaleskeyById(saleskeyId);

        // Assert - Verify that the saleskeyService's deleteSaleskeyById method was called
        verify(saleskeyService).deleteSaleskeyById(saleskeyId);
    }

}