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
@Table(name = "countries")
@Getter
@Setter
public class countries {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long country_id;

    private String name;
    private String description;
    
public countries(String name, String description) {
        // Default constructor
        this.name = name;
        this.description = description;
    }




}
