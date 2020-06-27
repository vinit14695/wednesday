package com.wednesday.assignment.relaxicab.controller.dto;

import com.wednesday.assignment.relaxicab.data.entity.Location;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CabBookingRequest {
    private Location from;
    private Location to;

}
