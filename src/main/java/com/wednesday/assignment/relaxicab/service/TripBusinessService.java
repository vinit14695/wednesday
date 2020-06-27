package com.wednesday.assignment.relaxicab.service;

import com.wednesday.assignment.relaxicab.data.entity.Trip;
import com.wednesday.assignment.relaxicab.repository.TripRepository;
import com.wednesday.assignment.relaxicab.service.exception.NoTripFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripBusinessService {

    @Autowired
    private TripRepository tripRepository;

    public List<Trip> getAllTrips() throws NoTripFoundException {
        List<Trip> allTrips = tripRepository.findAll();

        if (allTrips.isEmpty()) {
            throw new NoTripFoundException("TBS-001", "No Trip(s) is available.");
        }
        return allTrips;
    }
}
