package com.wednesday.assignment.relaxicab.service;

import com.wednesday.assignment.relaxicab.data.entity.Driver;
import com.wednesday.assignment.relaxicab.data.entity.Location;
import com.wednesday.assignment.relaxicab.data.entity.NearbyDriver;
import com.wednesday.assignment.relaxicab.repository.DriverRepository;
import com.wednesday.assignment.relaxicab.service.exception.NoDriverAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class DriverBusinessService {

    @Autowired
    private DriverRepository driverRepository;

    public List<NearbyDriver> getNearbyDrivers(Location from) throws NoDriverAvailableException {

        List<Driver> availableDrivers = driverRepository.findByAvailableTrue();

        if (availableDrivers.isEmpty()) {
            throw new NoDriverAvailableException("DBS-001", "No Driver(s) is available.");
        }
        //TODO: write nearby logic here
        List<NearbyDriver> nearbyDrivers = new ArrayList<>();
        for (Driver driver : availableDrivers) {
            double distanceInMeters = calculateHarversinDistance(from.getLatitude(), from.getLongitude(), driver.getCurrentLocation().getLatitude(), driver.getCurrentLocation().getLongitude());
            nearbyDrivers.add(NearbyDriver.builder().driver(driver).distanceFromLocationInMeters(distanceInMeters).build());
        }
        nearbyDrivers.sort(new SortByDistance());

        return nearbyDrivers;
    }

    public double calculateHarversinDistance(double lat1, double lon1, double lat2, double lon2) {
        double distance;

        // distance between latitudes and longitudes
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // convert to radians
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        distance = (rad * c) * 1000;
        return distance;
    }

    public Driver updateDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    class SortByDistance implements Comparator<NearbyDriver> {
        // Used for sorting in ascending order of
        public int compare(NearbyDriver a, NearbyDriver b) {
            double doubleCompareValue = a.getDistanceFromLocationInMeters() - b.getDistanceFromLocationInMeters();
            return (int) doubleCompareValue;
        }
    }
}
