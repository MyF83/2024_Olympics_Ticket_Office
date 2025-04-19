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

@Entity
@Table(name = "users")
@Getter
@Setter
public class users {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@NotNull
private Long userId;

private String firstname;
private String lastname;
private String username;
private String email;
private String password;
private String phoneNumber;
private String address;
private Date creationDate;


@OneToOne
@JoinColumn(name= "roleID")
private roles roles;


@OneToOne
@JoinColumn(name= "userKeyID")
private userskeys userskeys;


@OneToOne
@JoinColumn(name= "userSelID")
private userselections userselections;

@OneToOne
@JoinColumn(name= "policyID")
private policies policies;

private Date policiesSignDate;
private String city;
private String postalCode;

@OneToOne
@JoinColumn(name= "countryID")
private countries countries;


public users(String firstname, String lastname, String username, String email, String password, String phoneNumber, Date creationDate, roles roles, userskeys userskeys, userselections userselections, policies policies, Date policiesSignDate, String address, String city, String postalCode, countries countries) {  
        // Default constructor
    this.firstname = firstname;
    this.lastname = lastname;
    // this.username = username;
    this.username = firstname + "-" + lastname; // Assuming username is a combination of first and last name
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
    // this.creationDate = creationDate;
    this.creationDate = new Date(System.currentTimeMillis());
    this.roles = roles;
    this.userskeys = userskeys;
    this.userselections = userselections;
    this.policies = policies;
    this.policiesSignDate = new Date(System.currentTimeMillis());
    // this.policiesSignDate = policiesSignDate;
    this.address = address;
    this.city = city;
    this.postalCode = postalCode;
    this.countries = countries;

    }



}
