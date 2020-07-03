package com.wednesday.assignment.relaxicab.data.entity;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class NearbyDriver {
    private Driver driver;
    private double distanceFromLocation;
}
