package com.myriamfournier.olympics_ticket_office.service.impl;

import java.security.SecureRandom;
import java.sql.Date;
import java.util.List;
import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myriamfournier.olympics_ticket_office.pojo.keysgenerations;
import com.myriamfournier.olympics_ticket_office.pojo.userskeys;
import com.myriamfournier.olympics_ticket_office.repository.KeysgenerationRepository;
import com.myriamfournier.olympics_ticket_office.repository.UserskeyRepository;
import com.myriamfournier.olympics_ticket_office.service.UserskeyService;

@Service
public class UserskeyServiceImpl implements UserskeyService{

@Autowired
    private UserskeyRepository userskeyRepository;
    
@Autowired
    private KeysgenerationRepository keysgenerationRepository;
    
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    // Implement the methods defined in UserskeyService interface here
    
    @Override
    public List<userskeys> getAllUserskeys() {
        return userskeyRepository.findAllWithDetails();
    }



    @Override
    public List<userskeys> getAllWithKeysgenerations() {
        return userskeyRepository.findAllWithKeysgenerations();
    }


    @Override
    public userskeys getUserskeyById(Long id) {
        return userskeyRepository.findById(id).orElse(null);
    }



    @Override
    public void createUserskey(userskeys userskeys) {
        userskeyRepository.save(userskeys);
    }


    @Override
    public void updateUserskeyById(userskeys userskeys, Long id) {
            // (EN) A record is immutable,
            // (EN) impossible to modify.
            // (EN) Therefore, we must recover the element, modify it
            // (EN) put it back in base.
            // (FR) Un enregistement est immuable
            // (FR) impossible à modifier
            // (FR) De ce fait, on doit recuperer l'element, le modifier
            // (FR) le remettre en base.
            userskeys oldUserskey = getUserskeyById(id);

        if(oldUserskey != null){
            oldUserskey.setDate(userskeys.getDate());
            userskeyRepository.save(oldUserskey);
        }
    }




    @Override
    public void deleteUserskeyById(Long id) {
        userskeyRepository.deleteById(id);
    }
    
    @Override
    public userskeys createUserkey(Long user_id) {
        try {
            // Generate SHA-256 hash
            String userKeyHash = generateUserKeyHash(user_id);
            
            // Create keysgenerations entry
            keysgenerations keyGen = new keysgenerations();
            keyGen.setKeyGenerated(userKeyHash);
            keysgenerationRepository.save(keyGen);
            
            // Create userskeys entry
            userskeys userKey = new userskeys();
            userKey.setDate(new Date(System.currentTimeMillis()));
            userKey.setKeysgenerations(keyGen);
            userskeyRepository.save(userKey);
            
            return userKey;
        } catch (Exception e) {
            throw new RuntimeException("Error creating user key", e);
        }
    }
    
    @Override
    public String generateUserKeyHash(Long user_id) {
        try {
            // Generate timestamp
            long timestamp = System.currentTimeMillis();
            
            // Generate 256 random characters
            StringBuilder randomChars = new StringBuilder(256);
            for (int i = 0; i < 256; i++) {
                randomChars.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
            }
            
            // Combine: timestamp + user_id + random_characters
            String input = timestamp + user_id.toString() + randomChars.toString();
            
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
            throw new RuntimeException("Error generating user key hash", e);
        }
    }

}
