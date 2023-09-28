package com.example.online_booking_new.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Shows show;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "num_of_seats_booked")
    private int numOfSeatsBooked;

    @Column(name = "booking_time")
    private LocalDateTime bookingTime;

    public Booking(int id, Shows show, User user, int numOfSeatsBooked, LocalDateTime bookingTime) {
        this.id = id;
        this.show = show;
        this.user = user;
        this.numOfSeatsBooked = numOfSeatsBooked;
        this.bookingTime = bookingTime;
    }

    public Booking(int numOfSeatsBooked, LocalDateTime bookingTime) {
        this.numOfSeatsBooked = numOfSeatsBooked;
        this.bookingTime = bookingTime;
    }

    public int getId() {
        return id;
    }



    public Shows getShow() {
        return show;
    }

    public void setShow(Shows show) {
        this.show = show;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNumOfSeatsBooked() {
        return numOfSeatsBooked;
    }

    public void setNumOfSeatsBooked(int numOfSeatsBooked) {
        this.numOfSeatsBooked = numOfSeatsBooked;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Booking() {
    }
}
