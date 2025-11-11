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

import com.myriamfournier.olympics_ticket_office.pojo.offers;
import com.myriamfournier.olympics_ticket_office.service.OfferService;
import com.myriamfournier.olympics_ticket_office.service.PolicyService;

@ExtendWith(MockitoExtension.class)
public class OfferWsUnitTest {



    @Mock
    private OfferService offerService;

    @InjectMocks
    private OfferWs offerWs;


    @Test
    public void testGetAllOffers() {  
        // Arrange - Create mock offers list
        offers offer1 = new offers();
        offer1.setOffer_id(1L);

        offers offer2 = new offers();
        offer2.setOffer_id(2L);

        List<offers> mockOffers = Arrays.asList(offer1, offer2);

        when(offerService.getAllOffers()).thenReturn(mockOffers);

        // Act - Call the method being tested
        List<offers> result = offerWs.getAllOffers();

        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result size should be 2");

        // Verify that the service method was called
        verify(offerService).getAllOffers();
    }

    @Test
    public void testGetOfferById() {
        // Arrange - Create a mock offer
        Long offerId = 1L;
        offers mockOffer = new offers();
        mockOffer.setOffer_id(offerId);

        when(offerService.getOfferById(offerId)).thenReturn(mockOffer);

        // Act - Call the method being tested
        offers result = offerWs.getOfferById(offerId);

        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(offerId, result.getOffer_id(), "Offer ID should match");

        // Verify that the service method was called
        verify(offerService).getOfferById(offerId);
    }


    @Test
    public void testCreateOffer() {
        // Arrange - Create a new offer
        offers newOffer = new offers();
        newOffer.setOffer_id(1L);

        // Act - Call the method being tested
        offerWs.createOffer(newOffer);

        // Assert - Verify that the service method was called
        verify(offerService).createOffer(newOffer);
    }   


    @Test
    public void testUpdateOfferById() {
        // Arrange - Create an updated offer
        Long offerId = 1L;
        offers updatedOffer = new offers();
        updatedOffer.setOffer_id(offerId);

        // Act - Call the method being tested
        offerWs.updateOfferById(offerId, updatedOffer);

        // Assert - Verify that the service method was called
        verify(offerService).updateOfferById(updatedOffer, offerId);
    }   

    @Test
    public void testDeleteOfferById() { 
        // Arrange - Define the offer ID to delete
        Long offerId = 1L;

        // Act - Call the method being tested
        offerWs.deleteOfferById(offerId);

        // Assert - Verify that the service method was called
        verify(offerService).deleteOfferById(offerId);

    }
}
