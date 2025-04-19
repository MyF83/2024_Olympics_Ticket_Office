package com.myriamfournier.olympics_ticket_office.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myriamfournier.olympics_ticket_office.pojo.keysgenerations;
import com.myriamfournier.olympics_ticket_office.pojo.sales;
import com.myriamfournier.olympics_ticket_office.pojo.saleskeys;
import com.myriamfournier.olympics_ticket_office.repository.SaleRepository;
import com.myriamfournier.olympics_ticket_office.repository.KeysgenerationRepository;
import com.myriamfournier.olympics_ticket_office.repository.SaleskeyRepository;
import com.myriamfournier.olympics_ticket_office.service.SaleService;

@Service
public class SaleServiceImpl implements SaleService{

@Autowired
    private SaleRepository saleRepository; // Assuming you have a SaleRepository interface
    private SaleskeyRepository saleskeyRepository; // Assuming you have a SaleskeyRepository interface
    private KeysgenerationRepository keysgenerationRepository; // Assuming you have a KeysgenerationRepository interface

    // Implement the methods defined in SaleService interface here
    
    @Override
    public List<sales> getAllSales() {
        return saleRepository.findAllSales();
    }


    @Override
    public sales getSaleById(Long id) {
        return saleRepository.findById(id).orElse(null);
    }



    @Override
    public void createSale(sales sales) {
        saleRepository.save(sales);
    
     // Generate a unique 256-character key
        String uniqueKey = generate256CharacterKey();

     // Save the key in the Keysgenerations table
        keysgenerations keysEntity = new keysgenerations(uniqueKey);
        keysEntity.setKeyGenerated(uniqueKey);
            keysgenerationRepository.save(keysEntity);

            
        // Link the key to the user in the Saleskeys table
        saleskeys saleskeys = new saleskeys(null, keysEntity);
             saleskeys.setSale(sales);
            saleskeys.setKey(keysEntity);
            saleskeyRepository.save(saleskeys);
    }

    private String generate256CharacterKey() {
        StringBuilder keyBuilder = new StringBuilder();
        while (keyBuilder.length() < 256) {
            keyBuilder.append(java.util.UUID.randomUUID().toString().replace("-", ""));
        }
        return keyBuilder.substring(0, 256); // Ensure the key is exactly 256 characters
    }


    @Override
    public void updateSaleById(sales sales, Long id) {
            // un enregistement est immuable
            // impossible à modifier
            // de ce fait, on doit recuperer l'element, le modifier
            // le remettre
            sales oldSale = getSaleById(id);

        if(oldSale != null){
            oldSale.setDate(sales.getDate());
            saleRepository.save(oldSale);
        }
    }




    @Override
    public void deleteSaleById(Long id) {
        saleRepository.deleteById(id);
    }

  
 

}
