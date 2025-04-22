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
@Table(name = "offers")
@Getter
@Setter
public class offers {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@NotNull   
private Long offer_id;


private String title;
private String description;
private Integer rate;
private Integer nbSpectators;


public offers(String title, String description, Integer rate, Integer nbSpectators) {  
        // Default constructor
    this.title = title;
    this.description = description;
    this.rate = rate;
    this.nbSpectators = nbSpectators;
    }


}
