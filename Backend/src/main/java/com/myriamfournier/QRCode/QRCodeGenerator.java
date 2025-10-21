package com.myriamfournier.QRCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.myriamfournier.olympics_ticket_office.Application;
import com.myriamfournier.olympics_ticket_office.pojo.tickets;
import com.myriamfournier.olympics_ticket_office.repository.TicketRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;

/**
 * This class is responsible for generating QR codes and managing unique file names.
 */
@Service
public class QRCodeGenerator {

    /**
     * Repository for accessing ticket data.
     */
    @Autowired
    private TicketRepository ticketRepository;

    /**
     * Retrieves the key assembly for the QR code by combining user key and sale key.
     *
     * @param userKeyValue The user key to use in the assembly
     * @param saleKeyValue The sale key to use in the assembly
     * @return A string representing the key assembly.
     */
    public String getKeyAssembly(String userKeyValue, String saleKeyValue) {
        if (userKeyValue == null || saleKeyValue == null) {
            throw new IllegalArgumentException("User key and sale key cannot be null");
        }
        return userKeyValue + "-" + saleKeyValue;
    }

    /**
     * Generates a random 40-character name.
     *
     * @return A string of exactly 40 characters.
     */
    public String generate40CharacterName() {
        StringBuilder keyBuilder = new StringBuilder();
        while (keyBuilder.length() < 40) {
            keyBuilder.append(java.util.UUID.randomUUID().toString().replace("-", ""));
        }
        return keyBuilder.substring(0, 40); // Ensure the key is exactly 40 characters
    }
    
    /**
     * Generates a unique 40-character name by checking against the database.
     *
     * @return A unique string of 40 characters.
     */
    public String generateUnique40CharacterName() {
        String baseName = generate40CharacterName();
        String uniqueName = baseName;
        int counter = 1;
        
        // Check if the name already exists in the database
        while (ticketRepository.existsByFileName(uniqueName)) {
            uniqueName = baseName + "-" + counter;
            counter++;
        }
        
        return uniqueName;
    }

    /**
     * Generates a ticket number based on user's first and last name initials, date and a random number.
     * 
     * @param firstname User's first name
     * @param lastname User's last name
     * @return A unique ticket number
     */
    public String generateTicketNumber(String firstname, String lastname) {
        if (firstname == null || lastname == null || firstname.isEmpty() || lastname.isEmpty()) {
            throw new IllegalArgumentException("First name and last name cannot be null or empty");
        }
        
        // Get initials
        char firstInitial = Character.toUpperCase(firstname.charAt(0));
        char lastInitial = Character.toUpperCase(lastname.charAt(0));
        
        // Get current date in YYYYMMDD format
        String date = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
        
        // Generate 6-digit random number
        String randomNum = String.format("%06d", (int)(Math.random() * 1000000));
        
        // Combine all parts
        return firstInitial + "" + lastInitial + "-" + date + "-" + randomNum;
    }

    /**
     * Generates a QR code for a ticket and saves it to a file.
     *
     * @param data The data to encode in the QR code.
     * @param filename The filename to save the QR code as.
     * @return The full path to the saved QR code file.
     */
    public String generateQRCode(String data, String filename) {
        try {
            // QR code size
            int size = 300;
            
            // Generate the QR code matrix
            BitMatrix bitMatrix = generateMatrix(data, size);
            if (bitMatrix == null) {
                throw new RuntimeException("QR code generation failed");
            }
            
            // Define the output file path and format
            String imageFormat = "png";
            
            // Resolve the QRcodes folder in the resources directory
            String outputFolder = Paths.get("src", "main", "resources", "QRcodes").toAbsolutePath().toString();
            new File(outputFolder).mkdirs(); // Ensure the folder exists
            
            String outputFileName = outputFolder + File.separator + filename + "." + imageFormat;
            
            // Write the QR code to the file
            writeImage(outputFileName, imageFormat, bitMatrix);
            
            return outputFileName;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to generate QR code", e);
        }
    }

    /**
     * Writes the QR code image to a file.
     *
     * @param outputFileName The name of the output file.
     * @param imageFormat The format of the image (e.g., png).
     * @param bitMatrix The QR code matrix to write.
     */
    private static void writeImage(String outputFileName, String imageFormat, BitMatrix bitMatrix) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(outputFileName))) {
            MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to write QR code image", e);
        }
    }

    /**
     * Generates a QR code matrix from the given data.
     *
     * @param data The data to encode in the QR code.
     * @param size The size of the QR code.
     * @return The generated QR code matrix.
     */
    private static BitMatrix generateMatrix(String data, int size) {
        try {
            BitMatrix bitMatrix = new QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, size, size);
            return bitMatrix;
        } catch (com.google.zxing.WriterException e) {
            e.printStackTrace();
            return null;
        }
    }
}