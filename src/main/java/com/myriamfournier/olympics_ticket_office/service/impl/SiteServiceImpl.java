package com.myriamfournier.olympics_ticket_office.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myriamfournier.olympics_ticket_office.pojo.sites;
import com.myriamfournier.olympics_ticket_office.repository.SiteRepository;
import com.myriamfournier.olympics_ticket_office.service.SiteService;

@Service
public class SiteServiceImpl implements SiteService{

@Autowired
    private SiteRepository siteRepository; // Assuming you have a SiteRepository interface


    // Implement the methods defined in ServiceService interface here
    
    @Override
    public List<sites> getAllSites() {
        return siteRepository.findAllSites();
    }


    @Override
    public sites getSiteById(Long id) {
        return siteRepository.findById(id).orElse(null);
    }



    @Override
    public void createSite(sites sites) {
        siteRepository.save(sites);
    }


    @Override
    public void updateSiteById(sites sites, Long id) {
            // un enregistement est immuable
            // impossible à modifier
            // de ce fait, on doit recuperer l'element, le modifier
            // le remettre
            sites oldSite = getSiteById(id);

        if(oldSite != null){
            oldSite.setName(sites.getName());
            oldSite.setDescription(sites.getDescription());
            oldSite.setAddress(sites.getAddress());
            oldSite.setCity(sites.getCity());
            oldSite.setPostalCode(sites.getPostalCode());
            oldSite.setNbSeatsC1(sites.getNbSeatsC1());
            oldSite.setNbSeatsC2(sites.getNbSeatsC2());
            oldSite.setNbSeatsC3(sites.getNbSeatsC3());
            siteRepository.save(oldSite);
        }
    }




    @Override
    public void deleteSiteById(Long id) {
        siteRepository.deleteById(id);
    }

  
 

}
