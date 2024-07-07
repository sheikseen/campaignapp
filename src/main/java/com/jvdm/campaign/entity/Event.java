package com.jvdm.campaign.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "event")
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "eventid", nullable = false)
    private String eventId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "place", nullable = false)
    private String place;

    @Column(name = "seats", nullable = false)
    private int seats;

    @Column(name = "adultamount", nullable = false)
    private BigDecimal adultAmount;

    @Column(name = "childamount", nullable = false)
    private BigDecimal childAmount;

    @Column(name = "startdate", nullable = false)
    private Date startDate;

    @Column(name = "enddate", nullable = false)
    private Date endDate;

    @Column(name = "status", nullable = true)
    private EventStatus status;

    @Column(name = "seatsbooked", nullable = true)
    private int seatsBooked;

    public Event() {
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public BigDecimal getAdultAmount() {
        return adultAmount;
    }

    public void setAdultAmount(BigDecimal adultAmount) {
        this.adultAmount = adultAmount;
    }

    public BigDecimal getChildAmount() {
        return childAmount;
    }

    public void setChildAmount(BigDecimal childAmount) {
        this.childAmount = childAmount;
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

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public Event setSeatsBooked(int seatsBooked) {
        this.seatsBooked = seatsBooked;
        return this;
    }

    public EventStatus getStatus() {
        return status;
    }

    public Event setStatus(EventStatus status) {
        this.status = status;
        return this;
    }

    public Event(String eventId, String title, String description, Date startDate, Date endDate, String place,
            BigDecimal adultAmount, BigDecimal childAmount, int seats, int seatsBooked, EventStatus status) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.place = place;
        this.adultAmount = adultAmount;
        this.childAmount = childAmount;
        this.seats = seats;
        this.seatsBooked = seatsBooked;
        this.status = status;
    }

    public enum EventStatus {
        CREATED,
        STARTED,
        CANCELLED,
        ENDED
    }

}
