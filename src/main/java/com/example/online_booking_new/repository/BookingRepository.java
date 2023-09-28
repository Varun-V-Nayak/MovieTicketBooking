package com.example.online_booking_new.repository;

import com.example.online_booking_new.model.Booking;
import com.example.online_booking_new.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByUser(User user);

}
