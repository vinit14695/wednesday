package com.wednesday.assignment.relaxicab.controller.dto;

import com.wednesday.assignment.relaxicab.data.entity.NearbyDriver;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
public class DriverResponse extends CommonResponse {
    private List<NearbyDriver> drivers;

    @Builder
    public DriverResponse(int status, String message, List<NearbyDriver> drivers) {
        super(status, message);
        this.drivers = drivers;
    }
}
