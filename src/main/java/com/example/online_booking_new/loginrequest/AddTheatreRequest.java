package com.example.online_booking_new.loginrequest;

import com.example.online_booking_new.model.Movie;
import com.example.online_booking_new.model.Theatre;
import com.example.online_booking_new.model.User;

public class AddTheatreRequest extends User {
    private Theatre theatre;
    private LoginRequest loginRequest;

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public LoginRequest getLoginRequest() {
        return loginRequest;
    }

    public void setLoginRequest(LoginRequest loginRequest) {
        this.loginRequest = loginRequest;
    }
}
