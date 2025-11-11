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

@Entity
@Table(name = "userskeys")
public class userskeys {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userkey_id;
    private Date date;

    @OneToOne
    @JoinColumn(name= "key_id", nullable = true)
    private keysgenerations keysgenerations;

    public userskeys() {
        // Default constructor required by JPA
    }

    public userskeys(Date date, keysgenerations keysgenerations) {
        // Default constructor
        this.date = date;
        this.keysgenerations = keysgenerations;
    }

    public Long getUserkey_id() {
        return userkey_id;
    }

    public void setUserkey_id(Long userkey_id) {
        this.userkey_id = userkey_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public keysgenerations getKeysgenerations() {
        return keysgenerations;
    }

    public void setKeysgenerations(keysgenerations keysgenerations) {
        this.keysgenerations = keysgenerations;
    }

    public keysgenerations getKey() {
        return keysgenerations;
    }

    public void setKey(keysgenerations keysgenerations) {
        this.keysgenerations = keysgenerations;
    }

    public void setKeygeneration(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setKeygeneration'");
    }
}
