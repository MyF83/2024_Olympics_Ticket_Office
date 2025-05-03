package com.myriamfournier.olympics_ticket_office.pojo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "policies")
@Getter
@Setter
public class policies {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@NotNull
private Long policy_id;

private String title;
private String description;
private Date creationDate;
private String version;



   // Default constructor (required by Hibernate)
   public policies() {
}



public policies(String title, String description, Date creationDate, String version) {  
            // Default constructor
        this.title = title;
        this.description = description;
        this.creationDate = new Date(System.currentTimeMillis());
        this.version = version;
        this.creationDate = creationDate;
        this.version = version; 
    }

}
