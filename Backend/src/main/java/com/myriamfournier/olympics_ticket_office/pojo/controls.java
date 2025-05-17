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
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long control_id;

private Date date;

@Column(length=512)
private String scancode;

private Boolean isTicketValid;

public Boolean getIsTicketValid() {
    return isTicketValid;
}

public void setIsTicketValid(Boolean isTicketValid) {
    this.isTicketValid = isTicketValid;
}

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

public controls(Date date, String scancode, Boolean isTicketValid, tickets tickets/* , userskeys userskeys, saleskeys saleskeys*/) {
    // Default constructor
    this.date = date;
    this.scancode = scancode;
    this.isTicketValid = isTicketValid;
    this.tickets = tickets;
    // this.userskeys = userskeys;
    // this.saleskeys = saleskeys;

    }

    public tickets getTickets() {
        return tickets;
    }
}
