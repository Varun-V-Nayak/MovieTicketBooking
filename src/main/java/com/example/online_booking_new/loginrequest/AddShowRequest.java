package com.example.online_booking_new.loginrequest;

import com.example.online_booking_new.model.Shows;
import com.example.online_booking_new.model.Theatre;
import com.example.online_booking_new.model.User;

public class AddShowRequest extends User {
    private Shows shows;
    private LoginRequest loginRequest;

    public Shows getShows() {
        return shows;
    }

    public void setShows(Shows shows) {
        this.shows = shows;
    }

    public LoginRequest getLoginRequest() {
        return loginRequest;
    }

    public void setLoginRequest(LoginRequest loginRequest) {
        this.loginRequest = loginRequest;
    }
}
