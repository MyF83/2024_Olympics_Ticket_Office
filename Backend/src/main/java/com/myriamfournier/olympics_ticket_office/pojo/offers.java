package com.myriamfournier.olympics_ticket_office.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "offers")
public class offers {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long offer_id;

private String title;
private String description;
private String image;
private Integer rate;
private Integer nbSpectators;

// Default constructor (required by Hibernate)
public offers() {
}

public offers(String title, String description, String image, Integer rate, Integer nbSpectators) {  
    // Default constructor
    this.title = title;
    this.description = description;
    this.image = image;
    this.rate = rate;
    this.nbSpectators = nbSpectators;
}

public Long getOffer_id() {
    return offer_id;
}

public void setOffer_id(Long offer_id) {
    this.offer_id = offer_id;
}

public String getTitle() {
    return title;
}

public void setTitle(String title) {
    this.title = title;
}

public String getDescription() {
    return description;
}

public void setDescription(String description) {
    this.description = description;
}

public String getImage() {
    return image;
}

public void setImage(String image) {
    this.image = image;
}

public Integer getRate() {
    return rate;
}

public void setRate(Integer rate) {
    this.rate = rate;
}

public Integer getNbSpectators() {
    return nbSpectators;
}

public void setNbSpectators(Integer nbSpectators) {
    this.nbSpectators = nbSpectators;
}
}
