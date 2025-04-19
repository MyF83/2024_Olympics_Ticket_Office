package com.myriamfournier.olympics_ticket_office.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myriamfournier.olympics_ticket_office.pojo.keysgenerations;
import com.myriamfournier.olympics_ticket_office.repository.KeysgenerationRepository;
import com.myriamfournier.olympics_ticket_office.service.KeysgenerationsService;

@Service
public class KeysgenerationServiceImpl implements KeysgenerationsService{

@Autowired
    private KeysgenerationRepository keysgenerationsRepository; // Assuming you have a KeysgenerationsRepository interface


    // Implement the methods defined in KeysgenerationsService interface here
    
    @Override
    public List<keysgenerations> getAllKeysgenerations() {
        return keysgenerationsRepository.findAllKeysgenerations();
    }


    @Override
    public keysgenerations getKeysgenerationById(Long id) {
        return keysgenerationsRepository.findById(id).orElse(null);
    }



    @Override
    public void createKeysgeneration(keysgenerations keysgenerations) {
        keysgenerationsRepository.save(keysgenerations);
    }


    @Override
    public void updateKeysgenerationById(keysgenerations keysgenerations, Long id) {
            // un enregistement est immuable
            // impossible à modifier
            // de ce fait, on doit recuperer l'element, le modifier
            // le remettre
            keysgenerations oldKeysgeneration = getKeysgenerationById(id);

        if(oldKeysgeneration != null){
            keysgenerationsRepository.save(oldKeysgeneration);
        }
    }




    @Override
    public void deleteKeysgenerationById(Long id) {
        keysgenerationsRepository.deleteById(id);
    }

  
 

}
