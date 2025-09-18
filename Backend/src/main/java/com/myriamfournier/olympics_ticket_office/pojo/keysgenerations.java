package com.myriamfournier.olympics_ticket_office.pojo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "keysgenerations")
@Getter
@Setter
public class keysgenerations {


@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long key_id;

@Column(length=256)
private String keyGenerated;



   // Default constructor (required by Hibernate)
   public keysgenerations() {
}


public keysgenerations(String keyGenerated) {
        // Default constructor
        this.keyGenerated = keyGenerated;

    }
    
}
