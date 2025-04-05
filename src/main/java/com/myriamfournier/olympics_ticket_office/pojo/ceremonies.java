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
@Table(name = "ceremonies")
@Getter
@Setter
public class ceremonies {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@NotNull
private Long ceremId;  

private String name;
private String description;

public ceremonies(String name, String description) {
        // Default constructor
    this.name = name;       
    this.description = description;
    }

}
