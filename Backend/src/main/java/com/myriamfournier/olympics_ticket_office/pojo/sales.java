package com.myriamfournier.olympics_ticket_office.pojo;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "sales")
public class sales {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long sale_id;

private Timestamp date;

/* 
@ManyToOne
@JoinColumn(name= "user_id", nullable = true)
private users users;*/

@OneToOne
@JoinColumn(name= "cart_id", nullable = true)
private carts carts;

@OneToOne
@JoinColumn(name= "salekey_id", nullable = true)
private saleskeys saleskeys;

// Default constructor (required by Hibernate)
public sales() {
}

public sales(Timestamp date, /*users users,*/ carts carts, saleskeys saleskeys) {
    // Default constructor
    this.date = date;
    // this.users = users;
    this.carts = carts;
    this.saleskeys = saleskeys;
}

public Long getSale_id() {
    return sale_id;
}

public void setSale_id(Long sale_id) {
    this.sale_id = sale_id;
}

public Timestamp getDate() {
    return date;
}

public void setDate(Timestamp date) {
    this.date = date;
}

public carts getCarts() {
    return carts;
}

public void setCarts(carts carts) {
    this.carts = carts;
}

public saleskeys getSaleskeys() {
    return saleskeys;
}

public void setSaleskeys(saleskeys saleskeys) {
    this.saleskeys = saleskeys;
}
}
