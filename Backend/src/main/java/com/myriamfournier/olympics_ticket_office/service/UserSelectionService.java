package com.myriamfournier.olympics_ticket_office.service;

import java.util.List;

import com.myriamfournier.olympics_ticket_office.pojo.userselections;

public interface UserSelectionService{

    List<userselections> getAllUserSelections();
    List<userselections> getAllWithOffers();
    List<userselections> getAllWithEvents();

    userselections getUserSelectionById(Long id);

    void createUserSelection(userselections userselections);

    void updateUserSelectionById(userselections userselections, Long id);


    void deleteUserSelectionById(Long id);


    


}
