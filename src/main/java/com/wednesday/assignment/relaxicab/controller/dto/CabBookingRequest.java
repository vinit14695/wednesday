package com.wednesday.assignment.relaxicab.controller.dto;

import com.wednesday.assignment.relaxicab.data.entity.Location;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CabBookingRequest {

    @NotNull
    String sourceName;

    @NotNull
    double sourceLatitude;

    @NotNull
    double sourceLongitude;

    @NotNull
    String destinationName;

    @NotNull
    double destinationLatitude;

    @NotNull
    double destinationLongitude;

}
