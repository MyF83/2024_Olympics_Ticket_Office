package com.myriamfournier.olympics_ticket_office.ws;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.myriamfournier.olympics_ticket_office.pojo.countries;
import com.myriamfournier.olympics_ticket_office.service.CountryService;

@ExtendWith(MockitoExtension.class)
public class CountryWsUnitTest {



     @Mock
    private CountryService countryService;

    @InjectMocks
    private CountryWs countryWs;


    @Test
    public void testGetAllCountries() { 
        // Arrange - Create mock countries list
        countries country1 = new countries();
        country1.setCountry_id(1L);

        countries country2 = new countries();
        country2.setCountry_id(2L);

        List<countries> mockCountries = Arrays.asList(country1, country2);

        when(countryService.getAllCountries()).thenReturn(mockCountries);

        // Act - Call the method being tested
        List<countries> result = countryWs.getAllCountries();

        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result size should be 2");

        // Verify that the service method was called
        verify(countryService).getAllCountries();
    }


    @Test
    public void testGetCountryById() {
        // Arrange - Create a mock country
        Long countryId = 1L;
        countries mockCountry = new countries();
        mockCountry.setCountry_id(countryId);

        when(countryService.getCountryById(countryId)).thenReturn(mockCountry);

        // Act - Call the method being tested
        countries result = countryWs.getCountryById(countryId);

        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(countryId, result.getCountry_id(), "Country ID should match");

        // Verify that the service method was called
        verify(countryService).getCountryById(countryId);
    }   

    @Test
    public void testCreateCountry() {
        // Arrange - Create a mock country
        countries newCountry = new countries();
        newCountry.setCountry_id(3L);

        // No need to mock return value for void method
        doNothing().when(countryService).createCountry(newCountry);

        // Act - Call the method being tested (void method)
        countryWs.createCountry(newCountry);

        // Assert - Verify that the service method was called
        verify(countryService).createCountry(newCountry);
    }

    @Test
    public void testUpdateCountryById() {
        // Arrange - Create a mock country
        Long countryId = 1L;
        countries updatedCountry = new countries();
        updatedCountry.setCountry_id(countryId);

        // No need to mock return value for void method
        doNothing().when(countryService).updateCountryById(countryId, updatedCountry);

        // Act - Call the method being tested (void method)
        countryWs.updateCountryById(countryId, updatedCountry);

        // Assert - Verify that the service method was called with correct parameters
        verify(countryService).updateCountryById(countryId, updatedCountry);
    }

    @Test
    public void testDeleteCountryById() {
        // Arrange - Create a mock country ID
        Long countryId = 1L;

        // Act - Call the method being tested
        countryWs.deleteCountryById(countryId);

        // Assert - Verify that the service method was called
        verify(countryService).deleteCountryById(countryId);
    }   
}
