package com.myriamfournier.olympics_ticket_office.pojo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "events")
public class events {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long event_id;

private String title;
private Date date;
private String time;
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
public events(String title, Date date, String time, String image, String description, sports sports, ceremonies ceremonies, challengers challenger1, challengers challenger2, Float pricec1, Integer nbseatssoldc1, Integer nbseatsavailc1, Float pricec2, Integer nbseatssoldc2, Integer nbseatsavailc2, Float pricec3, Integer nbseatssoldc3, Integer nbseatsavailc3) {  
        this.title = title;
        this.date = date;
        this.time = time;
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

public Long getEvent_id() {
    return event_id;
}

public void setEvent_id(Long event_id) {
    this.event_id = event_id;
}

public String getTitle() {
    return title;
}

public void setTitle(String title) {
    this.title = title;
}

public Date getDate() {
    return date;
}

public void setDate(Date date) {
    this.date = date;
}

public String getTime() {
    return time;
}

public void setTime(String time) {
    this.time = time;
}

public String getImage() {
    return image;
}

public void setImage(String image) {
    this.image = image;
}

public String getDescription() {
    return description;
}

public void setDescription(String description) {
    this.description = description;
}

public sports getSports() {
    return sports;
}

public void setSports(sports sports) {
    this.sports = sports;
}

public ceremonies getCeremonies() {
    return ceremonies;
}

public void setCeremonies(ceremonies ceremonies) {
    this.ceremonies = ceremonies;
}

public challengers getChallenger1() {
    return challenger1;
}

public void setChallenger1(challengers challenger1) {
    this.challenger1 = challenger1;
}

public challengers getChallenger2() {
    return challenger2;
}

public void setChallenger2(challengers challenger2) {
    this.challenger2 = challenger2;
}

public Float getPricec1() {
    return pricec1;
}

public void setPricec1(Float pricec1) {
    this.pricec1 = pricec1;
}

public Integer getNbseatssoldc1() {
    return nbseatssoldc1;
}

public void setNbseatssoldc1(Integer nbseatssoldc1) {
    this.nbseatssoldc1 = nbseatssoldc1;
}

public Integer getNbseatsavailc1() {
    return nbseatsavailc1;
}

public void setNbseatsavailc1(Integer nbseatsavailc1) {
    this.nbseatsavailc1 = nbseatsavailc1;
}

public Float getPricec2() {
    return pricec2;
}

public void setPricec2(Float pricec2) {
    this.pricec2 = pricec2;
}

public Integer getNbseatssoldc2() {
    return nbseatssoldc2;
}

public void setNbseatssoldc2(Integer nbseatssoldc2) {
    this.nbseatssoldc2 = nbseatssoldc2;
}

public Integer getNbseatsavailc2() {
    return nbseatsavailc2;
}

public void setNbseatsavailc2(Integer nbseatsavailc2) {
    this.nbseatsavailc2 = nbseatsavailc2;
}

public Float getPricec3() {
    return pricec3;
}

public void setPricec3(Float pricec3) {
    this.pricec3 = pricec3;
}

public Integer getNbseatssoldc3() {
    return nbseatssoldc3;
}

public void setNbseatssoldc3(Integer nbseatssoldc3) {
    this.nbseatssoldc3 = nbseatssoldc3;
}

public Integer getNbseatsavailc3() {
    return nbseatsavailc3;
}

public void setNbseatsavailc3(Integer nbseatsavailc3) {
    this.nbseatsavailc3 = nbseatsavailc3;
}
}
