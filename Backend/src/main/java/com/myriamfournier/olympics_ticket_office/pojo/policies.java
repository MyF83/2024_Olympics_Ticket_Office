package com.myriamfournier.olympics_ticket_office.pojo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "policies")
public class policies {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long policy_id;

private String title;
private String description;
private String url;
private Date creationDate;
private String version;
private Boolean isActive = false; // Default value for isActive

// Default constructor (required by Hibernate)
public policies() {
}

public policies(String title, String description, String url, Date creationDate, String version, Boolean isActive) {  
    // Default constructor
    this.title = title;
    this.description = description;
    this.url = url;
    this.creationDate = new Date(System.currentTimeMillis());
    this.version = version;
    this.creationDate = creationDate;
    this.version = version; 
    this.isActive = isActive;
}

public Long getPolicy_id() {
    return policy_id;
}

public void setPolicy_id(Long policy_id) {
    this.policy_id = policy_id;
}

public String getTitle() {
    return title;
}

public void setTitle(String title) {
    this.title = title;
}

public String getDescription() {
    return description;
}

public void setDescription(String description) {
    this.description = description;
}

public String getUrl() {
    return url;
}

public void setUrl(String url) {
    this.url = url;
}

public Date getCreationDate() {
    return creationDate;
}

public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
}

public String getVersion() {
    return version;
}

public void setVersion(String version) {
    this.version = version;
}

public Boolean getIsActive() {
    return isActive;
}

public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
}
}
