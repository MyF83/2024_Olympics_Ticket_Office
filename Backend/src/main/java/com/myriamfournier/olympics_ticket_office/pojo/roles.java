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
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long role_id;

private String name;
private String description;


   // Default constructor (required by Hibernate)
   public roles() {
}


public roles(String name, String description) {
        // Default constructor
        this.name = name;
        this.description = description;     
    }


}
