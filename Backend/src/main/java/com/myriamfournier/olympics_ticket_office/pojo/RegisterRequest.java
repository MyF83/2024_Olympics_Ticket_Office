package com.myriamfournier.olympics_ticket_office.pojo;

// This class is a DTO (Data Transfer Object) for user registration requests.
// It contains only the fours fields needed for registration. The others fields are managed by the user in his account interface
public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private Long policyId;

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Long getPolicyId() { return policyId; }
    public void setPolicyId(Long policyId) { this.policyId = policyId; }
}
