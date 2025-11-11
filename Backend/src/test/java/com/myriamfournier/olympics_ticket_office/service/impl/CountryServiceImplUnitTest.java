package com.myriamfournier.olympics_ticket_office.service.impl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import com.myriamfournier.olympics_ticket_office.repository.CountryRepository;


@ExtendWith(MockitoExtension.class)
public class CountryServiceImplUnitTest {


    // Mock the repository dependencies that CartServiceImpl uses
    @Mock
    private CountryRepository countryRepository;



    // Inject the mocks into the service implementation we're testing
    @InjectMocks
    private CountryServiceImpl countryServiceImpl;

    @Test
    public void testGetAllCountries() {
        // Arrange
        List<com.myriamfournier.olympics_ticket_office.pojo.countries> countriesList = Arrays.asList(new com.myriamfournier.olympics_ticket_office.pojo.countries(), new com.myriamfournier.olympics_ticket_office.pojo.countries());
        when(countryRepository.findAllCountries()).thenReturn(countriesList);
        // Act
        List<com.myriamfournier.olympics_ticket_office.pojo.countries> result = countryServiceImpl.getAllCountries();
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }


       @Test
    public void testGetCountryById(){
		// Arrange
		com.myriamfournier.olympics_ticket_office.pojo.countries country = new com.myriamfournier.olympics_ticket_office.pojo.countries();
		when(countryRepository.findById(any())).thenReturn(Optional.of(country));    
		// Act
		com.myriamfournier.olympics_ticket_office.pojo.countries result = countryServiceImpl.getCountryById(1L);
		// Assert
		assertNotNull(result);
		assertEquals(country, result);
    }


       @Test
    public void testCreateCountry(){
		// Arrange
        com.myriamfournier.olympics_ticket_office.pojo.countries country = new com.myriamfournier.olympics_ticket_office.pojo.countries();
        when(countryRepository.save(any())).thenReturn(country);        
		// Act
		countryServiceImpl.createCountry(country);
		// Assert
		verify(countryRepository).save(country);
    }


       @Test
    public void testUpdateCountryById(){
		// Arrange
		com.myriamfournier.olympics_ticket_office.pojo.countries country = new com.myriamfournier.olympics_ticket_office.pojo.countries();
        when(countryRepository.findById(1L)).thenReturn(Optional.of(country)); 
        when(countryRepository.save(any())).thenReturn(country);
		// Act
		countryServiceImpl.updateCountryById(1L, country);
		// Assert
		verify(countryRepository).save(any());
    }


       @Test
    public void testDeleteCountryById(){
		// Arrange
        Long countryId = 1L;    
		// Act
        countryServiceImpl.deleteCountryById(countryId);
		// Assert
		verify(countryRepository).deleteById(countryId);
    }
}
