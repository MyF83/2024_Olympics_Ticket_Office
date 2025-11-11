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

import com.myriamfournier.olympics_ticket_office.pojo.sites;
import com.myriamfournier.olympics_ticket_office.pojo.countries;
import com.myriamfournier.olympics_ticket_office.service.SiteService;

@ExtendWith(MockitoExtension.class)
public class SiteWsUnitTest {


     @Mock
    private SiteService siteService;

    @InjectMocks
    private SiteWs siteWs;

    @Test
    public void testGetAllSitesDefault() {
        // Arrange - Create mock sites list
        sites site1 = new sites();
        site1.setSite_id(1L);
        site1.setName("Olympic Stadium");
        
        sites site2 = new sites();
        site2.setSite_id(2L);
        site2.setName("Aquatics Centre");
        
        List<sites> mockSites = Arrays.asList(site1, site2);
        
        when(siteService.getAllSites()).thenReturn(mockSites);
        
        // Act - Call the method being tested
        List<sites> result = siteWs.getAllSitesDefault();
        
        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result size should be 2");
        assertEquals("Olympic Stadium", result.get(0).getName(), "First site name should be 'Olympic Stadium'");
        assertEquals("Aquatics Centre", result.get(1).getName(), "Second site name should be 'Aquatics Centre'");
        
        // Verify that the service method was called
        verify(siteService).getAllSites();
    }

    @Test
    public void testGetAllWithCountries() {
        // Arrange - Create mock sites with countries
        sites site1 = new sites();
        site1.setSite_id(1L);
        site1.setName("Olympic Stadium");
        
        // Create a mock country for site1
        countries country1 = new countries();
        country1.setName("France");
        site1.setCountries(country1);
        
        sites site2 = new sites();
        site2.setSite_id(2L);
        site2.setName("Aquatics Centre");
        
        // Create a mock country for site2
        countries country2 = new countries();
        country2.setName("United Kingdom");
        site2.setCountries(country2);
        
        List<sites> mockSitesWithCountries = Arrays.asList(site1, site2);
        
        when(siteService.getAllWithCountries()).thenReturn(mockSitesWithCountries);
        
        // Act - Call the method being tested
        List<sites> result = siteWs.getAllWithCountries();
        
        // Assert - Verify the results
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result size should be 2");
        assertNotNull(result.get(0).getCountries(), "First site should have a country");
        assertNotNull(result.get(1).getCountries(), "Second site should have a country");
        assertEquals("France", result.get(0).getCountries().getName(), "First site country should be 'France'");
        assertEquals("United Kingdom", result.get(1).getCountries().getName(), "Second site country should be 'United Kingdom'");
        
        // Verify that the service method was called
        verify(siteService).getAllWithCountries();
    }

    @Test
    public void testGetSiteById() {
        // Arrange - Create mock site to be returned by service
        Long siteId = 1L;
        sites mockSite = new sites();
        mockSite.setSite_id(siteId);
        mockSite.setName("Olympic Stadium");
        
        // Create a mock country for the site
        countries country = new countries();
        country.setName("France");
        mockSite.setCountries(country);
        
        when(siteService.getSiteById(siteId)).thenReturn(mockSite);

        // Act - Call the method being tested
        sites result = siteWs.getSiteById(siteId);

        // Assert - Verify behavior and results
        assertNotNull(result, "Result should not be null");
        assertEquals(siteId, result.getSite_id(), "Site ID should match the requested ID");
        assertEquals("Olympic Stadium", result.getName(), "Site name should match");
        assertNotNull(result.getCountries(), "Site should have a country");
        assertEquals("France", result.getCountries().getName(), "Site country should match");
        verify(siteService).getSiteById(siteId);
    }

    @Test
    public void testCreateSite() {
        // Arrange - Create a mock site
        sites mockSite = new sites();
        mockSite.setSite_id(1L);
        mockSite.setName("Olympic Stadium");
        mockSite.setCity("Paris");

        // Act - Call the method being tested
        siteWs.createSite(mockSite);

        // Assert - Verify that the siteService's createSite method was called
        verify(siteService).createSite(mockSite);
    }
    
    @Test
    public void testUpdateSiteById() {
        // Arrange - Create a mock site
        Long siteId = 1L;
        sites updatedSite = new sites();
        updatedSite.setSite_id(siteId);
        updatedSite.setName("Updated Stadium");
        updatedSite.setCity("Updated City");

        // Act - Call the method being tested
        siteWs.updateSiteById(siteId, updatedSite);

        // Assert - Verify that the siteService's updateSiteById method was called
        verify(siteService).updateSiteById(updatedSite, siteId);
    }

    @Test
    public void testDeleteSiteById() {
        // Arrange - Create a mock site ID
        Long siteId = 1L;

        // Act - Call the method being tested
        siteWs.deleteSiteById(siteId);

        // Assert - Verify that the siteService's deleteSiteById method was called
        verify(siteService).deleteSiteById(siteId);
    }
    

}
