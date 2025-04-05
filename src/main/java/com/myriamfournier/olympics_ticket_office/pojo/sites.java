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
@Table(name = "sites")
@Getter
@Setter
public class sites {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@NotNull
private Long siteId;


private String name;
private String description;
private String address;
private String city;    
private Integer nbSeatsC1;
private Integer nbSeatsC2;
private Integer nbSeatsC3;


public sites(String name, String description, String address, String city, Integer nbSeatsC1, Integer nbSeatsC2, Integer nbSeatsC3) {
    // Default constructor
this.name = name;
this.description = description;
this.address = address;         
this.city = city;
this.nbSeatsC1 = nbSeatsC1;
this.nbSeatsC2 = nbSeatsC2;
this.nbSeatsC3 = nbSeatsC3;

    }

}
