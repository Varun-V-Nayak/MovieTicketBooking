package com.example.online_booking_new.service;

import com.example.online_booking_new.ResouceNotFoundException.ResourceNotFoundException;
import com.example.online_booking_new.model.User;
import com.example.online_booking_new.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createAdmin(User user) {
        User user1 = new User();
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setEmail(user.getEmail());
        user1.setPhoneNumber(user.getPhoneNumber());
        user1.setPreferredLocation(user.getPreferredLocation());
        user1.setAdmin(true);
        return userRepository.save(user1);
    }

    public User getUserById(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            try {
                throw new ResourceNotFoundException("User not found with id " + userId);
            } catch (ResourceNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public User createCustomer(User customer) {
        customer.setAdmin(false);
        return userRepository.save(customer);
    }
    public ResponseEntity<User> loginWithId(String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber);
        if (user == null) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            //throw new UserNotFoundException("User not found with phone number: " + phoneNumber);
        }
        return ResponseEntity.ok(user);
    }
    public User getCustomerByPhoneNumber(String phoneNumber) {
        //return userRepository.findByPhoneNumber(phoneNumber);
        //.orElseThrow(() -> new ResourceNotFoundException("Customer", "phone number", phoneNumber));
        User c = null;
        c = (User) userRepository.findByPhoneNumber(phoneNumber);
        return c;
    }
}
