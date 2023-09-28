package com.example.online_booking_new.service;

import com.example.online_booking_new.ResouceNotFoundException.ResourceNotFoundException;
import com.example.online_booking_new.model.Theatre;
import com.example.online_booking_new.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreService {
    @Autowired
    private TheatreRepository theatreRepository;

    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    public Theatre getTheatreById(int theatreId) {
        return theatreRepository.findById(theatreId).orElseThrow(() -> new ResourceNotFoundException("Theatre", "theatreId", theatreId));
    }

    public Theatre saveTheatre(Theatre theatre) {
        return theatreRepository.save(theatre);
    }

    public void deleteTheatre(int theatreId) {
        Theatre theatre = getTheatreById(theatreId);
        theatreRepository.delete(theatre);
    }
}
