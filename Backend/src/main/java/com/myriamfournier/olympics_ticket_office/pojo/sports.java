package com.myriamfournier.olympics_ticket_office.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
// import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sports")
public class sports {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long sport_id;

private String name;
private String description;
private Boolean isParalympic;

@ManyToOne
@JoinColumn(name= "site_id", nullable = true)
private sites sites;

// Default constructor (required by Hibernate)
public sports() {
}

public sports(String name, String description, Boolean isParalympic, sites sites) {
    // Default constructor
    this.name = name;       
    this.description = description;
    this.isParalympic = isParalympic;
    this.sites = sites;
}

public Long getSport_id() {
    return sport_id;
}

public void setSport_id(Long sport_id) {
    this.sport_id = sport_id;
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

public Boolean getIsParalympic() {
    return isParalympic;
}

public void setIsParalympic(Boolean isParalympic) {
    this.isParalympic = isParalympic;
}

public sites getSites() {
    return sites;
}

public void setSites(sites sites) {
    this.sites = sites;
}
}
