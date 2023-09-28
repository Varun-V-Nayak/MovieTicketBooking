package com.example.online_booking_new.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "Screen")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screen_id")
    private int screenId;


    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;

    @Column(name = "screen_name", unique=true)
    private String screenName;

    @Column(name = "total_seats")
    private int totalSeats;

    public int getScreenId() {
        return screenId;
    }

    public void setScreenId(int screenId) {
        this.screenId = screenId;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Screen(int screenId, Theatre theatre, String screenName, int totalSeats) {
        this.screenId = screenId;
        this.theatre = theatre;
        this.screenName = screenName;
        this.totalSeats = totalSeats;
    }

    public Screen(String screenName, int totalSeats) {
        this.screenName = screenName;
        this.totalSeats = totalSeats;
    }

    public Screen() {
    }
}
