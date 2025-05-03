package com.myriamfournier.olympics_ticket_office.pojo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "userselections")
@Getter
@Setter
public class userselections {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@NotNull
private Long usersel_id;

private Integer nbPersons;
private Float amount;

@ManyToOne
@JoinColumn(name= "event_id", nullable = true)
private events events;

@ManyToOne
@JoinColumn(name= "offer_id", nullable = true, columnDefinition = "BIGINT DEFAULT 1")
private offers offers;



   // Default constructor (required by Hibernate)
   public userselections() {
}

public userselections(Integer nbPersons, Float amount, events events, offers offers) {
        // Default constructor
        this.nbPersons = nbPersons;
        this.amount = amount;
        this.events = events;
        this.offers = offers;

    }
}
