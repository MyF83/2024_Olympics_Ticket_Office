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
@Table(name = "userskeys")
@Getter
@Setter
public class userskeys {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@NotNull
private Long user_key_id;
private Date date;

@OneToOne
@JoinColumn(name= "user_id", nullable = true)
private users users;

@OneToOne
@JoinColumn(name= "key_id", nullable = true)
private keysgenerations keysgenerations;


public userskeys(Date date, users users, keysgenerations keysgenerations) {
    // Default constructor
    this.date = date;
    this.users = users;
    this.keysgenerations = keysgenerations;
    }


public void setUser(users users2) {

    throw new UnsupportedOperationException("Unimplemented method 'setUser'");
}


public void setKey(keysgenerations keysEntity) {

    throw new UnsupportedOperationException("Unimplemented method 'setKey'");
}


public Object getKey() {
    
    throw new UnsupportedOperationException("Unimplemented method 'getKey'");
}


public String getKeyGenerated() {
    
    throw new UnsupportedOperationException("Unimplemented method 'getKeyGenerated'");
}
}
