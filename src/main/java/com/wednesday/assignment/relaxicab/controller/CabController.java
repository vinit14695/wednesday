package com.wednesday.assignment.relaxicab.controller;

import com.wednesday.assignment.relaxicab.controller.dto.CabBookingRequest;
import com.wednesday.assignment.relaxicab.controller.dto.CabBookingResponse;
import com.wednesday.assignment.relaxicab.data.entity.Trip;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cab")
public class CabController {

    @PostMapping(path = "/book", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CabBookingResponse> bookCab(CabBookingRequest bookingRequest, @RequestHeader String authorization) {


        return new ResponseEntity<>(CabBookingResponse
                .builder()
                .trip(new Trip())
                .message("void")
                .status(0).build(), HttpStatus.ACCEPTED);
    }
}
