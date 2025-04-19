package com.myriamfournier.olympics_ticket_office.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myriamfournier.olympics_ticket_office.pojo.offers;
import com.myriamfournier.olympics_ticket_office.repository.OfferRepository;
import com.myriamfournier.olympics_ticket_office.service.OfferService;

@Service
public class OfferServiceImpl implements OfferService{

@Autowired
    private OfferRepository offerRepository; // Assuming you have a OfferRepository interface


    // Implement the methods defined in OfferService interface here
    
    @Override
    public List<offers> getAllOffers() {
        return offerRepository.findAllOffers();
    }


    @Override
    public offers getOfferById(Long id) {
        return offerRepository.findById(id).orElse(null);
    }



    @Override
    public void createOffer(offers offers) {
        offerRepository.save(offers);
    }


    @Override
    public void updateOfferById(offers offers, Long id) {
            // un enregistement est immuable
            // impossible à modifier
            // de ce fait, on doit recuperer l'element, le modifier
            // le remettre
        offers oldOffer = getOfferById(id);

        if(oldOffer != null){
            oldOffer.setTitle(offers.getTitle());
            oldOffer.setDescription(offers.getDescription());
            oldOffer.setRate(offers.getRate());
            oldOffer.setNbSpectators(offers.getNbSpectators());
            offerRepository.save(oldOffer);
        }
    }




    @Override
    public void deleteOfferById(Long id) {
        offerRepository.deleteById(id);
    }

  
 

}
