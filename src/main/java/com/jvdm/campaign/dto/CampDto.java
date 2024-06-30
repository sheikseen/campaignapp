package com.jvdm.campaign.dto;

import com.jvdm.campaign.entity.Camp;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CampDto implements Serializable {
    private String id;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private String place;
    @DecimalMin(value = "1.00", inclusive = true, message = "Amount must be greater than or equal to 1.00")
    @Digits(integer=10, fraction=2, message="Amount has exceeded the allowed precision")
    private BigDecimal amount;
    @DecimalMin(value = "1.00", inclusive = true, message = "Amount must be greater than or equal to 1.00")
    @Digits(integer=10, fraction=2, message="Amount has exceeded the allowed precision")
    private BigDecimal childSeatAmount;
    private int seats;
    private int seatsBooked;
    private Camp.CampStatus status;

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

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public CampDto setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public BigDecimal getChildSeatAmount() {
        return childSeatAmount;
    }

    public CampDto setChildSeatAmount(BigDecimal childSeatAmount) {
        this.childSeatAmount = childSeatAmount;
        return this;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public CampDto setSeatsBooked(int seatsBooked) {
        this.seatsBooked = seatsBooked;
        return this;
    }

    public Camp.CampStatus getStatus() {
        return status;
    }

    public CampDto setStatus(Camp.CampStatus status) {
        this.status = status;
        return this;
    }

    public CampDto() {
    }

}
