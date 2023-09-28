package com.example.online_booking_new.controller;

import com.example.online_booking_new.loginrequest.AddShowRequest;
import com.example.online_booking_new.loginrequest.AddTheatreRequest;
import com.example.online_booking_new.loginrequest.LoginRequest;
import com.example.online_booking_new.model.Shows;
import com.example.online_booking_new.model.Theatre;
import com.example.online_booking_new.model.User;
import com.example.online_booking_new.repository.UserRepository;
import com.example.online_booking_new.service.ShowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowsController {
    @Autowired
    private ShowsService showsService;

//    @PostMapping
//    public Shows createShows(@RequestBody Shows shows) {
//
//        return showsService.createShows(shows);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getShowsById(@PathVariable int id) {
        try {
           Shows shows = showsService.getShowsById(id);
           return ResponseEntity.ok(shows);
        }catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public
    ResponseEntity<?>getAllShows() {
        try {
            List<Shows> shows = showsService.getAllShows();
            return ResponseEntity.ok(shows);
        }catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateShows(@PathVariable int id, @RequestBody Shows shows) {
        try {
            Shows shows1 =showsService.updateShows(id, shows);
            return ResponseEntity.ok(shows1);
        }catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteShows(@PathVariable int id) {
        try {
            showsService.deleteShows(id);
            return ResponseEntity.ok("Show deleted with id :"+ id);
        }catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    UserRepository userRepository;
    @PostMapping("/admin/addshow")
    public ResponseEntity<Shows> addShows(@RequestBody AddShowRequest addShowRequest) {
        LoginRequest l = null;
        l = addShowRequest.getLoginRequest();
        String phoneNumber;
        phoneNumber= l.getPhoneNumber();
        User user = null;
        user = (User) userRepository.findByPhoneNumber(phoneNumber);
        try{
            if (user.getAdmin()==true) {
                Shows savedShow = showsService.createShows(addShowRequest.getShows());
                return ResponseEntity.ok(savedShow);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }}
        catch(NullPointerException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}
