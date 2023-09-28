package com.example.online_booking_new.service;

import com.example.online_booking_new.ResouceNotFoundException.ResourceNotFoundException;
import com.example.online_booking_new.model.Movie;
import com.example.online_booking_new.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
        @Autowired
        private MovieRepository movieRepository;


        public Movie addMovie(Movie movie) {
                return movieRepository.save(movie);
        }


        public List<Movie> getAllMovies() {
                return movieRepository.findAll();
        }


        public Movie getMovieById(Integer movieId) {
                try {
                        return movieRepository.findById(movieId)
                                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", movieId));
                } catch (ResourceNotFoundException e) {
                        throw new RuntimeException(e);
                }
        }
        public void saveMovie(Movie movie) {
                movieRepository.save(movie);
        }

        public void deleteMovieById(Integer movieId) {
                movieRepository.deleteById(movieId);
        }



        }
