package com.myriamfournier.olympics_ticket_office.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myriamfournier.olympics_ticket_office.pojo.controls;
import com.myriamfournier.olympics_ticket_office.repository.ControlRepository;
import com.myriamfournier.olympics_ticket_office.service.ControlService;

@Service
public class ControlServiceImpl implements ControlService{

@Autowired
    private ControlRepository controlRepository; // Assuming you have a controlRepository interface


    // Implement the methods defined in ControlService interface here
    
    @Override
    public List<controls> getAllControls() {
        return controlRepository.findAllWithDetails();
    }

    @Override
    public List<controls> getAllWithTickets() {
        return controlRepository.findAllWithTickets();
    }
/* 
    @Override
    public List<controls> getAllWithUserskeys() {
        return controlRepository.findAllWithUserskeys();
    }

    @Override
    public List<controls> getAllWithSaleskeys() {
        return controlRepository.findAllWithSaleskeys();
    }*/


    @Override
    public controls getControlById(Long id) {
        return controlRepository.findById(id).orElse(null);
    }



    @Override
    public void createControl(controls controls) {
        controlRepository.save(controls);
    }

/* 
    @Override
    public void updateControlById(controls controls, Long id) {
            // un enregistement est immuable
            // impossible à modifier
            // de ce fait, on doit recuperer l'element, le modifier
            // le remettre
            controls oldControl = getControlById(id);

        if(oldControl != null){
            oldControl.setDate(controls.getDate());
            oldControl.setScancode(controls.getScancode());
            oldControl.setIsTicketValid(controls.getIsTicketValid());
            controlRepository.save(oldControl);
        }
    }*/

    @Override
    public void updateControlById(controls controls, Long id) {
        // un enregistement est immuable
        // impossible à modifier
        // de ce fait, on doit recuperer l'element, le modifier
        // le remettre
        controls oldControl = getControlById(id);
    
        if (oldControl != null) {
            oldControl.setDate(controls.getDate());
            oldControl.setScancode(controls.getScancode());
            oldControl.setIsTicketValid(controls.getIsTicketValid());
            controlRepository.save(oldControl);
        }
    }


    @Override
    public void deleteControlById(Long id) {
        controlRepository.deleteById(id);
    }

  
 

}
