package com.myriamfournier.olympics_ticket_office.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "countries")
public class countries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long country_id;

    private String name;
    private String description;

// Default constructor (required by Hibernate)
public countries() {
}

public countries(String name, String description) {
    // Default constructor
    this.name = name;
    this.description = description;
}

public Long getCountry_id() {
    return country_id;
}

public void setCountry_id(Long country_id) {
    this.country_id = country_id;
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
}
