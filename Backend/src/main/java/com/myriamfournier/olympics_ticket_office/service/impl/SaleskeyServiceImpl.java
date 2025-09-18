package com.myriamfournier.olympics_ticket_office.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.myriamfournier.olympics_ticket_office.pojo.saleskeys;
import com.myriamfournier.olympics_ticket_office.repository.SaleskeyRepository;
import com.myriamfournier.olympics_ticket_office.service.SaleskeyService;

@Service
public class SaleskeyServiceImpl implements SaleskeyService{

@Autowired
    
    private SaleskeyRepository saleskeyRepository; // Assuming you have a SaleskeyRepository interface
    

    // Implement the methods defined in SaleskeyService interface here
    
    @Override
    public List<saleskeys> getAllSaleskeys() {
        return saleskeyRepository.findAllWithDetails();
    }

    
    @Override
    public List<saleskeys> getAllWithKeysgenerations() {
        return saleskeyRepository.findAllWithKeysgenerations();
    }




    
    @Override
    public saleskeys getSaleskeyById(Long id) {
        return saleskeyRepository.findById(id).orElse(null);
    }



    @Override
    public void createSaleskey(saleskeys saleskeys) {
        saleskeyRepository.save(saleskeys);
    }



    @Override
    public void updateSaleskeyById(saleskeys saleskeys, Long id) {
            // (EN) A record is immutable,
            // (EN) impossible to modify.
            // (EN) Therefore, we must recover the element, modify it
            // (EN) put it back in base.
            // (FR) Un enregistement est immuable
            // (FR) impossible à modifier
            // (FR) De ce fait, on doit recuperer l'element, le modifier
            // (FR) le remettre en base.
            saleskeys oldSaleskey = getSaleskeyById(id);

        if(oldSaleskey != null){
            oldSaleskey.setDate(saleskeys.getDate());
            saleskeyRepository.save(oldSaleskey);
        }
    }




    @Override
    public void deleteSaleskeyById(Long id) {
        saleskeyRepository.deleteById(id);
    }

  
 

}
