package com.example.online_booking_new.service;

import com.example.online_booking_new.model.Booking;
import com.example.online_booking_new.model.Shows;
import com.example.online_booking_new.model.User;
import com.example.online_booking_new.repository.BookingRepository;
import com.example.online_booking_new.repository.ShowsRepository;
import com.example.online_booking_new.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ShowsRepository showsRepository;

    @Autowired
    private UserRepository userRepository;

    public Booking bookShow(Booking booking) {
        try {
            Shows show = showsRepository.findById(booking.getShow().getId()).orElse(null);
            User user = userRepository.findById(booking.getUser().getUserId()).orElse(null);

            if (show == null || user == null) {
                throw new IllegalArgumentException("Invalid show or user");
            }

            int availableSeats = show.getAvailableSeats();
            int numOfSeatsBooked = booking.getNumOfSeatsBooked();

            if (availableSeats < numOfSeatsBooked) {
                throw new IllegalArgumentException("Not enough available seats");
            }

            show.setAvailableSeats(availableSeats - numOfSeatsBooked);

            booking.setShow(show);
            booking.setUser(user);
            booking.setBookingTime(LocalDateTime.now());

            return bookingRepository.save(booking);
        }catch (IllegalArgumentException ex) {
            throw new RuntimeException("Error booking show: " + ex.getMessage());
        }
        catch (Exception e) {
            throw new RuntimeException("Error booking show: " + e.getMessage());
        }
    }


    public List<Booking> getBookingHistory(int userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new IllegalArgumentException("Invalid userz");
        }

        return bookingRepository.findByUser(user);
    }
}
