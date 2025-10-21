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
        return userselectionRepository.findAllWithDetails();
    }

    @Override
    public List<userselections> getAllWithOffers() {
        return userselectionRepository.findAllWithOffers();
    }

    @Override
    public List<userselections> getAllWithEvents() {
        return userselectionRepository.findAllWithEvents();
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
            // Update basic fields
            if(userselections.getNbPersons() != null) {
                oldUserSelection.setNbPersons(userselections.getNbPersons());
            }
            if(userselections.getAmount() != null) {
                oldUserSelection.setAmount(userselections.getAmount());
            }
            if(userselections.getSeat_class() != null) {
                oldUserSelection.setSeat_class(userselections.getSeat_class());
            }
            
            // Update relationships
            if(userselections.getOffers() != null) {
                oldUserSelection.setOffers(userselections.getOffers());
            }
            if(userselections.getEvents() != null) {
                oldUserSelection.setEvents(userselections.getEvents());
            }
            
            userselectionRepository.save(oldUserSelection);
        }
    }




    @Override
    public void deleteUserSelectionById(Long id) {
        userselectionRepository.deleteById(id);
    }

    @Override
    public Float calculateAmount(userselections userSelection) {
        // New calculation formula: (((nb_persons - offer.nb_spectators) * seat_class_price) + ((offer.nb_spectators * seat_class_price * (100 - offer.rate)) / 100))
        
        if (userSelection.getEvents() == null || userSelection.getOffers() == null || 
            userSelection.getNbPersons() == null || userSelection.getSeat_class() == null) {
            return 0.0f; // Return 0 if missing required data
        }
        
        Integer nbPersons = userSelection.getNbPersons();
        Integer offerNbSpectators = userSelection.getOffers().getNbSpectators();
        Integer offerRate = userSelection.getOffers().getRate();
        
        // Get seat class price from event
        Float seatClassPrice = getSeatClassPrice(userSelection.getEvents(), userSelection.getSeat_class());
        
        if (seatClassPrice == null) {
            return 0.0f; // Return 0 if can't determine seat price
        }
        
        // Calculate extra persons beyond offer coverage
        Integer extraPersons = Math.max(0, nbPersons - offerNbSpectators);
        Float extraPersonsAmount = extraPersons * seatClassPrice;
        
        // Calculate discounted price for persons covered by offer
        // Updated formula: (nb_spectators * seat_class_price * (100 - offer.rate)) / 100
        Float offerPersonsAmount = (offerNbSpectators * seatClassPrice * (100 - offerRate)) / 100.0f;
        
        return extraPersonsAmount + offerPersonsAmount;
    }
    
    private Float getSeatClassPrice(com.myriamfournier.olympics_ticket_office.pojo.events event, String seatClass) {
        if (event == null || seatClass == null) {
            return null;
        }
        
        switch (seatClass) {
            case "1":
            case "price1":
                return event.getPricec1();
            case "2":
            case "price2":
                return event.getPricec2();
            case "3":
            case "price3":
                return event.getPricec3();
            default:
                return event.getPricec1(); // Default to price class 1
        }
    }

}
