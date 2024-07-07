package com.jvdm.campaign.dto;

import com.jvdm.campaign.entity.Booking;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class BookingDto {
    public String bookingId;
    public int dependentCount;
    public String paymentVia;
    public BigDecimal totalAmount;
    public BigDecimal amountPaid;
    public boolean isPaid;
    public Booking.BookingStatus bookingStatus;
    public Date bookingDate;
    public String userId;
    public String dependentId;
    public String eventId;
    private String loggedInUserId;

    public String getBookingId() {
        return bookingId;
    }

    public BookingDto setBookingId(String bookingId) {
        this.bookingId = bookingId;
        return this;
    }

    public int getDependentCount() {
        return dependentCount;
    }

    public void setDependentCount(int dependentCount) {
        this.dependentCount = dependentCount;
    }

    public String getPaymentVia() {
        return paymentVia;
    }

    public void setPaymentVia(String paymentVia) {
        this.paymentVia = paymentVia;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public BookingDto setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public BookingDto setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
        return this;
    }

    public Booking.BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public BookingDto setBookingStatus(Booking.BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDependentId() {
        return dependentId;
    }

    public void setDependentId(String dependentId) {
        this.dependentId = dependentId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getLoggedInUserlId() {
        return loggedInUserId;
    }

    public BookingDto setLoggedInUserId(String loggedInUserId) {
        this.loggedInUserId = loggedInUserId;
        return this;
    }

    public List<DependentDTO> dependents;

    public List<DependentDTO> getDependents() {
        return dependents;
    }

    public BookingDto setDependents(List<DependentDTO> dependents) {
        this.dependents = dependents;
        return this;
    }

    public BookingDto() {
    }
}
