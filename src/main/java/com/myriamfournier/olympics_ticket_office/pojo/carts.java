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
@Table(name = "carts")
@Getter
@Setter
public class carts {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@NotNull
private Long cartId;

private Date date;

@OneToOne
@JoinColumn(name= "userID")
private users users;


@OneToOne
@JoinColumn(name= "userSelID")
private userselections userselections;

private Float totalAmount;

public carts(Date date, users users, userselections userselections, Float totalAmount) {
    // Default constructor
    this.date = date;       
    this.users = users;
    this.userselections = userselections;
    this.totalAmount = totalAmount;


    }
}
