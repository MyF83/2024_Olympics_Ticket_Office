package com.myriamfournier.olympics_ticket_office.pojo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "carts")
@Getter
@Setter
public class carts {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long cart_id;

private Date date;

@ManyToOne
@JoinColumn(name= "user_id", nullable = true)
private users users;


// @ManyToOne
// @JoinColumn(name= "usersel_id", nullable = true)
// private userselections userselections;

private Float totalAmount;


   // Default constructor (required by Hibernate)
   public carts() {
}

public carts(Date date, users users, /*userselections userselections,*/ Float totalAmount) {
    // Default constructor
    this.date = date;       
    this.users = users;
    // this.userselections = userselections;
    this.totalAmount = totalAmount;


    }

      public users getUsers() {
        return users;
    }
}
