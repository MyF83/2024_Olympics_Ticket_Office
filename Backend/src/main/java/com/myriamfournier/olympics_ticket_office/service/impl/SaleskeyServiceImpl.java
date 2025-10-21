package com.myriamfournier.olympics_ticket_office.service.impl;

import java.security.SecureRandom;
import java.sql.Date;
import java.util.List;
import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myriamfournier.olympics_ticket_office.pojo.keysgenerations;
import com.myriamfournier.olympics_ticket_office.pojo.saleskeys;
import com.myriamfournier.olympics_ticket_office.repository.KeysgenerationRepository;
import com.myriamfournier.olympics_ticket_office.repository.SaleskeyRepository;
import com.myriamfournier.olympics_ticket_office.service.SaleskeyService;

@Service
public class SaleskeyServiceImpl implements SaleskeyService{

@Autowired
    private SaleskeyRepository saleskeyRepository;
    
@Autowired
    private KeysgenerationRepository keysgenerationRepository;
    
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

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
    
    @Override
    public saleskeys createSalekey(Long sale_id) {
        try {
            // Generate SHA-256 hash
            String saleKeyHash = generateSaleKeyHash(sale_id);
            
            // Create keysgenerations entry
            keysgenerations keyGen = new keysgenerations();
            keyGen.setKeyGenerated(saleKeyHash);
            keysgenerationRepository.save(keyGen);
            
            // Create saleskeys entry
            saleskeys saleKey = new saleskeys();
            saleKey.setDate(new Date(System.currentTimeMillis()));
            saleKey.setKeysgenerations(keyGen);
            saleskeyRepository.save(saleKey);
            
            return saleKey;
        } catch (Exception e) {
            throw new RuntimeException("Error creating sale key", e);
        }
    }
    
    @Override
    public String generateSaleKeyHash(Long sale_id) {
        try {
            // Generate timestamp
            long timestamp = System.currentTimeMillis();
            
            // Generate 256 random characters
            StringBuilder randomChars = new StringBuilder(256);
            for (int i = 0; i < 256; i++) {
                randomChars.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
            }
            
            // Combine: timestamp + sale_id + random_characters
            String input = timestamp + sale_id.toString() + randomChars.toString();
            
            // Generate SHA-256 hash
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes("UTF-8"));
            
            // Convert to hex string (64 characters)
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error generating sale key hash", e);
        }
    }

}
