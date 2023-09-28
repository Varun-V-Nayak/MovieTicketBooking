package com.example.online_booking_new.repository;

import com.example.online_booking_new.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByPhoneNumber(String  phoneNumber);
}
