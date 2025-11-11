package com.myriamfournier.olympics_ticket_office.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myriamfournier.olympics_ticket_office.pojo.sites;
import com.myriamfournier.olympics_ticket_office.repository.SiteRepository;


@ExtendWith(MockitoExtension.class)
public class SiteServiceImplUnitTest {

    // Mock the repository dependencies that CartServiceImpl uses
    @Mock
    private SiteRepository siteRepository;



    // Inject the mocks into the service implementation we're testing
    @InjectMocks
    private SiteServiceImpl siteServiceImpl;

    @Test
    public void testGetAllSites() {
        // Arrange
        List<sites> sitesList = Arrays.asList(new sites(), new sites());
        when(siteRepository.findAllWithDetails()).thenReturn(sitesList);
        
        // Act
        List<sites> result = siteServiceImpl.getAllSites();
        
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(siteRepository, times(1)).findAllWithDetails();
    }

    @Test
    public void testGetAllWithCountries() {
        // Arrange
        List<sites> sitesList = Arrays.asList(new sites(), new sites());
        when(siteRepository.findAllWithCountries()).thenReturn(sitesList);
        
        // Act
        List<sites> result = siteServiceImpl.getAllWithCountries();
        
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(siteRepository, times(1)).findAllWithCountries();
    }

    @Test
    public void testGetSiteById() {
        // Arrange
        sites site = new sites();
        when(siteRepository.findById(1L)).thenReturn(Optional.of(site));
        
        // Act
        sites result = siteServiceImpl.getSiteById(1L);
        
        // Assert
        assertNotNull(result);
        verify(siteRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateSite() {
        // Arrange
        sites site = new sites();
        when(siteRepository.save(any())).thenReturn(site);
        
        // Act
        siteServiceImpl.createSite(site);
        
        // Assert
        verify(siteRepository, times(1)).save(site);
    }

    @Test
    public void testUpdateSiteById() {
        // Arrange
        sites existingSite = new sites();
        sites updatedSite = new sites();
        updatedSite.setName("Updated Name");
        
        when(siteRepository.findById(1L)).thenReturn(Optional.of(existingSite));
        when(siteRepository.save(any())).thenReturn(existingSite);
        
        // Act
        siteServiceImpl.updateSiteById(updatedSite, 1L);
        
        // Assert
        verify(siteRepository, times(1)).findById(1L);
        verify(siteRepository, times(1)).save(existingSite);
    }

    @Test
    public void testDeleteSiteById() {
        // Act
        siteServiceImpl.deleteSiteById(1L);
        
        // Assert
        verify(siteRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetSiteByIdNotFound() {
        // Arrange
        when(siteRepository.findById(1L)).thenReturn(Optional.empty());
        
        // Act
        sites result = siteServiceImpl.getSiteById(1L);
        
        // Assert
        assertEquals(null, result);
        verify(siteRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateSiteByIdNotFound() {
        // Arrange
        sites updatedSite = new sites();
        when(siteRepository.findById(1L)).thenReturn(Optional.empty());
        
        // Act
        siteServiceImpl.updateSiteById(updatedSite, 1L);
        
        // Assert
        verify(siteRepository, times(1)).findById(1L);
        verify(siteRepository, times(0)).save(any());
    }

}
