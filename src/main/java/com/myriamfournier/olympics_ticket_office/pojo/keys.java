package com.myriamfournier.olympics_ticket_office.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "keys")
@Getter
@Setter
public class keys {


@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@NotNull
private Long keyId;

private String keyGenerated;

public keys(String keyGenerated) {
    // Default constructor
        this.keyGenerated = keyGenerated;
    }
}
