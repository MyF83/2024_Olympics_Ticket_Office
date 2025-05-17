package com.myriamfournier.olympics_ticket_office.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
// import jakarta.persistence.OneToOne;
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
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long site_id;


private String name;
private String description;
private String address;
private String city;
private String postalCode;  

@ManyToOne
@JoinColumn(name= "country_id", nullable = true)
private countries countries;

private Integer nbseatsc1;
private Integer nbseatsc2;
private Integer nbseatsc3;


   // Default constructor (required by Hibernate)
   public sites() {
}

public sites(String name, String description, String address, String city, String postalCode, countries countries, Integer nbseatsc1, Integer nbseatsc2, Integer nbseatsc3) {
    // Default constructor
this.name = name;
this.description = description;
this.address = address;         
this.city = city;
this.postalCode = postalCode;
this.countries = countries;
this.nbseatsc1 = nbseatsc1;
this.nbseatsc2 = nbseatsc2;
this.nbseatsc3 = nbseatsc3;

    }

}
