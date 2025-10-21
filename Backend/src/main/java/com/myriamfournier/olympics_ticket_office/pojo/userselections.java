package com.myriamfournier.olympics_ticket_office.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "userselections")
public class userselections {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long usersel_id;

private Integer nbPersons;
private Float amount;
private String seat_class; // Add the missing seat_class property

@ManyToOne
@JoinColumn(name= "event_id", nullable = true)
private events events;

@ManyToOne
@JoinColumn(name= "offer_id", nullable = true, columnDefinition = "BIGINT DEFAULT 1")
private offers offers;

// Default constructor (required by Hibernate)
public userselections() {
}

public userselections(Integer nbPersons, Float amount, String seat_class, events events, offers offers) {
    // Default constructor
    this.nbPersons = nbPersons;
    this.amount = amount;
    this.seat_class = seat_class;
    this.events = events;
    this.offers = offers;
}

public Long getUsersel_id() {
    return usersel_id;
}

public void setUsersel_id(Long usersel_id) {
    this.usersel_id = usersel_id;
}

public Integer getNbPersons() {
    return nbPersons;
}

public void setNbPersons(Integer nbPersons) {
    this.nbPersons = nbPersons;
}

public Float getAmount() {
    return amount;
}

public void setAmount(Float amount) {
    this.amount = amount;
}

public String getSeat_class() {
    return seat_class;
}

public void setSeat_class(String seat_class) {
    this.seat_class = seat_class;
}

public events getEvents() {
    return events;
}

public void setEvents(events events) {
    this.events = events;
}

public offers getOffers() {
    return offers;
}

public void setOffers(offers offers) {
    this.offers = offers;
}
}
