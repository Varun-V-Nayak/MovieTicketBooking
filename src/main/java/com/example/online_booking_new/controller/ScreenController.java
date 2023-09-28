package com.example.online_booking_new.controller;

import com.example.online_booking_new.ResouceNotFoundException.ResourceNotFoundException;
import com.example.online_booking_new.model.Screen;
import com.example.online_booking_new.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/screens")
public class ScreenController {
    @Autowired
    private ScreenService screenService;

    @GetMapping("/getall")
    public
    ResponseEntity<?>getAllScreens() {
        try {
            List<Screen> screens = screenService.getAllScreens();
            return ResponseEntity.ok(screens);
        }catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getScreenById(@PathVariable(value = "id") int id) {
        try {
            Optional<Screen> screen = screenService.getScreenById(id);
            if (screen.isPresent()) {
                return ResponseEntity.ok().body(screen.get());
            } else {
                throw new ResourceNotFoundException("Screen not found with id " + id);
            }
        }catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addscreen")
    public ResponseEntity<?> createScreen(@RequestBody Screen screen) {
        try {
            Screen screen1 = screenService.createScreen(screen);
            return ResponseEntity.ok(screen1);
        }catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateScreen(@PathVariable(value = "id") int id, @RequestBody Screen screen) {
        try {
            Screen screen1 = screenService.updateScreen(id, screen);
            return ResponseEntity.ok(screen1);
        }catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteScreen(@PathVariable(value = "id") int id) {
        try {
            screenService.deleteScreen(id);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
