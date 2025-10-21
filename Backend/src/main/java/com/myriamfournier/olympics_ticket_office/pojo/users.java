package com.myriamfournier.olympics_ticket_office.pojo;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class users {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long user_id;

private String firstname;
private String lastname;
private String username;
private String email;
private String password;
private String phoneNumber;
private String address;
private Timestamp creationDate;


@ManyToOne
@JoinColumn(name= "role_id", nullable = true)
private roles roles;


@OneToOne
@JoinColumn(name= "userkey_id", nullable = true)
private userskeys userskeys;


@ManyToOne
@JoinColumn(name= "usersel_id", nullable = true)
private userselections userselections;


@ManyToOne
@JoinColumn(name= "policy_id", nullable = true)
private policies policies;

private Timestamp policySignDate;
private String city;
private String postalCode;

@ManyToOne
@JoinColumn(name= "country_id", nullable = true)
private countries countries;

@OneToMany(mappedBy = "users") 
@JsonIgnore
private List<carts> carts;

// Default constructor (required by Hibernate)
public users() {
}

public users(String firstname, String lastname, String username, String email, String password, String phoneNumber, Date creationDate, roles roles, userskeys userskeys, userselections userselections, policies policies, Date policySignDate, String address, String city, String postalCode, countries countries) {  
    this.firstname = firstname;
    this.lastname = lastname;
    this.username = username;
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.creationDate = new Timestamp(System.currentTimeMillis());
    this.roles = roles;
    this.userskeys = userskeys;
    this.userselections = userselections;
    this.policies = policies;
    this.policySignDate = new Timestamp(System.currentTimeMillis());
    this.address = address;
    this.city = city;
    this.postalCode = postalCode;
    this.countries = countries;
}

@PrePersist
protected void onPrePersist() {
    this.creationDate = Timestamp.valueOf(LocalDateTime.now());
    this.policySignDate = Timestamp.valueOf(LocalDateTime.now());
}

// Getters and Setters
public Long getUser_id() {
    return user_id;
}

public void setUser_id(Long user_id) {
    this.user_id = user_id;
}

public String getFirstname() {
    return firstname;
}

public void setFirstname(String firstname) {
    this.firstname = firstname;
}

public String getLastname() {
    return lastname;
}

public void setLastname(String lastname) {
    this.lastname = lastname;
}

public String getUsername() {
    return username;
}

public void setUsername(String username) {
    this.username = username;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}

public String getPhoneNumber() {
    return phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
}

public String getAddress() {
    return address;
}

public void setAddress(String address) {
    this.address = address;
}

public Timestamp getCreationDate() {
    return creationDate;
}

public void setCreationDate(Timestamp creationDate) {
    this.creationDate = creationDate;
}

public roles getRoles() {
    return roles;
}

public void setRoles(roles roles) {
    this.roles = roles;
}

public userskeys getUserskeys() {
    return userskeys;
}

public void setUserskeys(userskeys userskeys) {
    this.userskeys = userskeys;
}

public userselections getUserselections() {
    return userselections;
}

public void setUserselections(userselections userselections) {
    this.userselections = userselections;
}

public policies getPolicies() {
    return policies;
}

public void setPolicies(policies policies) {
    this.policies = policies;
}

public Timestamp getPolicySignDate() {
    return policySignDate;
}

public void setPolicySignDate(Timestamp policySignDate) {
    this.policySignDate = policySignDate;
}

public String getCity() {
    return city;
}

public void setCity(String city) {
    this.city = city;
}

public String getPostalCode() {
    return postalCode;
}

public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
}

public countries getCountries() {
    return countries;
}

public void setCountries(countries countries) {
    this.countries = countries;
}

public List<carts> getCarts() {
    return carts;
}

public void setCarts(List<carts> carts) {
    this.carts = carts;
}
}
