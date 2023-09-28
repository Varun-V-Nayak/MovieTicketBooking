package com.example.online_booking_new.controller;

import com.example.online_booking_new.model.Booking;
import com.example.online_booking_new.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<String> bookShow(@RequestBody Booking booking) {
        if(booking.getNumOfSeatsBooked() >=1) {
            try {
                bookingService.bookShow(booking);
                return ResponseEntity.ok("Booking Successful");
            } catch (RuntimeException ex) {
                return ResponseEntity.badRequest().body("Booking Failed");
            }
        }else{
            return ResponseEntity.badRequest().body("Invalid entry for Number of seats booked");
        }

    }


    @GetMapping("/history/{userId}")
    public ResponseEntity<?> getBookingHistory(@PathVariable int userId) {
        try {
            List<Booking> bookingHistory = null;
            bookingHistory =bookingService.getBookingHistory(userId);

            if(bookingHistory.size()!=0) {
                return ResponseEntity.ok(bookingHistory);
            }else{
                return ResponseEntity.ok("no booking for the user id ");
            }
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("enter a valid user id");
        }
        catch(Exception e ){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("exception");
        }
    }
}
