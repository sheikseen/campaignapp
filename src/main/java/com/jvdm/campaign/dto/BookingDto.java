package com.jvdm.campaign.dto;

import com.jvdm.campaign.entity.Booking;
import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class BookingDto
{
    public String bookingId;
    public String campId;
    public String bookerName;
    public int bookerAge;
    private String bookerPhone;
    private String bookerPlace;
    public int seatCount;
    public BigDecimal totalAmount;
    public Date bookingDate;
    public Booking.PaymentMethod paidVia;
    public BigDecimal amtPaid;
    public Booking.BookingStatus bookingStatus;
    public String loggedInPrincipalId;
    public List<DependentDTO> dependents;

    public String getBookingId() {
        return bookingId;
    }

    public BookingDto setBookingId(String bookingId) {
        this.bookingId = bookingId;
        return this;
    }

    public String getCampId() {
        return campId;
    }

    public BookingDto setCampId(String campId) {
        this.campId = campId;
        return this;
    }

    public String getBookerName() {
        return bookerName;
    }

    public BookingDto setBookerName(String bookerName) {
        this.bookerName = bookerName;
        return this;
    }

    public int getBookerAge() {
        return bookerAge;
    }

    public BookingDto setBookerAge(int bookerAge) {
        this.bookerAge = bookerAge;
        return this;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public BookingDto setSeatCount(int seatCount) {
        this.seatCount = seatCount;
        return this;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public BookingDto setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public BookingDto setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
        return this;
    }

    public BigDecimal getAmtPaid() {
        return amtPaid;
    }

    public BookingDto setAmtPaid(BigDecimal amtPaid) {
        this.amtPaid = amtPaid;
        return this;
    }

    public Booking.BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public BookingDto setBookingStatus(Booking.BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
        return this;
    }

    public String getLoggedInPrincipalId() {
        return loggedInPrincipalId;
    }

    public BookingDto setLoggedInPrincipalId(String loggedInPrincipalId) {
        this.loggedInPrincipalId = loggedInPrincipalId;
        return this;
    }

    public List<DependentDTO> getDependents() {
        return dependents;
    }

    public BookingDto setDependents(List<DependentDTO> dependents) {
        this.dependents = dependents;
        return this;
    }

    public String getBookerPhone() {
        return bookerPhone;
    }

    public BookingDto setBookerPhone(String bookerPhone) {
        this.bookerPhone = bookerPhone;
        return this;
    }

    public String getBookerPlace() {
        return bookerPlace;
    }

    public BookingDto setBookerPlace(String bookerPlace) {
        this.bookerPlace = bookerPlace;
        return this;
    }

    public Booking.PaymentMethod getPaidVia() {
        return paidVia;
    }

    public BookingDto setPaidVia(Booking.PaymentMethod paidVia) {
        this.paidVia = paidVia;
        return this;
    }

    public BookingDto() {
    }
}

