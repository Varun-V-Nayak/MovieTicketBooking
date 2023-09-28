package com.example.online_booking_new.controller;

import com.example.online_booking_new.loginrequest.AddMovieRequest;
import com.example.online_booking_new.loginrequest.AddTheatreRequest;
import com.example.online_booking_new.loginrequest.LoginRequest;
import com.example.online_booking_new.model.Movie;
import com.example.online_booking_new.model.Theatre;
import com.example.online_booking_new.model.User;
import com.example.online_booking_new.repository.UserRepository;
import com.example.online_booking_new.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theatres")
public class TheatreController {
    @Autowired
    private TheatreService theatreService;

    @GetMapping("/getall")
    public ResponseEntity<?> getAllTheatres() {
        try {

            List<Theatre> theatre =  theatreService.getAllTheatres();
            return ResponseEntity.ok(theatre);
        }catch (Exception e){
            e.getMessage();
            return new ResponseEntity<String>("exception"+e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{theatreId}")
    public ResponseEntity<?> getTheatreById(@PathVariable int theatreId) {
        try {
            Theatre theatre = theatreService.getTheatreById(theatreId);
            return ResponseEntity.ok(theatre);
        } catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/save")
    public ResponseEntity<?> saveTheatre(@RequestBody Theatre theatre) {
        try {
            Theatre theatre1= theatreService.saveTheatre(theatre);
            return ResponseEntity.ok(theatre1);
        }catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{theatreId}")
    public ResponseEntity<?> deleteTheatre(@PathVariable int  theatreId) {
        try {
            theatreService.deleteTheatre(theatreId);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @Autowired
    UserRepository userRepository;
@PostMapping("/admin/addtheatre")
public ResponseEntity<Theatre> addTheatre(@RequestBody AddTheatreRequest addTheatreRequest) {
    LoginRequest l = null;
    l = addTheatreRequest.getLoginRequest();
    String phoneNumber;
    phoneNumber= l.getPhoneNumber();
    User user = null;
    user = (User) userRepository.findByPhoneNumber(phoneNumber);
    try{
        if (user.getAdmin()==true) {
            Theatre savedTheatre = theatreService.saveTheatre(addTheatreRequest.getTheatre());
            return ResponseEntity.ok(savedTheatre);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }}
    catch(NullPointerException e){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
}
