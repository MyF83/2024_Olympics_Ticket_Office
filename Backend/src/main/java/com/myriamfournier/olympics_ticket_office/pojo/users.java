package com.myriamfournier.olympics_ticket_office.pojo;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class users {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long user_id;

private String firstname;
private String lastname;
private String username;
private String email;
private String password;
private String phoneNumber;
private String address;
private Timestamp creationDate;


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

private Timestamp policySignDate;
private String city;
private String postalCode;

@ManyToOne
@JoinColumn(name= "country_id", nullable = true)
private countries countries;

@OneToMany(mappedBy = "users") 
@JsonIgnore
private List<carts> carts;

/* 
  public static String getUsername(String lastname, String firstname) {
        String userName =  lastname + "-" +  firstname; // Assuming username is a combination of first and last name
        return userName;
    }*/


   // Default constructor (required by Hibernate)
   public users() {
}


    public users(String firstname, String lastname, String username, String email, String password, String phoneNumber, Date creationDate, roles roles, userskeys userskeys, userselections userselections, policies policies, Date policySignDate, String address, String city, String postalCode, countries countries) {  
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.creationDate = new Timestamp(System.currentTimeMillis());
        this.roles = roles;
        this.userskeys = userskeys;
        this.userselections = userselections;
        this.policies = policies;
        this.policySignDate = new Timestamp(System.currentTimeMillis());
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

    public userskeys getUserskeys() {
        return userskeys;

    }

    @PrePersist
    protected void onPrePersist() {
        this.creationDate = Timestamp.valueOf(LocalDateTime.now());
        this.policySignDate = Timestamp.valueOf(LocalDateTime.now());
    }


    public List<carts> getCarts() {
        return carts;
    }


}
