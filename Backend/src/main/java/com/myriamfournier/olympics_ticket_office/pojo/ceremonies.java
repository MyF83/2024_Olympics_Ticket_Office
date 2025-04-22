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
@Table(name = "ceremonies")
@Getter
@Setter
public class ceremonies {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@NotNull
private Long cerem_id;  

private String name;
private String description;

@ManyToOne
@JoinColumn(name= "site_id", nullable = true)
private sites sites;


public ceremonies(String name, String description, sites sites) {
        // Default constructor
    this.name = name;       
    this.description = description;
    this.sites = sites;
    }

}
