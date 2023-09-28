package com.example.online_booking_new.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "theatre")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theatre_id")
    private int theatreId;

    @Column(name = "theatre_name", unique = true)
    private String theatreName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "total_seats")
    private int totalSeats;

    public Theatre(int theatreId, String theatreName, Location location, int totalSeats) {
        this.theatreId = theatreId;
        this.theatreName = theatreName;
        this.location = location;
        this.totalSeats = totalSeats;
    }

    public Theatre() {
    }

    public int getTheatreId() {
        return theatreId;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Theatre(String theatreName, int totalSeats) {
        this.theatreName = theatreName;
        this.totalSeats = totalSeats;
    }
}
