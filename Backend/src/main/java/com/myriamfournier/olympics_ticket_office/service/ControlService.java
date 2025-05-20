package com.myriamfournier.olympics_ticket_office.service;

import java.util.List;

import com.myriamfournier.olympics_ticket_office.pojo.controls;

public interface ControlService{

    List<controls> getAllControls();
    List<controls> getAllWithTickets();
    // List<controls> getAllWithUserskeys();
    // List<controls> getAllWithSaleskeys();

    controls getControlById(Long id);

    void createControl(controls controls);

    void updateControlById(controls controls, Long id);


    void deleteControlById(Long id);

    


    


}
