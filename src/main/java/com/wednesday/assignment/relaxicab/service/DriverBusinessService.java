package com.wednesday.assignment.relaxicab.service;

import com.wednesday.assignment.relaxicab.data.entity.Driver;
import com.wednesday.assignment.relaxicab.data.entity.Location;
import com.wednesday.assignment.relaxicab.repository.DriverRepository;
import com.wednesday.assignment.relaxicab.service.exception.NoDriverAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverBusinessService {

    @Autowired
    private DriverRepository driverRepository;

    public List<Driver> getNearbyDrivers(Location from) throws NoDriverAvailableException {

        List<Driver> nearbyDrivers = driverRepository.findByAvailableTrue();

        if (nearbyDrivers.isEmpty()) {
            throw new NoDriverAvailableException("DBS-001", "No Driver(s) is available.");
        }
        //TODO: write nearby logic here


        return nearbyDrivers;
    }
}
