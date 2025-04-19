package com.myriamfournier.olympics_ticket_office.service;

import java.util.List;

import com.myriamfournier.olympics_ticket_office.pojo.sports;

public interface SportService{

    List<sports> getAllSports();

    sports getSportById(Long id);

    void createSport(sports sports);

    void updateSportById(sports sports, Long id);


    void deleteSportById(Long id);


    


}
