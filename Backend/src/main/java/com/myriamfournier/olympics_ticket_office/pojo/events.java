package com.myriamfournier.olympics_ticket_office.pojo;

import java.sql.Date;

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
@Table(name = "events")
@Getter
@Setter
public class events {


@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@NotNull
private Long event_id;

private String title;
private Date date;
private String image;
private String description;

@ManyToOne
@JoinColumn(name= "sport_id", nullable = true)
private sports sports;

@ManyToOne
@JoinColumn(name= "cerem_id", nullable = true)
private ceremonies ceremonies;

@ManyToOne
@JoinColumn(name = "challenger1_id", nullable = true) // First challenger
private challengers challenger1;

@ManyToOne
@JoinColumn(name = "challenger2_id", nullable = true) // Second challenger
private challengers challenger2;



private Float pricec1;
private Integer nbseatssoldc1;
private Integer nbseatsavailc1;
private Float pricec2;
private Integer nbseatssoldc2;
private Integer nbseatsavailc2;
private Float pricec3;
private Integer nbseatssoldc3;
private Integer nbseatsavailc3;

   // Default constructor (required by Hibernate)
   public events() {
}

// Parameterized constructor
public events(String title, Date date, String image, String description, sports sports, ceremonies ceremonies, challengers challenger1, challengers challenger2, Float pricec1, Integer nbseatssoldc1, Integer nbseatsavailc1, Float pricec2, Integer nbseatssoldc2, Integer nbseatsavailc2, Float pricec3, Integer nbseatssoldc3, Integer nbseatsavailc3) {  
            // Default constructor
        this.title = title;
        this.date = date;
        this.image = image;
        this.description = description;
        this.sports = sports;
        this.ceremonies = ceremonies;   
        this.challenger1 = challenger1;
        this.challenger2 = challenger2;
        this.pricec1 = pricec1;
        this.nbseatssoldc1 = nbseatssoldc1;
        this.nbseatsavailc1 = nbseatsavailc1;
        this.pricec2 = pricec2;
        this.nbseatssoldc2 = nbseatssoldc2;
        this.nbseatsavailc2 = nbseatsavailc2;
        this.pricec3 = pricec3;
        this.nbseatssoldc3 = nbseatssoldc3;
        this.nbseatsavailc3 = nbseatsavailc3;   
    }
}
