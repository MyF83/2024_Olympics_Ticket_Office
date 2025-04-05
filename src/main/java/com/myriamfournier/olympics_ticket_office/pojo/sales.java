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
@Table(name = "sales")
@Getter
@Setter
public class sales {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@NotNull
private Long saleId;

private Date date;

@OneToOne
@JoinColumn(name= "userID")
private users users;

@OneToOne
@JoinColumn(name= "cartID")
private carts carts;

@OneToOne
@JoinColumn(name= "salesKeyID")
private saleskeys saleskeys;


public sales(Date date, users users, carts carts, saleskeys saleskeys) {
        // Default constructor
        this.date = date;
        this.users = users;
        this.carts = carts;
        this.saleskeys = saleskeys;
    }
}
