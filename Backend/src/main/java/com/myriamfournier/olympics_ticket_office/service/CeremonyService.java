package com.myriamfournier.olympics_ticket_office.service;

import java.util.List;

import com.myriamfournier.olympics_ticket_office.pojo.ceremonies;

public interface CeremonyService{

    List<ceremonies> getAllCeremonies();

    ceremonies getCeremonyById(Long id);

    void createCeremony(ceremonies ceremonies);

    void updateCeremonyById(ceremonies ceremonies, Long id);


    void deleteCeremonyById(Long id);


    


}
