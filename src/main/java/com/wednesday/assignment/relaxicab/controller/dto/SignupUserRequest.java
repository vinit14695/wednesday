package com.wednesday.assignment.relaxicab.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SignupUserRequest {

    @JsonProperty("first_name")
    private String firstName = null;

    @JsonProperty("last_name")
    private String lastName = null;

    @JsonProperty("user_name")
    private String userName = null;

    @JsonProperty("email_address")
    private String emailAddress = null;

    private String password = null;

    private String country = null;

    private String dob = null;

    @JsonProperty("contact_number")
    private String contactNumber = null;
}
