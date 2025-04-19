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
        return eventRepository.findAllEvents();
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
            oldEvent.setDate(events.getDate());
            oldEvent.setDescription(events.getDescription());
            oldEvent.setPriceC1(events.getPriceC1());
            oldEvent.setNbSeatsSoldC1(events.getNbSeatsSoldC1());
            oldEvent.setNbSeatsAvailC1(events.getNbSeatsAvailC1());
            oldEvent.setPriceC2(events.getPriceC2());
            oldEvent.setNbSeatsSoldC2(events.getNbSeatsSoldC2());
            oldEvent.setNbSeatsAvailC2(events.getNbSeatsAvailC2());
            oldEvent.setPriceC3(events.getPriceC3());
            oldEvent.setNbSeatsSoldC3(events.getNbSeatsSoldC3());
            oldEvent.setNbSeatsAvailC3(events.getNbSeatsAvailC3());
            eventRepository.save(oldEvent);
        }
    }




    @Override
    public void deleteEventById(Long id) {
        eventRepository.deleteById(id);
    }

  
 

}
