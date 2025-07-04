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
@Table(name = "roles")
@Getter
@Setter
public class roles {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@NotNull
private Long roleId;

private String name;
private String description;

public roles(String name, String description) {
        // Default constructor
        this.name = name;
        this.description = description;     
    }


}
