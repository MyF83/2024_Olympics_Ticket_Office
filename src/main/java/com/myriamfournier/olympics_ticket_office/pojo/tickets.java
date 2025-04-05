package com.myriamfournier.olympics_ticket_office.pojo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


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
}
