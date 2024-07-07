package com.jvdm.campaign.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "user", uniqueConstraints = {

        @UniqueConstraint(columnNames = "email")
})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "userid")
    private String userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String place;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = true, unique = true)
    private String email;

    @Column(nullable = true)
    private String password;

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "principal_roles", joinColumns = @JoinColumn(name = "principal_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role_id")
    private Set<Role.RoleId> roles = new HashSet<>();

    public Set<Role.RoleId> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role.RoleId> roleIds) {
        this.roles = roleIds;
    }

    public User(String userId, String name, String place, String phone, int age, String gender, String email,
            String address, String password, Set<Role.RoleId> roles) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.place = place;
        this.age = age;
        this.gender = gender;
        this.password = password;
        this.roles = roles;
    }
}