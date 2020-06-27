package com.wednesday.assignment.relaxicab.controller;

import com.wednesday.assignment.relaxicab.controller.dto.TripResponse;
import com.wednesday.assignment.relaxicab.data.entity.Trip;
import com.wednesday.assignment.relaxicab.service.TripBusinessService;
import com.wednesday.assignment.relaxicab.service.exception.NoTripFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/trip")
public class TripController {

    @Autowired
    private TripBusinessService tripBusinessService;

    @GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TripResponse> getBookings() throws NoTripFoundException {


        List<Trip> userTrips = tripBusinessService.getAllTrips();

        return new ResponseEntity<>(TripResponse.builder()
                .status(0)
                .message("Success!!!")
                .trips(userTrips)
                .build(), HttpStatus.OK);
    }
}
