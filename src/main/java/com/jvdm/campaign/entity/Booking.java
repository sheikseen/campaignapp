package com.jvdm.campaign.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
//import java.util.List;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @Column(name = "bookingid")
    private String bookingId;

    @Column(name = "dependentcount")
    private int dependentCount;

    @Column(name = "paymentvia")
    private PaymentMethod paymentVia;

    @Column(name = "totalamount")
    private BigDecimal totalAmount;

    @Column(name = "amountpaid")
    private BigDecimal amountPaid;

    @Column(name = "ispaid")
    private boolean isPaid;

    @Column(name = "bookingstatus")
    private BookingStatus bookingStatus;

    @Column(name = "bookingdate")
    private Date bookingDate;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "eventid", referencedColumnName = "eventid")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "dependentid", referencedColumnName = "dependentid")
    private Dependent dependent;

    public String getBookingId() {
        return bookingId;
    }

    public Booking setBookingId(String bookingId) {
        this.bookingId = bookingId;
        return this;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public Booking setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
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
        return paymentVia;
    }

    public Booking setPaidVia(PaymentMethod paymentVia) {
        this.paymentVia = paymentVia;
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

    // public Principal getLoggedInPrincipal() {
    // return loggedInPrincipal;
    // }

    // public Booking setLoggedInPrincipal(Principal loggedInPrincipal) {
    // this.loggedInPrincipal = loggedInPrincipal;
    // return this;
    // }

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
