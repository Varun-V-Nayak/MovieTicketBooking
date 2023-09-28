package com.example.online_booking_new.loginrequest;

import com.example.online_booking_new.model.Movie;
import com.example.online_booking_new.model.User;

public class AddMovieRequest extends User {
    private Movie movie;
    private LoginRequest loginRequest;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LoginRequest getLoginRequest() {
        return loginRequest;
    }

    public void setLoginRequest(LoginRequest loginRequest) {
        this.loginRequest = loginRequest;
    }
}
