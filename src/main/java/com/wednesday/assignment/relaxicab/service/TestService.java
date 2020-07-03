package com.wednesday.assignment.relaxicab.service;

import com.wednesday.assignment.relaxicab.data.entity.Driver;
import com.wednesday.assignment.relaxicab.data.entity.Location;
import com.wednesday.assignment.relaxicab.repository.DriverRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

//@Service
@Slf4j
public class TestService {

    @Autowired
    private DriverRepository driverRepository;

    @PostConstruct
    public void init() {
        log.info("Inside init.");
        System.out.println("Inside");
        ArrayList<Driver> drivers = new ArrayList<>();

        drivers.add(Driver.builder()
                .firstName("Phoebe")
                .lastName("Buffay")
                .userName("Buffay.P")
                .currentLocation(Location.
                        builder()
                        .name("Indian Coffee House")
                        .latitude(21.1425)
                        .longitude(79.0607)
                        .build())
                .available(true)
                .cabNumber("MH31E234")
                .contactNumber("774400221144")
                .build());

        drivers.add(Driver.builder()
                .firstName("Mike")
                .lastName("Hannigan")
                .userName("Hannigan.M")
                .currentLocation(Location.
                        builder()
                        .name("Eternity Mall")
                        .latitude(21.1432)
                        .longitude(79.0803)
                        .build())
                .available(true)
                .cabNumber("MH49E274")
                .contactNumber("774400221144")
                .build());

        drivers.add(Driver.builder()
                .firstName("David")
                .lastName("Brook")
                .userName("Brook.D")
                .currentLocation(Location.
                        builder()
                        .name("Empress Mall")
                        .latitude(21.1480)
                        .longitude(79.0936)
                        .build())
                .available(true)
                .cabNumber("MH31G243")
                .contactNumber("774400221144")
                .build());
        //todo: remove this and use migration for oinserting data
        driverRepository.saveAll(drivers);

    }
}
