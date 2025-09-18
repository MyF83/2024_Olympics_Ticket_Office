package com.myriamfournier.olympics_ticket_office.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myriamfournier.olympics_ticket_office.pojo.userskeys;
import com.myriamfournier.olympics_ticket_office.repository.UserskeyRepository;
import com.myriamfournier.olympics_ticket_office.service.UserskeyService;

@Service
public class UserskeyServiceImpl implements UserskeyService{

@Autowired
    private UserskeyRepository userskeyRepository; // Assuming you have a CartRepository interface


    // Implement the methods defined in CartService interface here
    
    @Override
    public List<userskeys> getAllUserskeys() {
        return userskeyRepository.findAllWithDetails();
    }



    @Override
    public List<userskeys> getAllWithKeysgenerations() {
        return userskeyRepository.findAllWithKeysgenerations();
    }


    @Override
    public userskeys getUserskeyById(Long id) {
        return userskeyRepository.findById(id).orElse(null);
    }



    @Override
    public void createUserskey(userskeys userskeys) {
        userskeyRepository.save(userskeys);
    }


    @Override
    public void updateUserskeyById(userskeys userskeys, Long id) {
            // (EN) A record is immutable,
            // (EN) impossible to modify.
            // (EN) Therefore, we must recover the element, modify it
            // (EN) put it back in base.
            // (FR) Un enregistement est immuable
            // (FR) impossible à modifier
            // (FR) De ce fait, on doit recuperer l'element, le modifier
            // (FR) le remettre en base.
            userskeys oldUserskey = getUserskeyById(id);

        if(oldUserskey != null){
            oldUserskey.setDate(userskeys.getDate());
            userskeyRepository.save(oldUserskey);
        }
    }




    @Override
    public void deleteUserskeyById(Long id) {
        userskeyRepository.deleteById(id);
    }



  
 

}
