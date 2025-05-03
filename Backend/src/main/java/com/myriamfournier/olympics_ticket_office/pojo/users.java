package com.myriamfournier.olympics_ticket_office.pojo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
private Long user_id;

private String firstname;
private String lastname;
private String username;
private String email;
private String password;
private String phonenumber;
private String address;
private Date creationDate;


@ManyToOne
@JoinColumn(name= "role_id", nullable = true)
private roles roles;


@OneToOne
@JoinColumn(name= "userkey_id", nullable = true)
private userskeys userskeys;


@ManyToOne
@JoinColumn(name= "usersel_id", nullable = true)
private userselections userselections;


@ManyToOne
@JoinColumn(name= "policy_id", nullable = true)
private policies policies;

private Date policySignDate;
private String city;
private String postalCode;

@ManyToOne
@JoinColumn(name= "country_id", nullable = true)
private countries countries;

/* 
  public static String getUsername(String lastname, String firstname) {
        String userName =  lastname + "-" +  firstname; // Assuming username is a combination of first and last name
        return userName;
    }*/


       // Default constructor (required by Hibernate)
   public users() {
}


public users(String firstname, String lastname, String username, String email, String password, String phonenumber, Date creationDate, roles roles, userskeys userskeys, userselections userselections, policies policies, Date policySignDate, String address, String city, String postalCode, countries countries) {  
        // Default constructor
    this.firstname = firstname;
    this.lastname = lastname;
    this.username = username;
    // this.username = username;
    // this.username = getUsername(lastname, firstname); // Assuming username is a combination of first and last name
    // this.username = lastname  + "-" + firstname; // Assuming username is a combination of first and last name
    this.email = email;
    this.password = password;
    this.phonenumber = phonenumber;
    // this.creationDate = creationDate;
    this.creationDate = new Date(System.currentTimeMillis());
    this.roles = roles;
    this.userskeys = userskeys;
    this.userselections = userselections;
    this.policies = policies;
    this.policySignDate = new Date(System.currentTimeMillis());
    // this.policySignDate = policySignDate;
    this.address = address;
    this.city = city;
    this.postalCode = postalCode;
    this.countries = countries;

    }
/*
public users(String string, String string2, String string3, String string4, String string5, String string6,
        String string7, Object object, Object object2, Object object3, Object object4, Object object5, Object object6,
        Object object7, Object object8) {
    
}*/



}
