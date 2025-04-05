package com.myriamfournier.olympics_ticket_office.pojo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "events")
@Getter
@Setter
public class events {


@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@NotNull
private Long eventId;

private Date date;
private String desciption;
private Float priceC1;
private Integer nbSeatsSoldC1;
private Integer nbSeatsAvailC1;
private Float priceC2;
private Integer nbSeatsSoldC2;
private Integer nbSeatsAvailC2;
private Float priceC3;
private Integer nbSeatsSoldC3;
private Integer nbSeatsAvailC3;


public events(Date date, String desciption, Float priceC1, Integer nbSeatsSoldC1, Integer nbSeatsAvailC1, Float priceC2, Integer nbSeatsSoldC2, Integer nbSeatsAvailC2, Float priceC3, Integer nbSeatsSoldC3, Integer nbSeatsAvailC3) {  
            // Default constructor
        this.date = date;
        this.desciption = desciption;
        this.priceC1 = priceC1;
        this.nbSeatsSoldC1 = nbSeatsSoldC1;
        this.nbSeatsAvailC1 = nbSeatsAvailC1;
        this.priceC2 = priceC2;
        this.nbSeatsSoldC2 = nbSeatsSoldC2;
        this.nbSeatsAvailC2 = nbSeatsAvailC2;
        this.priceC3 = priceC3;
        this.nbSeatsSoldC3 = nbSeatsSoldC3;
        this.nbSeatsAvailC3 = nbSeatsAvailC3;   
    }
}
