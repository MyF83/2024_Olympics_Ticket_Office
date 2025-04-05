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
@Table(name = "challengers")
@Getter
@Setter
public class challengers {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long challengerId;
    private String name;
    
public challengers(String name) {
        // Default constructor
        this.name = name;
    }

}
