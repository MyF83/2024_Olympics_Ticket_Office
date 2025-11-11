package com.myriamfournier.olympics_ticket_office.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myriamfournier.olympics_ticket_office.pojo.countries;
import com.myriamfournier.olympics_ticket_office.repository.CountryRepository;
import com.myriamfournier.olympics_ticket_office.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService{

@Autowired
    private CountryRepository countryRepository; // Assuming you have a countryRepository interface


    // Implement the methods defined in CountryService interface here
    
    @Override
    public List<countries> getAllCountries() {
        return countryRepository.findAllCountries();
    }


    @Override
    public countries getCountryById(Long id) {
        return countryRepository.findById(id).orElse(null);
    }



    @Override
    public void createCountry(countries countries) {
        countryRepository.save(countries);
    }


    @Override
    public void updateCountryById(Long id, countries countries) {
            // un enregistement est immuable
            // impossible à modifier
            // de ce fait, on doit recuperer l'element, le modifier
            // le remettre
            countries oldCountry = getCountryById(id);

        if(oldCountry != null){
            oldCountry.setName(countries.getName());
            oldCountry.setDescription(countries.getDescription());
            countryRepository.save(oldCountry);
        }
    }




    @Override
    public void deleteCountryById(Long id) {
        countryRepository.deleteById(id);
    }

  
 

}
