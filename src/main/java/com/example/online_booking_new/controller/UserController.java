package com.example.online_booking_new.controller;

import com.example.online_booking_new.ResouceNotFoundException.ResourceNotFoundException;
import com.example.online_booking_new.loginrequest.LoginRequest;
import com.example.online_booking_new.model.User;
import com.example.online_booking_new.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestController
@RequestMapping("")
public class UserController {
    @Autowired
    private UserService userService;

        //creates a new admin
    @PostMapping("/createadmin")
    public ResponseEntity<?> createAdmin(@RequestBody User user) {
        try {
            User createdUser = userService.createAdmin(user);
            return ResponseEntity.ok(createdUser);
        }catch(DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //admin can get all users
   // @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @GetMapping("/admin/users")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        }catch (MethodArgumentTypeMismatchException e ){
            e.getMessage();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //admin can get user by id
    @GetMapping("/admin/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable int userId) {
        try {
            User user = userService.getUserById(userId);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @PostMapping("/customer/register")
    public ResponseEntity<?> createCustomer(@RequestBody User customer) {
        try {
            User newCustomer = userService.createCustomer(customer);
            return new ResponseEntity<>("Customer registration Succesfull :  " + newCustomer.getFirstName(), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest request) {
        try {
            User customer = userService.loginWithId(request.getPhoneNumber()).getBody();
            if (customer == null) {
                System.out.println("Invalid login");
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } else {
                System.out.println("Login successful for user: " + customer.getFirstName());
                return new ResponseEntity<>(customer, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }

    @GetMapping("/customer/{phoneNumber}")
    public ResponseEntity<User> getCustomerByPhoneNumber(@PathVariable String phoneNumber) {
        try {
            User customer = userService.getCustomerByPhoneNumber(phoneNumber);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

}
