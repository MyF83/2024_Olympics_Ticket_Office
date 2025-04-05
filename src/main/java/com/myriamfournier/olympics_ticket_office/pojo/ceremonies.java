package com.myriamfournier.olympics_ticket_office.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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

@OneToOne
@JoinColumn(name= "siteID")
private sites sites;


public ceremonies(String name, String description, sites sites) {
        // Default constructor
    this.name = name;       
    this.description = description;
    this.sites = sites;
    }

}
