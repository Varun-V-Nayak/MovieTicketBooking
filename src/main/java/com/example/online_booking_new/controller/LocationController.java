package com.example.online_booking_new.controller;

import com.example.online_booking_new.loginrequest.LocationRequest;
import com.example.online_booking_new.model.Location;
import com.example.online_booking_new.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping
    public ResponseEntity<?> createLocation(@RequestBody LocationRequest locationRequest) {
        try {
            Location location = locationService.createLocation(locationRequest.getName(), locationRequest.getAddress());
            return new ResponseEntity<>(location, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("could not create a location");
        }catch(DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e ){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("exception "+e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLocation(@PathVariable int id) {
        try {
            Location location = locationService.getLocation(id);
            if (location == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(location, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("exception");
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllLocations() {
        try {
            List<Location> locations = locationService.getAllLocations();
            return new ResponseEntity<>(locations, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("exception");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLocation(@PathVariable int id, @RequestBody LocationRequest locationRequest) {
        try {
            Location location = locationService.updateLocation(id, locationRequest.getName(), locationRequest.getAddress());
            if (location == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(location, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("exception");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable int id) {
        try {
            locationService.deleteLocation(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
