package com.myriamfournier.olympics_ticket_office.pojo;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import com.myriamfournier.QRCode.QRCodeGenerator;

@Entity
@Table(name = "tickets")
public class tickets {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticket_id;

    private Timestamp date;

    @Column(length=512)
    private String keyAssembly;

    private String fileName;

    @Column(length=50, unique=true)
    private String ticketNumber;

    @Column(length=255)
    private String qrCodePath;

    @OneToOne
    @JoinColumn(name= "sale_id", nullable = true)
    private sales sales;

    @Transient
    @Autowired
    private QRCodeGenerator qrCodeGenerator;

    // Default constructor (required by Hibernate)
    public tickets() {
    }

    public tickets(Timestamp date, String keyAssembly, String fileName, sales sales, String ticketNumber, String qrCodePath) {
        this.date = date;
        this.keyAssembly = keyAssembly;
        this.fileName = fileName;
        this.sales = sales;
        this.ticketNumber = ticketNumber;
        this.qrCodePath = qrCodePath;
    }

    /**
     * Generates a ticket with a unique ticket number and QR code
     * 
     * @param sales The associated sale
     * @return The generated ticket
     */
    public static tickets createTicket(sales sales) {
        if (sales == null || sales.getCarts() == null || sales.getCarts().getUsers() == null) {
            throw new IllegalArgumentException("Sales must include cart and user information");
        }
        
        QRCodeGenerator generator = new QRCodeGenerator();
        
        // Get user first and last name
        String firstname = sales.getCarts().getUsers().getFirstname();
        String lastname = sales.getCarts().getUsers().getLastname();
        
        // Generate unique ticket number
        String ticketNumber = generator.generateTicketNumber(firstname, lastname);
        
        // Generate the QR code data from user key and sale key
        String userKeyValue = sales.getCarts().getUsers().getUserskeys().getKeysgenerations().getKeyGenerated();
        String saleKeyValue = sales.getSaleskeys().getKeysgenerations().getKeyGenerated();
        String keyAssembly = generator.getKeyAssembly(userKeyValue, saleKeyValue);
        
        // Generate unique filename for the QR code
        String uniqueFileName = generator.generateUnique40CharacterName();
        
        // Generate QR code and get the path
        String qrCodePath = generator.generateQRCode(keyAssembly, uniqueFileName);
        
        // Create and return the ticket
        tickets ticket = new tickets();
        ticket.setDate(new Timestamp(System.currentTimeMillis()));
        ticket.setKeyAssembly(keyAssembly);
        ticket.setFileName(uniqueFileName);
        ticket.setSales(sales);
        ticket.setTicketNumber(ticketNumber);
        ticket.setQrCodePath(qrCodePath);
        
        return ticket;
    }

    // Getters and Setters
    public Long getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Long ticket_id) {
        this.ticket_id = ticket_id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getKeyAssembly() {
        return keyAssembly;
    }

    public void setKeyAssembly(String keyAssembly) {
        this.keyAssembly = keyAssembly;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getQrCodePath() {
        return qrCodePath;
    }

    public void setQrCodePath(String qrCodePath) {
        this.qrCodePath = qrCodePath;
    }

    public sales getSales() {
        return sales;
    }

    public void setSales(sales sales) {
        this.sales = sales;
    }

    public QRCodeGenerator getQrCodeGenerator() {
        return qrCodeGenerator;
    }

    public void setQrCodeGenerator(QRCodeGenerator qrCodeGenerator) {
        this.qrCodeGenerator = qrCodeGenerator;
    }
}
