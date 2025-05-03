package com.myriamfournier.olympics_ticket_office.pojo;

import java.sql.Date;

import jakarta.persistence.Column;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
//import java.io.FileOutputStream;
//import java.io.File;
//import java.nio.file.Paths;
//import com.google.zxing.client.j2se.MatrixToImageWriter;

//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.qrcode.QRCodeWriter;
//import com.myriamfournier.olympics_ticket_office.Application;
//import com.myriamfournier.olympics_ticket_office.repository.TicketRepository;
//import org.springframework.boot.SpringApplication;
//import org.springframework.context.ApplicationContext;

// import com.myriamfournier.olympics_ticket_office.pojo.userskeys;
// import com.myriamfournier.olympics_ticket_office.pojo.saleskeys;

@Entity
@Table(name = "tickets")
@Getter
@Setter
public class tickets {

    //@Autowired
   // private static TicketRepository ticketRepository; // Inject the repository to query the database
   
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@NotNull
private Long ticket_id;

private Date date;

@Column(length=512)
private String keyAssembly;

private String fileName;


@OneToOne
@JoinColumn(name= "userkey_id", nullable = true)
private  userskeys userskeys;

@OneToOne
@JoinColumn(name= "salekey_id", nullable = true)
private  saleskeys saleskeys;

@OneToOne
@JoinColumn(name= "sale_id", nullable = true)
private  sales sales;

@OneToOne
@JoinColumn(name= "cart_id", nullable = true)
private  carts carts;

@OneToOne
@JoinColumn(name= "user_id", nullable = true)
private  users users;

@OneToOne
@JoinColumn(name= "usersel_id", nullable = true)
private  userselections userselections;



   // Default constructor (required by Hibernate)
   public tickets() {
}

public tickets(Date date, String keyAssembly, String fileName, userskeys userskeys, saleskeys saleskeys, sales sales, carts carts, users users, userselections userselections) {
    // Default constructor
    this.date = date;
    this.keyAssembly = keyAssembly;
    this.fileName = fileName;
    this.userskeys = userskeys;
    this.saleskeys = saleskeys;
    this.sales = sales;
    this.carts = carts;
    this.users = users;
    this.userselections = userselections;
    }

    
 
    public userskeys getUserskeys() {
        return userskeys;
    }

    public saleskeys getSaleskeys() {
        return saleskeys;
    }

    
/* 
    public void setKeyAssembly(String keyAssembly2) {

        throw new UnsupportedOperationException("Unimplemented method 'setKeyAssembly'");
    }*/

    public Date getDate() {

        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


/* Error : double call to method getuserskeys/getUserskeys
    public Object getuserskeys() {

        throw new UnsupportedOperationException("Unimplemented method 'getuserskeys'");
    

    public Sales getSales() {

        return sales;
    }

    public void setSales(sales sales) {
        this.sales = sales;
    }}*/

   


/* 
    public String getKeyAssembly() {
      
        throw new UnsupportedOperationException("Unimplemented method 'getKeyAssembly'");
    }*/

    
    /* 
    private String generate40CharacterName() {
            StringBuilder keyBuilder = new StringBuilder();
            while (keyBuilder.length() < 40) {
                keyBuilder.append(java.util.UUID.randomUUID().toString().replace("-", ""));
            }
            return keyBuilder.substring(0, 40); // Ensure the key is exactly 40 characters
        }
    
    private String generateUnique40CharacterName() {
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

        public static void main(String[] args) {

        // Start the Spring Boot application context
            ApplicationContext context = SpringApplication.run(Application.class, args);

            // Get the tickets bean from the application context
            tickets ticketInstance = context.getBean(tickets.class);

            // Call the generateUnique40CharacterName() method on the instance
            String name = ticketInstance.generateUnique40CharacterName();


            // Create an instance of the tickets class
           // OLD : tickets ticketInstance = new tickets(date, keyAssembly, fileName, userskeys, saleskeys, sales, carts, users, userselections);
        
            // Call the generate40CharacterName() method on the instance
            // String name = ticketInstance.generate40CharacterName();
            // OLD : String name = ticketInstance.generateUnique40CharacterName(); // Generate a unique 40-character name
            // System.out.println("Generated file name: " + name);
        
            // QR code data
            String data = "Contente, ça marche !";
            // System.out.println("QR code data: " + data);
        
            // QR code size
            int size = 500;
        
            // Generate the QR code matrix
            BitMatrix bitMatrix = generateMatrix(data, size);
            if (bitMatrix == null) {
                System.out.println("QR code generation failed.");
                return;
            }
            // System.out.println("QR code generated successfully.");
        
            // Define the output file path and format
            String imageFormat = "png";

                    // OLD : new File("c:/QRcode/").mkdirs();
                    // OLD : String outputFileName = "c:/QRcode/" + name + "." + imageFormat;
            // Resolve the QRcodes folder in the resources directory
            String outputFolder = Paths.get("src", "main", "resources", "QRcodes").toAbsolutePath().toString();
            new File(outputFolder).mkdirs(); // Ensure the folder exists
            // System.out.println("Output file name: " + outputFileName);
        
            String outputFileName = outputFolder + File.separator + name + "." + imageFormat;
            // System.out.println("Output file name: " + outputFileName);
            // Save the complete output filename into the fileName variable of the class :
            // OLD tickets.fileName = outputFileName;
            ticketInstance.setFileName(outputFileName);
            // System.out.println("Output file name: " + tickets.fileName);
            
            // Write the QR code to the file
            // System.out.println("Writing image to file...");
            writeImage(outputFileName, imageFormat, bitMatrix);
            // System.out.println("Image written successfully.");
        }

    private static void writeImage(String outputFileName, String imageFormat, BitMatrix bitMatrix) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(outputFileName))) {
            MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
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
*/
    

}
