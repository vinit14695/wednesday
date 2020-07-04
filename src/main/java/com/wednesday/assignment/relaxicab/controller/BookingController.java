package com.wednesday.assignment.relaxicab.controller;

import com.wednesday.assignment.relaxicab.controller.dto.CabBookingRequest;
import com.wednesday.assignment.relaxicab.controller.dto.CabBookingResponse;
import com.wednesday.assignment.relaxicab.controller.dto.TripResponse;
import com.wednesday.assignment.relaxicab.data.entity.Location;
import com.wednesday.assignment.relaxicab.data.entity.Trip;
import com.wednesday.assignment.relaxicab.data.entity.User;
import com.wednesday.assignment.relaxicab.service.BookingBusinessService;
import com.wednesday.assignment.relaxicab.service.UserAuthBusinessService;
import com.wednesday.assignment.relaxicab.service.exception.AuthorizationFailedException;
import com.wednesday.assignment.relaxicab.service.exception.NoDriverAvailableException;
import com.wednesday.assignment.relaxicab.service.exception.NoTripFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingBusinessService bookingBusinessService;

    @Autowired
    private UserAuthBusinessService userAuthBusinessService;

    @PostMapping(path = "/cab", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CabBookingResponse> bookCab(CabBookingRequest bookingRequest, @RequestHeader final String authorization) throws AuthorizationFailedException, NoDriverAvailableException {
        //authorize user
        User user = userAuthBusinessService.getUser(authorization);

        Location from = Location.builder()
                .name(bookingRequest.getSourceName())
                .latitude(bookingRequest.getSourceLatitude())
                .longitude(bookingRequest.getSourceLongitude())
                .build();

        Location to = Location.builder()
                .name(bookingRequest.getDestinationName())
                .latitude(bookingRequest.getDestinationLatitude())
                .longitude(bookingRequest.getDestinationLongitude())
                .build();
        Trip bookedTrip = bookingBusinessService.bookCab(user, from, to);

        CabBookingResponse bookingResponse = CabBookingResponse.builder()
                .driverFirstName(bookedTrip.getDriver().getFirstName())
                .driverLastName(bookedTrip.getDriver().getLastName())
                .driverContactNumber(bookedTrip.getDriver().getContactNumber())
                .cabNumber(bookedTrip.getDriver().getCabNumber())
                .from(bookedTrip.getFrom())
                .to(bookedTrip.getTo())
                .predictedAmount(bookedTrip.getAmount())
                .bookedOn(bookedTrip.getBookedOn())
                .tripStatus(bookedTrip.getStatus())
                .distance(bookedTrip.getDistance())
                .status(0)
                .message("you cab has been booked!!!")
                .build();

        return new ResponseEntity<>(bookingResponse, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/get/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TripResponse> getBookings(@RequestHeader final String authorization) throws NoTripFoundException, AuthorizationFailedException {

        //authorize user
        User user = userAuthBusinessService.getUser(authorization);

        //get all trips of user
        List<Trip> userTrips = bookingBusinessService.getAllTrips(user);

        return new ResponseEntity<>(TripResponse.builder()
                .status(0)
                .message("Here is the list of trips booked by the user.")
                .trips(userTrips)
                .build(), HttpStatus.OK);
    }
}
