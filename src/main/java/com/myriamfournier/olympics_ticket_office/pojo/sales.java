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
@Table(name = "sales")
@Getter
@Setter
public class sales {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@NotNull
private Long saleId;

private Date date;

public sales(Date date) {
        // Default constructor
        this.date = date;
    }
}
