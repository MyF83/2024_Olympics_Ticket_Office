package com.myriamfournier.olympics_ticket_office.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.myriamfournier.olympics_ticket_office.pojo.tickets;
import com.myriamfournier.olympics_ticket_office.pojo.userskeys;
import com.myriamfournier.olympics_ticket_office.Application;
import com.myriamfournier.olympics_ticket_office.pojo.saleskeys;
import com.myriamfournier.olympics_ticket_office.repository.TicketRepository;
import com.myriamfournier.olympics_ticket_office.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService{

@Autowired
    private TicketRepository ticketRepository; // Assuming you have a TicketRepository interface


    // Implement the methods defined in TicketService interface here
    
    @Override
    public List<tickets> getAllTickets() {
        return ticketRepository.findAllWithDetails();
    }

    @Override
    public List<tickets> getAllWithUserskeys() {
        return ticketRepository.findAllWithUserskeys();
    }

    @Override
    public List<tickets> getAllWithSaleskeys() {
        return ticketRepository.findAllWithSaleskeys();
    }

    @Override
    public List<tickets> getAllWithSales() {
        return ticketRepository.findAllWithSales();
    }

    @Override
    public List<tickets> getAllWithCarts() {
        return ticketRepository.findAllWithCarts();
    }

    @Override
    public List<tickets> getAllWithUsers() {
        return ticketRepository.findAllWithUsers();
    }

    @Override
    public List<tickets> getAllWithUserselections() {
        return ticketRepository.findAllWithUserselections();
    }


    @Override
    public tickets getTicketById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }



    @Override
    public void createTicket(tickets tickets) {
        // Fetch the user key
       // String userKey = ((userskeys) tickets.getUserskeys().getKey()).getKeyGenerated();
       String userKey = tickets.getUserskeys().getKey().getKeyGenerated();


        // Fetch the sale key
        //String saleKey = ((saleskeys) tickets.getSaleskeys().getKey()).getKeyGenerated();
        String saleKey = tickets.getSaleskeys().getKey().getKeyGenerated();

        // Assemble the keys
        String keyAssembly = userKey + saleKey;

        // Generate a unique 40-character name
        String uniqueFileName = generateUnique40CharacterName();

        // Set the unique file name in the ticket
        tickets.setFileName(uniqueFileName);

        // Set the assembled key in the ticket
        tickets.setKeyAssembly(keyAssembly);

        // Save the ticket to the database
        ticketRepository.save(tickets);
    }


    @Override
    public void updateTicketById(tickets tickets, Long id) {
            // (EN) A record is immutable,
            // (EN) impossible to modify.
            // (EN) Therefore, we must recover the element, modify it
            // (EN) put it back in base.
            // (FR) Un enregistement est immuable
            // (FR) impossible à modifier
            // (FR) De ce fait, on doit recuperer l'element, le modifier
            // (FR) le remettre en base.
            tickets oldTicket = getTicketById(id);

        if(oldTicket != null){
            oldTicket.setDate(tickets.getDate());
            oldTicket.setKeyAssembly(tickets.getKeyAssembly());
            ticketRepository.save(oldTicket);
        }
    }




    @Override
    public void deleteTicketById(Long id) {
        ticketRepository.deleteById(id);
    }

    public String generate40CharacterName() {
        StringBuilder keyBuilder = new StringBuilder();
        while (keyBuilder.length() < 40) {
            keyBuilder.append(java.util.UUID.randomUUID().toString().replace("-", ""));
        }
        return keyBuilder.substring(0, 40); // Ensure the key is exactly 40 characters
    }

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

public void generateQRCode(String data, String name) {
    try {
        // QR code size
        int size = 500;
// Generate the QR code matrix
BitMatrix bitMatrix = new QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, size, size);

// Define the output file path and format
String imageFormat = "png";
String outputFolder = Paths.get("src", "main", "resources", "QRcodes").toAbsolutePath().toString();
new File(outputFolder).mkdirs(); // Ensure the folder exists

String outputFilename = outputFolder + File.separator + name + "." + imageFormat;

// Write the QR code to the file
try (FileOutputStream fileOutputStream = new FileOutputStream(new File(outputFilename))) {
    MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, fileOutputStream);
}

System.out.println("QR code generated successfully: " + outputFilename);
} catch (Exception e) {
e.printStackTrace();
}
}


public void testQRCodeGeneration() {
    // Generate a unique name for the QR code
    String name = generateUnique40CharacterName();

    // QR code data
    String data = "This is a test QR code!";

    // Generate the QR code
    generateQRCode(data, name);

    System.out.println("Test QR code generated with name: " + name);
}

public static void main(String[] args) {
    // Start the Spring Boot application context
    ApplicationContext context = SpringApplication.run(Application.class, args);

    // Get the TicketService bean from the application context
    TicketServiceImpl ticketService = context.getBean(TicketServiceImpl.class);

    // Test QR code generation
    ticketService.testQRCodeGeneration();
}

}
