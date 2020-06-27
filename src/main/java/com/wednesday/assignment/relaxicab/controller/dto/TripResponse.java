package com.wednesday.assignment.relaxicab.controller.dto;

import com.wednesday.assignment.relaxicab.data.entity.Trip;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class TripResponse extends CommonResponse {

    private List<Trip> trips;

    @Builder
    public TripResponse(int status, String message, List<Trip> trips) {
        super(status, message);
        this.trips = trips;
    }
}
