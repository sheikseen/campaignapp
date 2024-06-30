package com.jvdm.campaign.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "camps")
public class Camp implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @Column(nullable = false)
    private String place;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private BigDecimal childSeatAmount;

    @Column(nullable = false)
    private int seats;

    @Column
    private int seatsBooked;

    @Column(nullable = false)
    private CampStatus status;

    public Camp() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getChildSeatAmount() {
        return childSeatAmount;
    }

    public void setChildSeatAmount(BigDecimal amount) {
        this.childSeatAmount = amount;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Camp(String id, String title, String description, Date startDate, Date endDate, String place, BigDecimal amount, BigDecimal childSeatAmount, int seats, int seatsBooked, CampStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.place = place;
        this.amount = amount;
        this.childSeatAmount = childSeatAmount;
        this.seats = seats;
        this.seatsBooked = seatsBooked;
        this.status = status;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public Camp setSeatsBooked(int seatsBooked) {
        this.seatsBooked = seatsBooked;
        return this;
    }

    public CampStatus getStatus() {
        return status;
    }

    public Camp setStatus(CampStatus status) {
        this.status = status;
        return this;
    }

    public enum CampStatus {
        CREATED,
        STARTED,
        CANCELLED,
        ENDED
    }

}


