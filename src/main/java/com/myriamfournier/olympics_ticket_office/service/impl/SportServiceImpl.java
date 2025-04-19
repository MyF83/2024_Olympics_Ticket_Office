package com.myriamfournier.olympics_ticket_office.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myriamfournier.olympics_ticket_office.pojo.sports;
import com.myriamfournier.olympics_ticket_office.repository.SportRepository;
import com.myriamfournier.olympics_ticket_office.service.SportService;

@Service
public class SportServiceImpl implements SportService{

@Autowired
    private SportRepository sportRepository; // Assuming you have a SportRepository interface


    // Implement the methods defined in SportService interface here
    
    @Override
    public List<sports> getAllSports() {
        return sportRepository.findAllSports();
    }


    @Override
    public sports getSportById(Long id) {
        return sportRepository.findById(id).orElse(null);
    }



    @Override
    public void createSport(sports sports) {
        sportRepository.save(sports);
    }


    @Override
    public void updateSportById(sports sports, Long id) {
            // un enregistement est immuable
            // impossible à modifier
            // de ce fait, on doit recuperer l'element, le modifier
            // le remettre
            sports oldSport = getSportById(id);

        if(oldSport != null){
            oldSport.setName(sports.getName());
            oldSport.setDescription(sports.getDescription());
            oldSport.setIsParalymp(sports.getIsParalymp());
            sportRepository.save(oldSport);
        }
    }




    @Override
    public void deleteSportById(Long id) {
        sportRepository.deleteById(id);
    }

  
 

}
