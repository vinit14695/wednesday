package com.wednesday.assignment.relaxicab.service;

import com.wednesday.assignment.relaxicab.data.entity.Driver;
import com.wednesday.assignment.relaxicab.data.entity.Location;
import com.wednesday.assignment.relaxicab.data.entity.Trip;
import com.wednesday.assignment.relaxicab.data.entity.User;
import com.wednesday.assignment.relaxicab.repository.DriverRepository;
import com.wednesday.assignment.relaxicab.service.exception.NoDriverAvailableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CabBusinessService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private DriverBusinessService driverBusinessService;

    public Trip bookCab(User user, Location from, Location to) throws NoDriverAvailableException {
        //get nearby drivers;
        List<Driver> nearbyDrivers = driverBusinessService.getNearbyDrivers(from);


        return new Trip();
    }

}
