package com.wednesday.assignment.relaxicab.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NearbyDriverRequest {
    @NotNull
    double latitude;

    @NotNull
    double longitude;
}
