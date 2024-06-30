package com.jvdm.campaign.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Bookings")
public class Booking {

    @Id
    @Column(name = "booking_id")
    private String bookingId;

    @Column(name = "booker_name")
    private String bookerName;

    @Column(name = "booker_age")
    private int bookerAge;

    @Column(nullable = false)
    private String bookerPhone;

    @Column(nullable = false)
    private String bookerPlace;

    @Column(name = "booking_date")
    private Date bookingDate;

    @Column(name = "seat_count")
    private int seatCount;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "paidVia")
    private PaymentMethod paidVia;

    @Column(name = "AmtPaid")
    private BigDecimal amountPaid;

    @Column(name = "isPaid")
    private boolean isPaid;

    @Column(name = "booking_status")
    private BookingStatus bookingStatus;

    @ManyToOne
    @JoinColumn(name = "principal_id", referencedColumnName = "id")
    private Principal loggedInPrincipal;

    @ManyToOne
    @JoinColumn(name = "camp_id", referencedColumnName = "id")
    private Camp camp;

    public String getBookingId() {
        return bookingId;
    }

    public Booking setBookingId(String bookingId) {
        this.bookingId = bookingId;
        return this;
    }

    public String getBookerName() {
        return bookerName;
    }

    public Booking setBookerName(String bookerName) {
        this.bookerName = bookerName;
        return this;
    }

    public int getBookerAge() {
        return bookerAge;
    }

    public Booking setBookerAge(int bookerAge) {
        this.bookerAge = bookerAge;
        return this;
    }

    public String getBookerPhone() {
        return bookerPhone;
    }

    public Booking setBookerPhone(String bookerPhone) {
        this.bookerPhone = bookerPhone;
        return this;
    }

    public String getBookerPlace() {
        return bookerPlace;
    }

    public Booking setBookerPlace(String bookerPlace) {
        this.bookerPlace = bookerPlace;
        return this;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public Booking setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
        return this;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public Booking setSeatCount(int seatCount) {
        this.seatCount = seatCount;
        return this;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public Booking setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public PaymentMethod getPaidVia() {
        return paidVia;
    }

    public Booking setPaidVia(PaymentMethod paidVia) {
        this.paidVia = paidVia;
        return this;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public Booking setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
        return this;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public Booking setPaid(boolean paid) {
        isPaid = paid;
        return this;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public Booking setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
        return this;
    }

    public Principal getLoggedInPrincipal() {
        return loggedInPrincipal;
    }

    public Booking setLoggedInPrincipal(Principal loggedInPrincipal) {
        this.loggedInPrincipal = loggedInPrincipal;
        return this;
    }

    public Camp getCamp() {
        return camp;
    }

    public Booking setCamp(Camp camp) {
        this.camp = camp;
        return this;
    }

    public Booking() {
    }

    public enum BookingStatus {
        PENDING,
        CONFIRMED,
        CANCELLED,
        COMPLETED,
        REJECTED
    }

    public enum PAYMENT_STATUS {
        PENDING,
        PAID,
        REFUNDED,
        CANCELLED,
    }

    public enum PaymentMethod {
        CASH,
        GOOGLE_PAY
    }

}
