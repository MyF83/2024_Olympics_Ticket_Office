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

@Entity
@Table(name = "saleskeys")
@Getter
@Setter
public class saleskeys {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@NotNull
private Long sale_key_id;
private Date date;

@OneToOne
@JoinColumn(name= "key_id", nullable = true)
private keysgenerations keysgenerations;

public saleskeys(Date date, keysgenerations keysgenerations) {
    // Default constructor
    this.date = date;
    this.keysgenerations = keysgenerations;

    }

public void setKey(keysgenerations keysEntity) {

    throw new UnsupportedOperationException("Unimplemented method 'setKey'");
}

public void setSale(sales sales) {

    throw new UnsupportedOperationException("Unimplemented method 'setSale'");
}

public Object getKey() {
   
    throw new UnsupportedOperationException("Unimplemented method 'getKey'");
}

public String getKeyGenerated() {
    
    throw new UnsupportedOperationException("Unimplemented method 'getKeyGenerated'");
}
}
