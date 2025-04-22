package com.myriamfournier.olympics_ticket_office.pojo;

import java.sql.Date;

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
private Long control_id;

private Date date;

@Column(length=512)
private String scan_code;

private Boolean isTicketValid;

@ManyToOne
// @JoinTable
@JoinColumn(name= "ticket_id", nullable = true)
private tickets tickets;

@ManyToOne
// @JoinTable
@JoinColumn(name= "user_key_id", nullable = true)
private userskeys userskeys;

@ManyToOne
// @JoinTable
@JoinColumn(name= "sale_key_id", nullable = true)
private saleskeys saleskeys;



public controls(Date date, String scan_code, Boolean isTicketValid, tickets tickets, userskeys userskeys, saleskeys saleskeys) {
    // Default constructor
    this.date = date;
    this.scan_code = scan_code;
    this.isTicketValid = isTicketValid;
    this.tickets = tickets;
    this.userskeys = userskeys;
    this.saleskeys = saleskeys;

    }
}
