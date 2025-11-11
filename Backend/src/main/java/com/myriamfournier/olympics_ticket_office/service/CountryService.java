package com.myriamfournier.olympics_ticket_office.service;

import java.util.List;

import com.myriamfournier.olympics_ticket_office.pojo.countries;

public interface CountryService{

    List<countries> getAllCountries();
    
    countries getCountryById(Long id);

    void createCountry(countries countries);

    void updateCountryById(Long id, countries countries);


    void deleteCountryById(Long id);


    


}
