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
        return saleRepository.findAllWithDetails();
    }

    @Override
    public List<sales> getAllWithUsers() {
        return saleRepository.findAllWithUsers();
    }

    @Override
    public List<sales> getAllWithCarts() {
        return saleRepository.findAllWithCarts();
    }

    @Override
    public List<sales> getAllWithSaleskeys() {
        return saleRepository.findAllWithSaleskeys();
    }


    @Override
    public sales getSaleById(Long id) {
        return saleRepository.findById(id).orElse(null);
    }

    @Override
    public sales setSale(sales sales) {
        // Update the fields of the sales object
        sales.setSale_id(sales.getSale_id()); // Use setSale_id instead of setSaleId
        sales.setDate(sales.getDate());
        sales.setUsers(sales.getUsers());
        sales.setCarts(sales.getCarts());
        sales.setSaleskeys(sales.getSaleskeys());
        return sales;
    }
/* 
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
    }*/

    @Override
public void createSale(sales sales) {
    // Step 1: Generate a unique 256-character key
    String uniqueKey = generateUnique256CharacterKey();

    // Step 2: Save the key in the Keysgenerations table
    keysgenerations keysEntity = new keysgenerations(uniqueKey);
    keysEntity.setKeyGenerated(uniqueKey);
    keysgenerationRepository.save(keysEntity);

    // Step 3: Create a new Saleskeys object and link it to the generated key
    saleskeys saleskeys = new saleskeys();
    saleskeys.setKey(keysEntity); // Link the key to the Saleskeys object
    saleskeyRepository.save(saleskeys);

    // Step 4: Link the Saleskeys object to the Sales object
    sales.setSaleskeys(saleskeys); // Set the foreign key relationship
    saleRepository.save(sales); // Save the Sales object
}



    private String generateUnique256CharacterKey() {
        String baseKey = generate256CharacterKey();
        String uniqueKey = baseKey;
        int counter = 1;
    
        // Check if the key already exists in the database
        while (keysgenerationRepository.existsByKeyGenerated(uniqueKey)) {
            uniqueKey = baseKey + "-" + counter;
            counter++;
        }
    
        return uniqueKey;
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
            // (EN) A record is immutable,
            // (EN) impossible to modify.
            // (EN) Therefore, we must recover the element, modify it
            // (EN) put it back in base.
            // (FR) Un enregistement est immuable
            // (FR) impossible à modifier
            // (FR) De ce fait, on doit recuperer l'element, le modifier
            // (FR) le remettre en base.
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
