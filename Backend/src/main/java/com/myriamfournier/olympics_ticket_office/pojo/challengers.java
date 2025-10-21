package com.myriamfournier.olympics_ticket_office.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "challengers")
public class challengers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long challenger_id;
    
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = true) 
    private countries countries;

    // Default constructor (required by Hibernate)
    public challengers() {
    }
    
    public challengers(String name, countries countries) {
        // Default constructor
        this.name = name;
        this.countries = countries;
    }

    public Long getChallenger_id() {
        return challenger_id;
    }

    public void setChallenger_id(Long challenger_id) {
        this.challenger_id = challenger_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public countries getCountries() {
        return countries;
    }

    public void setCountries(countries countries) {
        this.countries = countries;
    }
}
