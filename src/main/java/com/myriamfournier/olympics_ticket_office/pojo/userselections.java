package com.myriamfournier.olympics_ticket_office.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

public userselections(Integer nbPersons, Float amount) {
        // Default constructor
        this.nbPersons = nbPersons;
        this.amount = amount;

    }
}
