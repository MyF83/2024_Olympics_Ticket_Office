package com.myriamfournier.olympics_ticket_office.service;

import com.myriamfournier.olympics_ticket_office.pojo.carts;
import com.myriamfournier.olympics_ticket_office.pojo.events;
import com.myriamfournier.olympics_ticket_office.pojo.offers;
import com.myriamfournier.olympics_ticket_office.pojo.users;

/**
 * Service interface for QR code generation with comprehensive ticket data.
 */
public interface QRCodeGeneratorService {
    
    /**
     * Generates a QR code with comprehensive ticket data.
     *
     * @param user The user information
     * @param event The event information
     * @param offer The offer information
     * @param cart The cart information
     * @param keyAssembly The 128-character key assembly (64 user key + 64 sale key)
     * @return The filename of the generated QR code
     */
    String generateQRCodeWithTicketData(users user, events event, offers offer, carts cart, String keyAssembly);
    
    /**
     * Generates a 40-character random filename for QR codes.
     *
     * @return A unique 40-character string
     */
    String generateUnique40CharacterName();
    
    /**
     * Generates a ticket number based on user's initials and date.
     *
     * @param firstname User's first name
     * @param lastname User's last name
     * @return A unique ticket number
     */
    String generateTicketNumber(String firstname, String lastname);
    
    /**
     * Generates a QR code image file with the specified content.
     *
     * @param content The content to encode in the QR code
     * @param fileName The filename for the QR code image
     * @throws Exception If QR code generation fails
     */
    void generateQRCodeImage(String content, String fileName) throws Exception;
}