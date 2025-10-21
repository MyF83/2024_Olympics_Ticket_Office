package com.myriamfournier.olympics_ticket_office.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "keysgenerations")
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
    
    public Long getKey_id() {
        return key_id;
    }

    public void setKey_id(Long key_id) {
        this.key_id = key_id;
    }

    public String getKeyGenerated() {
        return keyGenerated;
    }

    public void setKeyGenerated(String keyGenerated) {
        this.keyGenerated = keyGenerated;
    }
}
