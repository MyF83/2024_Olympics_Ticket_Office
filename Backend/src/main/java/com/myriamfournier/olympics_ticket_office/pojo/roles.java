package com.myriamfournier.olympics_ticket_office.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "roles")
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

public Long getRole_id() {
    return role_id;
}

public void setRole_id(Long role_id) {
    this.role_id = role_id;
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
}
