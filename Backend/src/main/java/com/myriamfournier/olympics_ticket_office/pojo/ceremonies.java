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

@Entity
@Table(name = "ceremonies")
public class ceremonies {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long cerem_id;  

private String name;
private String description;

@ManyToOne
@JoinColumn(name= "site_id", nullable = true)
private sites sites;

   // Default constructor (required by Hibernate)
   public ceremonies() {
}

public ceremonies(String name, String description, sites sites) {
        // Default constructor
    this.name = name;       
    this.description = description;
    this.sites = sites;
}

public Long getCerem_id() {
    return cerem_id;
}

public void setCerem_id(Long cerem_id) {
    this.cerem_id = cerem_id;
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

public sites getSites() {
    return sites;
}

public void setSites(sites sites) {
    this.sites = sites;
}

public Long getCeremony_id() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getCeremony_id'");
}
}
