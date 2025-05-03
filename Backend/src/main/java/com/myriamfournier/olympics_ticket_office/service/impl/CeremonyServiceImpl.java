package com.myriamfournier.olympics_ticket_office.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myriamfournier.olympics_ticket_office.pojo.ceremonies;
import com.myriamfournier.olympics_ticket_office.repository.CeremonyRepository;
import com.myriamfournier.olympics_ticket_office.service.CeremonyService;

@Service
public class CeremonyServiceImpl implements CeremonyService{

@Autowired
    private CeremonyRepository ceremonyRepository; // Assuming you have a CeremonyRepository interface


    // Implement the methods defined in CeremonyService interface here
    
    @Override
    public List<ceremonies> getAllCeremonies() {
        return ceremonyRepository.findAllWithDetails();
    }

    @Override
    public List<ceremonies> getAllWithSites() {
        return ceremonyRepository.findAllWithSites();
    }


    @Override
    public ceremonies getCeremonyById(Long id) {
        return ceremonyRepository.findById(id).orElse(null);
    }



    @Override
    public void createCeremony(ceremonies ceremonies) {
        ceremonyRepository.save(ceremonies);
    }


    @Override
    public void updateCeremonyById(ceremonies ceremonies, Long id) {
            // un enregistement est immuable
            // impossible à modifier
            // de ce fait, on doit recuperer l'element, le modifier
            // le remettre
            ceremonies oldCeremony = getCeremonyById(id);

        if(oldCeremony != null){
            oldCeremony.setName(ceremonies.getName());
            oldCeremony.setDescription(ceremonies.getDescription());
            ceremonyRepository.save(oldCeremony);
        }
    }




    @Override
    public void deleteCeremonyById(Long id) {
        ceremonyRepository.deleteById(id);
    }

  
 

}
