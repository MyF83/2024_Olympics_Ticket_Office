package com.myriamfournier.olympics_ticket_office.service;

import java.util.List;

import com.myriamfournier.olympics_ticket_office.pojo.events;

public interface EventService{

    List<events> getAllEvents();

    events getEventById(Long id);

    void createEvent(events events);

    void updateEventById(events events, Long id);


    void deleteEventById(Long id);


    


}
