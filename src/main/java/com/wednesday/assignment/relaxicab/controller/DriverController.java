package com.wednesday.assignment.relaxicab.controller;

import com.wednesday.assignment.relaxicab.controller.dto.DriverResponse;
import com.wednesday.assignment.relaxicab.controller.dto.NearbyDriverRequest;
import com.wednesday.assignment.relaxicab.data.entity.Driver;
import com.wednesday.assignment.relaxicab.data.entity.Location;
import com.wednesday.assignment.relaxicab.data.entity.NearbyDriver;
import com.wednesday.assignment.relaxicab.service.DriverBusinessService;
import com.wednesday.assignment.relaxicab.service.exception.NoDriverAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverBusinessService driverBusinessService;

    @GetMapping(path = "/get/nearby", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DriverResponse> getNearbyDrivers(NearbyDriverRequest nearbyDriverRequest, @RequestHeader final String authorization) throws NoDriverAvailableException {

        Location location = Location.builder()
                .latitude(nearbyDriverRequest.getLatitude())
                .longitude(nearbyDriverRequest.getLongitude())
                .build();
        List<NearbyDriver> nearbyDrivers = driverBusinessService.getNearbyDrivers(location);

        return new ResponseEntity<>(DriverResponse.builder()
                .status(0)
                .message("Success!!!")
                .drivers(nearbyDrivers)
                .build(), HttpStatus.OK);
    }
}
