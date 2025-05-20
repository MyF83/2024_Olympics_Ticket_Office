package com.myriamfournier.olympics_ticket_office.pojo;


import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

private Timestamp date;

@ManyToOne
@JoinColumn(name= "user_id", nullable = true)
private users users;

@OneToMany
private List<offers> offers;




// @ManyToOne
// @JoinColumn(name= "usersel_id", nullable = true)
// private userselections userselections;

private Float totalAmount;


   // Default constructor (required by Hibernate)
   public carts() {
}

public carts(Timestamp date, users users, offers offers, /*userselections userselections,*/ Float totalAmount) {
    // Default constructor
    this.date = date;       
    this.users = users;
    this.offers = (List<com.myriamfournier.olympics_ticket_office.pojo.offers>) offers;
    // this.userselections = userselections;
    this.totalAmount = totalAmount;


    }

      public users getUsers() {
        return users;
    }

      public void setOffers(List<offers> offers) {
        this.offers = offers;
    }

      public List<offers> getOffers() {
        return offers;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = (float) totalAmount;
    }

 public void findByUser(users users) {
        this.users = users;
    }

    public void setUser(users users) {
        this.users = users;
    }


    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp getDate() {
        return date;
    }

 }

