package com.myriamfournier.olympics_ticket_office.pojo;

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
@Table(name = "userselections")
@Getter
@Setter
public class userselections {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@NotNull
private Long userSelId;

private Integer nbPersons;
private Float amount;

@OneToOne
@JoinColumn(name= "eventID")
private events events;

@OneToOne
@JoinColumn(name= "offerID")
private offers offers;

public userselections(Integer nbPersons, Float amount, events events, offers offers) {
        // Default constructor
        this.nbPersons = nbPersons;
        this.amount = amount;
        this.events = events;
        this.offers = offers;

    }
}
