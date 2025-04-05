package com.myriamfournier.olympics_ticket_office.pojo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class users {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@NotNull
private Long userId;

private String firstName;
private String lastName;
private String email;
private String password;
private String phoneNumber;
private String address;
private String city;
private String postalCode;
private Date creationDate;
private Date policiesSignDate;




public users(String firstName, String lastName, String email, String password, String phoneNumber, String address, String city, String country, String postalCode, Date creationDate, Date policiesSignDate) {  
        // Default constructor
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.city = city;
    this.postalCode = postalCode;
    this.creationDate = new Date(System.currentTimeMillis());
    this.policiesSignDate = new Date(System.currentTimeMillis());
    this.creationDate = creationDate;
    this.policiesSignDate = policiesSignDate;

    }



}
