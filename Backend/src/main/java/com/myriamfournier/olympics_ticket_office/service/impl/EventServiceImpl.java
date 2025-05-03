package com.myriamfournier.olympics_ticket_office.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myriamfournier.olympics_ticket_office.pojo.events;
import com.myriamfournier.olympics_ticket_office.repository.EventRepository;
import com.myriamfournier.olympics_ticket_office.service.EventService;

@Service
public class EventServiceImpl implements EventService{

@Autowired
    private EventRepository eventRepository; // Assuming you have a EventRepository interface


    // Implement the methods defined in EventService interface here
    
    @Override
    public List<events> getAllEvents() {
        return eventRepository.findAllWithDetails();
    }

    @Override
    public List<events> getAllWithSports() {
        return eventRepository.findAllWithSports();
    }

    @Override
    public List<events> getAllWithCeremonies() {
        return eventRepository.findAllWithCeremonies();
    }

    @Override
    public List<events> getAllWithChallenger1() {
        return eventRepository.findAllWithChallenger1();
    }

    @Override
    public List<events> getAllWithChallenger2() {
        return eventRepository.findAllWithChallenger2();
    }


    @Override
    public events getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }



    @Override
    public void createEvent(events events) {
        eventRepository.save(events);
    }


    @Override
    public void updateEventById(events events, Long id) {
            // un enregistement est immuable
            // impossible à modifier
            // de ce fait, on doit recuperer l'element, le modifier
            // le remettre
        events oldEvent = getEventById(id);

        if(oldEvent != null){
            oldEvent.setTitle(events.getTitle());
            oldEvent.setDate(events.getDate());
            oldEvent.setImage(events.getImage());
            oldEvent.setDescription(events.getDescription());
            oldEvent.setPricec1(events.getPricec1());
            oldEvent.setNbseatssoldc1(events.getNbseatssoldc1());
            oldEvent.setNbseatsavailc1(events.getNbseatsavailc1());
            oldEvent.setPricec2(events.getPricec2());
            oldEvent.setNbseatssoldc2(events.getNbseatssoldc2());
            oldEvent.setNbseatsavailc2(events.getNbseatsavailc2());
            oldEvent.setPricec3(events.getPricec3());
            oldEvent.setNbseatssoldc3(events.getNbseatssoldc3());
            oldEvent.setNbseatsavailc3(events.getNbseatsavailc3());
            eventRepository.save(oldEvent);
        }
    }




    @Override
    public void deleteEventById(Long id) {
        eventRepository.deleteById(id);
    }

  
 

}
