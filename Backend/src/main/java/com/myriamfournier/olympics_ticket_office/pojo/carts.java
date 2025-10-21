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
import jakarta.persistence.OneToOne;
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

@OneToOne
@JoinColumn(name= "usersel_id", nullable = true)
private userselections userselections;

private Float totalAmount;


   // Default constructor (required by Hibernate)
   public carts() {
}

public carts(Timestamp date, users users, userselections userselections, Float totalAmount) {
    this.date = date;       
    this.users = users;
    this.userselections = userselections;
    this.totalAmount = totalAmount;
    }

    // Users getter/setter provided by Lombok @Getter/@Setter

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

    public Long getCart_id() {
        return cart_id;
    }

    public void setCart_id(Long cart_id) {
        this.cart_id = cart_id;
    }

    public users getUsers() {
        return users;
    }

    public userselections getUserselections() {
        return userselections;
    }

    public void setUserselections(userselections userselections) {
        this.userselections = userselections;
    }

 }

