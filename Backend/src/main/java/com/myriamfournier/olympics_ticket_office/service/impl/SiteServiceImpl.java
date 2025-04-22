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
            // (EN) A record is immutable,
            // (EN) impossible to modify.
            // (EN) Therefore, we must recover the element, modify it
            // (EN) put it back in base.
            // (FR) Un enregistement est immuable
            // (FR) impossible à modifier
            // (FR) De ce fait, on doit recuperer l'element, le modifier
            // (FR) le remettre en base.
            sites oldSite = getSiteById(id);

        if(oldSite != null){
            oldSite.setName(sites.getName());
            oldSite.setDescription(sites.getDescription());
            oldSite.setAddress(sites.getAddress());
            oldSite.setCity(sites.getCity());
            oldSite.setPostalCode(sites.getPostalCode());
            oldSite.setNbseatsc1(sites.getNbseatsc1());
            oldSite.setNbseatsc2(sites.getNbseatsc2());
            oldSite.setNbseatsc3(sites.getNbseatsc3());
            siteRepository.save(oldSite);
        }
    }




    @Override
    public void deleteSiteById(Long id) {
        siteRepository.deleteById(id);
    }

  
 

}
