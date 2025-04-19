package com.myriamfournier.olympics_ticket_office.pojo;

import java.sql.Date;

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
import java.io.FileOutputStream;
import java.io.File;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;


@Entity
@Table(name = "tickets")
@Getter
@Setter
public class tickets {

   
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@NotNull
private Long ticketId;

private Date date;

private String keyAssembly;

@OneToOne
@JoinColumn(name= "userKeyID")
private userskeys userskeys;

@OneToOne
@JoinColumn(name= "saleKeyID")
private saleskeys saleskeys;

@OneToOne
@JoinColumn(name= "saleID")
private sales sales;

@OneToOne
@JoinColumn(name= "cartID")
private carts carts;

@OneToOne
@JoinColumn(name= "userID")
private users users;

@OneToOne
@JoinColumn(name= "userSelID")
private userselections userselections;

public tickets(Date date, String keyAssembly, userskeys userskeys, saleskeys saleskeys, sales sales, carts carts, users users, userselections userselections) {
    // Default constructor
    this.date = date;
    this.keyAssembly = keyAssembly;
    this.userskeys = userskeys;
    this.saleskeys = saleskeys;
    this.sales = sales;
    this.carts = carts;
    this.users = users;
    this.userselections = userselections;
    }


    public static void main(String[] args) {
        String data = "J'aime les Jeux Olympiques d'été !";
        int size = 500;

        // encode
        BitMatrix bitMatrix = generateMatrix(data, size);

        String imageFormat = "png";
        new File("c:/QRcode/").mkdirs();
        String outputFileName = "c:/QRcode/qrcode_test." + imageFormat;

        // write in a file
        writeImage(outputFileName, imageFormat, bitMatrix);
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
}
