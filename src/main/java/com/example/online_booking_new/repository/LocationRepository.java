package com.example.online_booking_new.repository;

import com.example.online_booking_new.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Integer> {
}
