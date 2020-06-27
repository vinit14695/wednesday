package com.wednesday.assignment.relaxicab.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter




public class UserResponse extends CommonResponse {
    private long userId;

    @Builder
    public UserResponse(int status, String message, long userId) {
        super(status, message);
        this.userId = userId;
    }
}
