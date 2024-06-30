package com.jvdm.campaign.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Dependents")
public class Dependent {

    @Id
    @Column(name = "dependent_id")
    private String dependentId;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "phoneNo", nullable = false)
    private String phoneNo;

    public String getDependentId() {
        return dependentId;
    }

    public Dependent setDependentId(String dependentId) {
        this.dependentId = dependentId;
        return this;
    }

    public Booking getBooking() {
        return booking;
    }

    public Dependent setBooking(Booking booking) {
        this.booking = booking;
        return this;
    }

    public String getName() {
        return name;
    }

    public Dependent setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Dependent setAge(int age) {
        this.age = age;
        return this;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public Dependent setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
        return this;
    }

    public Dependent() {
    }
}
