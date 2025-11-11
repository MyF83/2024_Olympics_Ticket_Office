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

import com.myriamfournier.olympics_ticket_office.repository.OfferRepository;


@ExtendWith(MockitoExtension.class)
public class OfferServiceImplUnitTest {

    // Mock the repository dependencies that CartServiceImpl uses
    @Mock
    private OfferRepository offerRepository;



    // Inject the mocks into the service implementation we're testing
    @InjectMocks
    private OfferServiceImpl offerServiceImpl;

    @Test
    public void testGetAllOffers() {
        // Arrange
        List<com.myriamfournier.olympics_ticket_office.pojo.offers> offersList = Arrays.asList(new com.myriamfournier.olympics_ticket_office.pojo.offers(), new com.myriamfournier.olympics_ticket_office.pojo.offers());
        when(offerRepository.findAllOffers()).thenReturn(offersList);
        // Act
        List<com.myriamfournier.olympics_ticket_office.pojo.offers> result = offerServiceImpl.getAllOffers();
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

       @Test
    public void testGetOfferById() {
        // Arrange
        com.myriamfournier.olympics_ticket_office.pojo.offers offer = new com.myriamfournier.olympics_ticket_office.pojo.offers();
        when(offerRepository.findById(any())).thenReturn(Optional.of(offer));
        // Act
        com.myriamfournier.olympics_ticket_office.pojo.offers result = offerServiceImpl.getOfferById(1L);
        // Assert
        assertNotNull(result);
        assertEquals(offer, result);
    }

       @Test
    public void testCreateOffer() {
        // Arrange
        com.myriamfournier.olympics_ticket_office.pojo.offers offer = new com.myriamfournier.olympics_ticket_office.pojo.offers();
        when(offerRepository.save(any())).thenReturn(offer);
        // Act
        offerServiceImpl.createOffer(offer);
        // Assert
        verify(offerRepository).save(offer);
    }

       @Test
    public void testUpdateOfferById() {
        // Arrange
        com.myriamfournier.olympics_ticket_office.pojo.offers existingOffer = new com.myriamfournier.olympics_ticket_office.pojo.offers();
        when(offerRepository.findById(1L)).thenReturn(Optional.of(existingOffer)); 
        when(offerRepository.save(any())).thenReturn(existingOffer);
        // Act
        offerServiceImpl.updateOfferById(existingOffer, 1L);
        // Assert
        verify(offerRepository).save(any());
    }

       @Test
    public void testDeleteOfferById() {
        // Arrange
        Long offerId = 1L;
        offerServiceImpl.deleteOfferById(offerId);  
        // Act
        verify(offerRepository).deleteById(offerId);
        // Assert
        assertTrue(true);
    }

}
