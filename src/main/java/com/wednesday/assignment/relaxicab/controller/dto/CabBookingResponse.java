package com.wednesday.assignment.relaxicab.controller.dto;

import com.wednesday.assignment.relaxicab.data.entity.Trip;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CabBookingResponse extends CommonResponse {

    private Trip trip;

    @Builder
    public CabBookingResponse(int status, String message, Trip trip) {
        super(status, message);
        this.trip = trip;
    }
}
