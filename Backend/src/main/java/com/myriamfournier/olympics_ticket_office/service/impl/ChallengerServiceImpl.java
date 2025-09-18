package com.myriamfournier.olympics_ticket_office.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myriamfournier.olympics_ticket_office.pojo.challengers;
import com.myriamfournier.olympics_ticket_office.repository.ChallengerRepository;
import com.myriamfournier.olympics_ticket_office.service.ChallengerService;

@Service
public class ChallengerServiceImpl implements ChallengerService{

@Autowired
    private ChallengerRepository challengerRepository; // Assuming you have a ChallengerRepository interface


    // Implement the methods defined in ChallengerService interface here
    
    @Override
    public List<challengers> getAllChallengers() {
        return challengerRepository.findAllWithDetails();
    }

    @Override
    public List<challengers> getAllWithCountries() {
        return challengerRepository.findAllWithCountries();
    }


    @Override
    public challengers getChallengerById(Long id) {
        return challengerRepository.findById(id).orElse(null);
    }



    @Override
    public void createChallenger(challengers challengers) {
        challengerRepository.save(challengers);
    }


    @Override
    public void updateChallengerById(challengers challengers, Long id) {
            // un enregistement est immuable
            // impossible à modifier
            // de ce fait, on doit recuperer l'element, le modifier
            // le remettre
            challengers oldChallenger = getChallengerById(id);

        if(oldChallenger != null){
            oldChallenger.setName(challengers.getName());
            challengerRepository.save(oldChallenger);
        }
    }




    @Override
    public void deleteChallengerById(Long id) {
        challengerRepository.deleteById(id);
    }

  
 

}
