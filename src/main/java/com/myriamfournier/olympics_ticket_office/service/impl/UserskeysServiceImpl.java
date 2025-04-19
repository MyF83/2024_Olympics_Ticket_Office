package com.myriamfournier.olympics_ticket_office.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myriamfournier.olympics_ticket_office.pojo.userskeys;
import com.myriamfournier.olympics_ticket_office.repository.UserskeyRepository;
import com.myriamfournier.olympics_ticket_office.service.UserskeyService;

@Service
public class UserskeysServiceImpl implements UserskeyService{

@Autowired
    private UserskeyRepository userskeysRepository; // Assuming you have a CartRepository interface


    // Implement the methods defined in CartService interface here
    
    @Override
    public List<userskeys> getAllUserskeys() {
        return userskeysRepository.findAllUserskeys();
    }


    @Override
    public userskeys getUserskeyById(Long id) {
        return userskeysRepository.findById(id).orElse(null);
    }



    @Override
    public void createUserskey(userskeys userskeys) {
        userskeysRepository.save(userskeys);
    }


    @Override
    public void updateUserskeyById(userskeys userskeys, Long id) {
            // un enregistement est immuable
            // impossible à modifier
            // de ce fait, on doit recuperer l'element, le modifier
            // le remettre
            userskeys oldUserskey = getUserskeyById(id);

        if(oldUserskey != null){
            oldUserskey.setDate(userskeys.getDate());
            userskeysRepository.save(oldUserskey);
        }
    }




    @Override
    public void deleteUserskeyById(Long id) {
        userskeysRepository.deleteById(id);
    }



  
 

}
