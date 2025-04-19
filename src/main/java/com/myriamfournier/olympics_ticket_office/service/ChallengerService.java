package com.myriamfournier.olympics_ticket_office.service;

import java.util.List;

import com.myriamfournier.olympics_ticket_office.pojo.challengers;

public interface ChallengerService{

    List<challengers> getAllChallengers();

    challengers getChallengerById(Long id);

    void createChallenger(challengers challengers);

    void updateChallengerById(challengers challengers, Long id);


    void deleteChallengerById(Long id);


    


}
