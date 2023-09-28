package com.example.online_booking_new.repository;

import com.example.online_booking_new.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
}
