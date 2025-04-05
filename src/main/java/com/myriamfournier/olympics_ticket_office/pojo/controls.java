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
@Table(name = "controls")
@Getter
@Setter
public class controls {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@NotNull
private Long controlId;

private Date date;
private String scanCode;
private Boolean isTicketValid;

}
