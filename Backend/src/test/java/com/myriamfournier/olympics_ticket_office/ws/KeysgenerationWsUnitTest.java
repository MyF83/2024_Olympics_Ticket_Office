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


import com.myriamfournier.olympics_ticket_office.pojo.keysgenerations;
import com.myriamfournier.olympics_ticket_office.service.KeysgenerationService;

@ExtendWith(MockitoExtension.class)
public class KeysgenerationWsUnitTest {


    @Mock
    private KeysgenerationService keysgenerationService;

    @InjectMocks
    private KeysgenerationsWs keysgenerationsWs;


    @Test
    public void testGetAllCarts() {  
        // Arrange - Create mock keysgenerations list
        keysgenerations keysgeneration1 = new keysgenerations();
        keysgeneration1.setKey_id(1L);

        keysgenerations keysgeneration2 = new keysgenerations();
        keysgeneration2.setKey_id(2L);

        List<keysgenerations> mockKeysgenerations = Arrays.asList(keysgeneration1, keysgeneration2);

        when(keysgenerationService.getAllKeysgenerations()).thenReturn(mockKeysgenerations);

        // Act - Call the method being tested
        List<keysgenerations> result = keysgenerationsWs.getAllCarts();

        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result size should be 2");

        // Verify that the service method was called
        verify(keysgenerationService).getAllKeysgenerations();
        }

         @Test
    public void testGetKeysgenerationById() {
        // Arrange - Create a mock keysgeneration
        Long keysgenerationId = 1L;
        keysgenerations mockKeysgeneration = new keysgenerations();
        mockKeysgeneration.setKey_id(keysgenerationId);

        when(keysgenerationService.getKeysgenerationById(keysgenerationId)).thenReturn(mockKeysgeneration);

        // Act - Call the method being tested
        keysgenerations result = keysgenerationsWs.getKeysgenerationById(keysgenerationId);

        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(keysgenerationId, result.getKey_id(), "Keysgeneration ID should match");

        // Verify that the service method was called
        verify(keysgenerationService).getKeysgenerationById(keysgenerationId);
    }


    @Test
    public void testCreateKeysgeneration() {
        // Arrange - Create a new keysgeneration
        keysgenerations newKeysgeneration = new keysgenerations();
        newKeysgeneration.setKey_id(1L);

        // Act - Call the method being tested
        keysgenerationsWs.createKeysgeneration(newKeysgeneration);

        // Assert - Verify that the service method was called
        verify(keysgenerationService).createKeysgeneration(newKeysgeneration);    
    }

    @Test
    public void testUpdateKeysgenerationById() {
        // Arrange - Create updated keysgeneration data
        Long keysgenerationId = 1L;
        keysgenerations updatedKeysgeneration = new keysgenerations();
        updatedKeysgeneration.setKey_id(keysgenerationId);

        // Act - Call the method being tested
        keysgenerationsWs.updateKeysgenerationById(keysgenerationId, updatedKeysgeneration);

        // Assert - Verify that the service method was called
        verify(keysgenerationService).updateKeysgenerationById(updatedKeysgeneration, keysgenerationId);
    }

    @Test
    public void testDeleteKeysgenerationById() {
        // Arrange - Define the keysgeneration ID to delete
        Long keysgenerationId = 1L;

        // Act - Call the method being tested
        keysgenerationsWs.deleteKeysgenerationById(keysgenerationId);

        // Assert - Verify that the service method was called
        verify(keysgenerationService).deleteKeysgenerationById(keysgenerationId);
    }
}
