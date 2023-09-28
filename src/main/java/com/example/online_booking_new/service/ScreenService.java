package com.example.online_booking_new.service;

import com.example.online_booking_new.ResouceNotFoundException.ResourceNotFoundException;
import com.example.online_booking_new.model.Screen;
import com.example.online_booking_new.repository.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScreenService {
    @Autowired
    private ScreenRepository screenRepository;

    public List<Screen> getAllScreens() {
        return screenRepository.findAll();
    }

    public Optional<Screen> getScreenById(int id) {
        return screenRepository.findById(id);
    }

    public Screen createScreen(Screen screen) {
        return screenRepository.save(screen);
    }

    public Screen updateScreen(int id, Screen screen) {
        Optional<Screen> existingScreen = screenRepository.findById(id);
        if (existingScreen.isPresent()) {
            Screen updatedScreen = existingScreen.get();
            updatedScreen.setScreenName(screen.getScreenName());
            updatedScreen.setTotalSeats(screen.getTotalSeats());
            return screenRepository.save(updatedScreen);
        } else {
            throw new ResourceNotFoundException("Screen not found with id " + id);
        }
    }

    public void deleteScreen(int id) {
        Optional<Screen> screen = screenRepository.findById(id);
        if (screen.isPresent()) {
            screenRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Screen not found with id " + id);
        }
    }
}
