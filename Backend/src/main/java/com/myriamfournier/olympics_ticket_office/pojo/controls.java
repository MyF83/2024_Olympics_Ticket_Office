package com.myriamfournier.olympics_ticket_office.pojo;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.JoinTable;
// import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "controls")
public class controls {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long control_id;

private Timestamp date;

@Column(length=512)
private String scancode;

private Boolean isTicketValid;

@ManyToOne
// @JoinTable
@JoinColumn(name= "ticket_id", nullable = true)
private tickets tickets;

/* 
@ManyToOne
// @JoinTable
@JoinColumn(name= "userkey_id", nullable = true)
private userskeys userskeys;

@ManyToOne
// @JoinTable
@JoinColumn(name= "salekey_id", nullable = true)
private saleskeys saleskeys;*/

   // Default constructor (required by Hibernate)
   public controls() {
}

public controls(Timestamp date, String scancode, Boolean isTicketValid, tickets tickets/* , userskeys userskeys, saleskeys saleskeys*/) {
    // Default constructor
    this.date = date;
    this.scancode = scancode;
    this.isTicketValid = isTicketValid;
    this.tickets = tickets;
    // this.userskeys = userskeys;
    // this.saleskeys = saleskeys;
}

public Long getControl_id() {
    return control_id;
}

public void setControl_id(Long control_id) {
    this.control_id = control_id;
}

public Timestamp getDate() {
    return date;
}

public void setDate(Timestamp date) {
    this.date = date;
}

public String getScancode() {
    return scancode;
}

public void setScancode(String scancode) {
    this.scancode = scancode;
}

public Boolean getIsTicketValid() {
    return isTicketValid;
}

public void setIsTicketValid(Boolean isTicketValid) {
    this.isTicketValid = isTicketValid;
}

public tickets getTickets() {
    return tickets;
}

public void setTickets(tickets tickets) {
    this.tickets = tickets;
}
}
