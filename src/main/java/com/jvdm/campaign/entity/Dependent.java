package com.jvdm.campaign.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "dependent")
public class Dependent {

    @Id
    @Column(name = "dependentid")
    private String dependentId;

    @ManyToOne
    @JoinColumn(name = "bookingid", nullable = false)
    private Booking booking;

    @Column(name = "depententname", nullable = false)
    private String depententName;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "place", nullable = false)
    private String place;

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
        return depententName;
    }

    public Dependent setName(String depententName) {
        this.depententName = depententName;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Dependent setAge(int age) {
        this.age = age;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Dependent() {
    }
}
