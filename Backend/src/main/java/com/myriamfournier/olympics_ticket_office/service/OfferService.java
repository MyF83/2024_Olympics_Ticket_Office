package com.myriamfournier.olympics_ticket_office.service;

import java.util.List;

import com.myriamfournier.olympics_ticket_office.pojo.offers;

public interface OfferService{

    List<offers> getAllOffers();

    offers getOfferById(Long id);

    void createOffer(offers offers);

    void updateOfferById(offers offers, Long id);


    void deleteOfferById(Long id);


    


}
