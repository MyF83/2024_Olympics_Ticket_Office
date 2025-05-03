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
@Table(name = "sports")
@Getter
@Setter
public class sports {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@NotNull
private Long sport_id;

private String name;
private String description;
private Boolean isParalymp;

@ManyToOne
@JoinColumn(name= "site_id", nullable = true)
private sites sites;

   // Default constructor (required by Hibernate)
   public sports() {
}


public sports(String name, String description, Boolean isParalymp, sites sites) {
    // Default constructor
    this.name = name;       
    this.description = description;
    this.isParalymp = isParalymp;
    this.sites = sites;

   

    }

}
