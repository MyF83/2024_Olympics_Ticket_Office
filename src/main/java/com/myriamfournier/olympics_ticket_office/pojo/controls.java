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
@Table(name = "controls")
@Getter
@Setter
public class controls {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@NotNull
private Long controlId;

private Date date;
private String scanCode;
private Boolean isTicketValid;

@OneToOne
@JoinColumn(name= "ticketID")
private tickets tickets;

@OneToOne
@JoinColumn(name= "userKeyID")
private userskeys userskeys;

@OneToOne
@JoinColumn(name= "saleKeyID")
private saleskeys saleskeys;



public controls(Date date, String scanCode, Boolean isTicketValid, tickets tickets, userskeys userskeys, saleskeys saleskeys) {
    // Default constructor
    this.date = date;
    this.scanCode = scanCode;
    this.isTicketValid = isTicketValid;
    this.tickets = tickets;
    this.userskeys = userskeys;
    this.saleskeys = saleskeys;

    }
}
