package com.myriamfournier.olympics_ticket_office.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myriamfournier.olympics_ticket_office.pojo.userselections;
import com.myriamfournier.olympics_ticket_office.repository.UserSelectionRepository;
import com.myriamfournier.olympics_ticket_office.service.UserSelectionService;

@Service
public class UserSelectionServiceImpl implements UserSelectionService{

@Autowired
    private UserSelectionRepository userselectionRepository; // Assuming you have a UserSelectionRepository interface


    // Implement the methods defined in UserSelectionService interface here
    
    @Override
    public List<userselections> getAllUserSelections() {
        return userselectionRepository.findAllUserSelections();
    }


    @Override
    public userselections getUserSelectionById(Long id) {
        return userselectionRepository.findById(id).orElse(null);
    }



    @Override
    public void createUserSelection(userselections userselections) {
        userselectionRepository.save(userselections);
    }


    @Override
    public void updateUserSelectionById(userselections userselections, Long id) {
            // (EN) A record is immutable,
            // (EN) impossible to modify.
            // (EN) Therefore, we must recover the element, modify it
            // (EN) put it back in base.
            // (FR) Un enregistement est immuable
            // (FR) impossible à modifier
            // (FR) De ce fait, on doit recuperer l'element, le modifier
            // (FR) le remettre en base.
        userselections oldUserSelection = getUserSelectionById(id);

        if(oldUserSelection != null){
            oldUserSelection.setNbPersons(userselections.getNbPersons());
            oldUserSelection.setAmount(userselections.getAmount());
            userselectionRepository.save(oldUserSelection);
        }
    }




    @Override
    public void deleteUserSelectionById(Long id) {
        userselectionRepository.deleteById(id);
    }

  
 

}
