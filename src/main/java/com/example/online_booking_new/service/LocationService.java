package com.example.online_booking_new.service;

import com.example.online_booking_new.model.Location;
import com.example.online_booking_new.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public Location createLocation(String name, String address) {
        Location location = new Location(name, address);
        return locationRepository.save(location);
    }

    public Location getLocation(int id) {
        return locationRepository.findById(id).orElse(null);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location updateLocation(int id, String name, String address) {
        Location location = getLocation(id);
        if (location == null) {
            return null;
        }
        location.setName(name);
        location.setAddress(address);
        return locationRepository.save(location);
    }

    public void deleteLocation(int id) {
        locationRepository.deleteById(id);
    }

}
