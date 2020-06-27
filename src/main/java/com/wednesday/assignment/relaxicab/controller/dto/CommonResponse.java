package com.wednesday.assignment.relaxicab.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonResponse {
    private int status;
    private String message;
}
