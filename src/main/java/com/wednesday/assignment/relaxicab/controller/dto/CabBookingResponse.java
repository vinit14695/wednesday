package com.wednesday.assignment.relaxicab.controller.dto;

import com.wednesday.assignment.relaxicab.data.entity.Location;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class CabBookingResponse extends CommonResponse {

    private String driverFirstName;

    private String driverLastName;

    private String driverContactNumber;

    private String cabNumber;

    private Location from;

    private Location to;

    private Timestamp bookedOn;

    private String tripStatus;

    private long distance;

    private double predictedAmount;

    @Builder
    public CabBookingResponse(int status, String message, String driverFirstName, String driverLastName, String driverContactNumber, String cabNumber, Location from, Location to, Timestamp bookedOn, String tripStatus, long distance, double predictedAmount) {
        super(status, message);
        this.driverFirstName = driverFirstName;
        this.driverLastName = driverLastName;
        this.driverContactNumber = driverContactNumber;
        this.cabNumber = cabNumber;
        this.from = from;
        this.to = to;
        this.bookedOn = bookedOn;
        this.tripStatus = tripStatus;
        this.distance = distance;
        this.predictedAmount = predictedAmount;
    }
}
