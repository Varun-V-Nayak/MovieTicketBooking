package com.example.online_booking_new.controller;

import com.example.online_booking_new.loginrequest.AddMovieRequest;
import com.example.online_booking_new.loginrequest.LoginRequest;
import com.example.online_booking_new.model.Movie;
import com.example.online_booking_new.model.User;
import com.example.online_booking_new.repository.UserRepository;
import com.example.online_booking_new.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping("/addmovie")
    public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
        try {
            Movie savedMovie = movieService.addMovie(movie);
            return ResponseEntity.ok(savedMovie);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("exception"+e.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllMovies() {
        try {
            List<Movie> movies = movieService.getAllMovies();
            return ResponseEntity.ok(movies);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("exception"+e.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<?> getMovieById(@PathVariable Integer movieId) {
        try {
            Movie movie = movieService.getMovieById(movieId);
            return ResponseEntity.ok(movie);
        }catch (RuntimeException e){

            return new ResponseEntity<String>("Movie not found with id : "+movieId, HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("exception"+e.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<?> deleteMovieById(@PathVariable Integer movieId) {
        if(movieId>=1){
        try {
            movieService.deleteMovieById(movieId);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("exception" + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }else{
            return  new ResponseEntity<String>("Enter a valid movie id  ", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }

    @PutMapping("/updatemovie/{movieId}")
    public ResponseEntity<?> updateShow(@PathVariable Integer movieId, @RequestBody Movie movie) {
        if(movieId>=1) {
            try {
                Movie existingMovie = movieService.getMovieById(movieId);
                if (existingMovie != null) {
                    movie.setMovieId(movieId);
                    movieService.saveMovie(movie);
                    return ResponseEntity.ok().build();
                }
                return new ResponseEntity<String>("No movie existing with the provided id ", HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<String>("exception" + e.getMessage(), HttpStatus.NOT_FOUND);
            }
        }else{
            return  new ResponseEntity<String>("Enter a valid movie id  ", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }

    @Autowired
    UserRepository userRepository;

    @PostMapping("/admin/addmovie")
    public ResponseEntity<?> addMovie(@RequestBody AddMovieRequest addMovieRequest) {
        try {
            LoginRequest l = null;
            l = addMovieRequest.getLoginRequest();
            String phoneNumber;
            phoneNumber = l.getPhoneNumber();
            User user = null;
            user = (User) userRepository.findByPhoneNumber(phoneNumber);
            try {
                if (user.getAdmin() == true) {
                    Movie savedMovie = movieService.addMovie(addMovieRequest.getMovie());
                    return ResponseEntity.ok(savedMovie);
                } else {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                }
            } catch (NullPointerException e) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (DataIntegrityViolationException e) {

            ResponseEntity.status(HttpStatus.NO_CONTENT).body("enter a unique movie name");
            return new ResponseEntity<String>("enter a unique movie title", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
