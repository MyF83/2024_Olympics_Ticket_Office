package com.myriamfournier.olympics_ticket_office.service;

import java.util.List;

import com.myriamfournier.olympics_ticket_office.pojo.userskeys;

public interface UserskeyService{

    List<userskeys> getAllUserskeys();
    List<userskeys> getAllWithUsers();
    List<userskeys> getAllWithKeysgenerations();

    userskeys getUserskeyById(Long id);

    void createUserskey(userskeys userskeys);

    void updateUserskeyById(userskeys userskeys, Long id);


    void deleteUserskeyById(Long id);


    


}
