package com.example.online_booking_new.service;

import com.example.online_booking_new.model.Shows;
import com.example.online_booking_new.repository.ShowsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowsService {
    @Autowired
    private ShowsRepository showsRepository;

    public Shows createShows(Shows shows) {
        return showsRepository.save(shows);
    }

    public Shows getShowsById(int id) {
        return showsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Shows with id " + id + " not found"));
    }

    public List<Shows> getAllShows() {
        return showsRepository.findAll();
    }

    public Shows updateShows(int id, Shows shows) {
        Shows existingShows = getShowsById(id);
        existingShows.setMovie(shows.getMovie());
        //existingShows.setTheatre(shows.getTheatre());
        existingShows.setLocation(shows.getLocation());
        existingShows.setScreen(shows.getScreen());
        existingShows.setShowTime(shows.getShowTime());
        existingShows.setAvailableSeats(shows.getAvailableSeats());
        return showsRepository.save(existingShows);
    }

    public void deleteShows(int id) {
        Shows existingShows = getShowsById(id);
        showsRepository.delete(existingShows);
    }
}
