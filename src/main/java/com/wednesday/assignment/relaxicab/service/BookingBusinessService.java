package com.wednesday.assignment.relaxicab.service;

import com.wednesday.assignment.relaxicab.data.entity.*;
import com.wednesday.assignment.relaxicab.repository.TripRepository;
import com.wednesday.assignment.relaxicab.service.exception.NoDriverAvailableException;
import com.wednesday.assignment.relaxicab.service.exception.NoTripFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class BookingBusinessService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private DriverBusinessService driverBusinessService;

    @Value("${cab.rate.per.kilometer}")
    private int ratePerKilometer;

    public Trip bookCab(User user, Location from, Location to) throws NoDriverAvailableException {
        //get nearby driver;
        List<NearbyDriver> nearbyDrivers = driverBusinessService.getNearbyDrivers(from);
        Driver driver = nearbyDrivers.get(0).getDriver();

        //book driver
        driver.setAvailable(false);
        Driver bookedDriver = driverBusinessService.updateDriver(driver);


        //get distance
        long distance = (long) driverBusinessService.calculateHarversinDistance(from.getLatitude(), from.getLongitude(), to.getLatitude(), to.getLongitude());

        double amount = ratePerKilometer * (distance / 1000);

        //book a trip
        Trip bookedTrip = Trip.builder()
                .user(user)
                .driver(bookedDriver)
                .from(from)
                .to(to)
                .distance(distance)
                .amount(amount)
                .bookedOn(new Timestamp(System.currentTimeMillis()))
                .status("booked")
                .build();

        tripRepository.save(bookedTrip);

        return bookedTrip;
    }


    public List<Trip> getAllTrips(User user) throws NoTripFoundException {
        List<Trip> allTrips = tripRepository.findByUser(user, Sort.by(Sort.Direction.DESC, "bookedOn"));

        if (allTrips.isEmpty()) {
            throw new NoTripFoundException("TBS-001", "No Trip(s) is available.");
        }
        return allTrips;
    }


}
