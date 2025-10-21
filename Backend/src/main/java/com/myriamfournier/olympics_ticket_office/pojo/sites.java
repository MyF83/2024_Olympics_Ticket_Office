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

@Entity
@Table(name = "sites")
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

private String planUrl;

// Default constructor (required by Hibernate)
public sites() {
}

public sites(String name, String description, String address, String city, String postalCode, countries countries, Integer nbseatsc1, Integer nbseatsc2, Integer nbseatsc3, String planUrl) {
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
    this.planUrl = planUrl;
}

public Long getSite_id() {
    return site_id;
}

public void setSite_id(Long site_id) {
    this.site_id = site_id;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getDescription() {
    return description;
}

public void setDescription(String description) {
    this.description = description;
}

public String getAddress() {
    return address;
}

public void setAddress(String address) {
    this.address = address;
}

public String getCity() {
    return city;
}

public void setCity(String city) {
    this.city = city;
}

public String getPostalCode() {
    return postalCode;
}

public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
}

public countries getCountries() {
    return countries;
}

public void setCountries(countries countries) {
    this.countries = countries;
}

public Integer getNbseatsc1() {
    return nbseatsc1;
}

public void setNbseatsc1(Integer nbseatsc1) {
    this.nbseatsc1 = nbseatsc1;
}

public Integer getNbseatsc2() {
    return nbseatsc2;
}

public void setNbseatsc2(Integer nbseatsc2) {
    this.nbseatsc2 = nbseatsc2;
}

public Integer getNbseatsc3() {
    return nbseatsc3;
}

public void setNbseatsc3(Integer nbseatsc3) {
    this.nbseatsc3 = nbseatsc3;
}

public String getPlanUrl() {
    return planUrl;
}

public void setPlanUrl(String planUrl) {
    this.planUrl = planUrl;
}
}
