package com.myriamfournier.olympics_ticket_office.service.impl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myriamfournier.olympics_ticket_office.repository.KeysgenerationRepository;


@ExtendWith(MockitoExtension.class)
public class KeysgenerationServiceImplUnitTest {


    // Mock the repository dependencies that CartServiceImpl uses
    @Mock
    private KeysgenerationRepository keysgenerationRepository;



    // Inject the mocks into the service implementation we're testing
    @InjectMocks
    private KeysgenerationServiceImpl keysgenerationServiceImpl;

    @Test
    public void testGetAllKeysgenerations() {
        // Arrange
        List<com.myriamfournier.olympics_ticket_office.pojo.keysgenerations> keysgenerationsList = Arrays.asList(new com.myriamfournier.olympics_ticket_office.pojo.keysgenerations(), new com.myriamfournier.olympics_ticket_office.pojo.keysgenerations());
        when(keysgenerationRepository.findAllKeysgenerations()).thenReturn(keysgenerationsList);
        // Act
        List<com.myriamfournier.olympics_ticket_office.pojo.keysgenerations> result = keysgenerationServiceImpl.getAllKeysgenerations();
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

        @Test
    public void testGetKeysgenerationById() {
        // Arrange
        com.myriamfournier.olympics_ticket_office.pojo.keysgenerations keysgeneration = new com.myriamfournier.olympics_ticket_office.pojo.keysgenerations();
        when(keysgenerationRepository.findById(any())).thenReturn(Optional.of(keysgeneration));
        // Act
        com.myriamfournier.olympics_ticket_office.pojo.keysgenerations result = keysgenerationServiceImpl.getKeysgenerationById(1L);
        // Assert
        assertNotNull(result);
        assertEquals(keysgeneration, result);
    }


        @Test
    public void testCreateKeysgeneration() {
        // Arrange
        com.myriamfournier.olympics_ticket_office.pojo.keysgenerations keysgeneration = new com.myriamfournier.olympics_ticket_office.pojo.keysgenerations();
        when(keysgenerationRepository.save(any())).thenReturn(keysgeneration);
        // Act
        keysgenerationServiceImpl.createKeysgeneration(keysgeneration);
        // Assert
        verify(keysgenerationRepository).save(keysgeneration);
    }

        @Test
    public void testUpdateKeysgenerationById() {
        // Arrange
        com.myriamfournier.olympics_ticket_office.pojo.keysgenerations keysgeneration = new com.myriamfournier.olympics_ticket_office.pojo.keysgenerations();
        when(keysgenerationRepository.findById(1L)).thenReturn(Optional.of(keysgeneration)); 
        when(keysgenerationRepository.save(any())).thenReturn(keysgeneration);
        // Act
        keysgenerationServiceImpl.updateKeysgenerationById(keysgeneration, 1L);
        // Assert
        verify(keysgenerationRepository).save(any());
    }

        @Test
    public void testDeleteKeysgenerationById() {
        // Arrange
        Long keysgenerationId = 1L;
        keysgenerationServiceImpl.deleteKeysgenerationById(keysgenerationId);
        // Act
        verify(keysgenerationRepository).deleteById(keysgenerationId);      
        // Assert
        assertTrue(true);
    }
}
