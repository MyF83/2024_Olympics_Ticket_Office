package com.myriamfournier.olympics_ticket_office.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.myriamfournier.olympics_ticket_office.pojo.carts;
import com.myriamfournier.olympics_ticket_office.pojo.events;
import com.myriamfournier.olympics_ticket_office.pojo.offers;
import com.myriamfournier.olympics_ticket_office.pojo.users;
import com.myriamfournier.olympics_ticket_office.repository.TicketRepository;
import com.myriamfournier.olympics_ticket_office.service.QRCodeGeneratorService;

@Service
public class QRCodeGeneratorServiceImpl implements QRCodeGeneratorService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public String generateQRCodeWithTicketData(users user, events event, offers offer, carts cart, String keyAssembly) {
        try {
            // Generate unique filename
            String fileName = generateUnique40CharacterName();
            
            // Generate ticket number
            String ticketNumber = generateTicketNumber(user.getFirstname(), user.getLastname());
            
            // Build comprehensive QR code data
            StringBuilder qrData = new StringBuilder();
            qrData.append("OLYMPIC TICKET 2024\n");
            qrData.append("==================\n");
            qrData.append("Ticket Number: ").append(ticketNumber).append("\n");
            qrData.append("Key Assembly: ").append(keyAssembly).append("\n\n");
            
            // User Information
            qrData.append("HOLDER INFORMATION:\n");
            qrData.append("Name: ").append(user.getFirstname()).append(" ").append(user.getLastname()).append("\n");
            qrData.append("Email: ").append(user.getEmail()).append("\n\n");
            
            // Event Information
            qrData.append("EVENT DETAILS:\n");
            qrData.append("Sport: ").append(event.getSports() != null ? event.getSports().getName() : "N/A").append("\n");
            qrData.append("Event: ").append(event.getTitle()).append("\n");
            qrData.append("Date: ").append(event.getDate()).append("\n");
            qrData.append("Time: ").append(event.getTime()).append("\n");
            qrData.append("Location: ").append(event.getSports() != null && event.getSports().getSites() != null ? 
                event.getSports().getSites().getName() : "N/A").append("\n\n");
            
            // Ticket Details
            qrData.append("TICKET DETAILS:\n");
            qrData.append("Offer: ").append(offer != null ? offer.getTitle() : "N/A").append("\n");
            qrData.append("Category: General\n"); // Default category
            qrData.append("Seat: TBD\n"); // Default seat
            qrData.append("Quantity: 1\n"); // Default quantity
            qrData.append("Price: €").append(cart.getTotalAmount()).append("\n\n");
            
            // Security Information
            qrData.append("SECURITY:\n");
            qrData.append("This ticket is valid only with valid ID\n");
            qrData.append("Verification Code: ").append(keyAssembly.substring(0, 16)).append("...\n");
            
            // Generate QR code and save to Frontend/public/QRCodes directory
            generateQRCode(qrData.toString(), fileName);
            
            return fileName + ".png";
        } catch (Exception e) {
            throw new RuntimeException("Error generating QR code with ticket data", e);
        }
    }
    
    @Override
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
    
    @Override
    public String generateTicketNumber(String firstname, String lastname) {
        if (firstname == null || lastname == null || firstname.isEmpty() || lastname.isEmpty()) {
            throw new IllegalArgumentException("First name and last name cannot be null or empty");
        }
        
        // Get initials
        char firstInitial = Character.toUpperCase(firstname.charAt(0));
        char lastInitial = Character.toUpperCase(lastname.charAt(0));
        
        // Get current date in YYYYMMDD format
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        
        // Generate 6-digit random number
        String randomNum = String.format("%06d", (int)(Math.random() * 1000000));
        
        // Combine all parts
        return firstInitial + "" + lastInitial + "-" + date + "-" + randomNum;
    }
    
    @Override
    public void generateQRCodeImage(String content, String fileName) throws Exception {
        try {
            // QR code size
            int size = 300;
            
            // Generate the QR code matrix
            BitMatrix bitMatrix = generateMatrix(content, size);
            if (bitMatrix == null) {
                throw new Exception("QR code generation failed");
            }
            
            // Define the output file path - save to Frontend/public/QRCodes
            String imageFormat = "png";
            
            // Get the absolute path to Frontend/public/QRCodes directory
            String projectRoot = System.getProperty("user.dir");
            String outputFolder = Paths.get(projectRoot, "..", "Frontend", "public", "QRCodes").toAbsolutePath().toString();
            new File(outputFolder).mkdirs(); // Ensure the folder exists
            
            String outputFileName = outputFolder + File.separator + fileName;
            
            // Write the QR code to the file
            writeImage(outputFileName, imageFormat, bitMatrix);
            
            System.out.println("QR code image generated successfully: " + outputFileName);
            
        } catch (Exception e) {
            System.out.println("Failed to generate QR code image: " + e.getMessage());
            throw e;
        }
    }
    
    private String generate40CharacterName() {
        StringBuilder keyBuilder = new StringBuilder();
        while (keyBuilder.length() < 40) {
            keyBuilder.append(java.util.UUID.randomUUID().toString().replace("-", ""));
        }
        return keyBuilder.substring(0, 40); // Ensure the key is exactly 40 characters
    }
    
    private void generateQRCode(String data, String filename) {
        try {
            // QR code size
            int size = 300;
            
            // Generate the QR code matrix
            BitMatrix bitMatrix = generateMatrix(data, size);
            if (bitMatrix == null) {
                throw new RuntimeException("QR code generation failed");
            }
            
            // Define the output file path - save to Frontend/public/QRCodes
            String imageFormat = "png";
            
            // Get the absolute path to Frontend/public/QRCodes directory
            String projectRoot = System.getProperty("user.dir");
            String outputFolder = Paths.get(projectRoot, "..", "Frontend", "public", "QRCodes").toAbsolutePath().toString();
            new File(outputFolder).mkdirs(); // Ensure the folder exists
            
            String outputFileName = outputFolder + File.separator + filename + "." + imageFormat;
            
            // Write the QR code to the file
            writeImage(outputFileName, imageFormat, bitMatrix);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to generate QR code", e);
        }
    }
    
    private static void writeImage(String outputFileName, String imageFormat, BitMatrix bitMatrix) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(outputFileName))) {
            MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to write QR code image", e);
        }
    }
    
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