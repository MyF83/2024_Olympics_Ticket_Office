package com.myriamfournier.olympics_ticket_office.pojo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "saleskeys")
@Getter
@Setter
public class saleskeys {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long salekey_id;

private Date date;

@OneToOne
@JoinColumn(name= "key_id", nullable = true)
private keysgenerations keysgenerations;


@JsonBackReference
@OneToOne(mappedBy = "saleskeys") // Bidirectional relationship
private sales sales;


// Default constructor (required by Hibernate)
public saleskeys() {
}


public saleskeys(Date date, keysgenerations keysgenerations) {
    // Default constructor
    this.date = date;
    this.keysgenerations = keysgenerations;

}

public keysgenerations getKeysgenerations() {
    return keysgenerations;
}

public void setKeysgenerations(keysgenerations keysgenerations) {
    this.keysgenerations = keysgenerations;
}


}
