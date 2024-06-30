package com.jvdm.campaign.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class SignupRequest {

    @NotBlank
    private String name;
    @NotBlank
    @Pattern(regexp="^\\d{10}$", message="Invalid phone number")
    private String phone;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private Set<String> roles;

    public String name() {
        return name;
    }

    public SignupRequest setName(String name) {
        this.name = name;
        return this;
    }


    public String phone() {
        return phone;
    }

    public SignupRequest setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String email() {
        return email;
    }

    public SignupRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String username() {
        return username;
    }

    public SignupRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String password() {
        return password;
    }

    public SignupRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public Set<String> roles() {
        return roles;
    }

    public SignupRequest setRoles(Set<String> roles) {
        this.roles = roles;
        return this;
    }
}
