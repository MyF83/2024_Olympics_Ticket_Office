package com.myriamfournier.olympics_ticket_office.service;

import java.util.List;

import com.myriamfournier.olympics_ticket_office.pojo.sites;

public interface SiteService{

    List<sites> getAllSites();

    sites getSiteById(Long id);

    void createSite(sites sites);

    void updateSiteById(sites sites, Long id);


    void deleteSiteById(Long id);


    


}
