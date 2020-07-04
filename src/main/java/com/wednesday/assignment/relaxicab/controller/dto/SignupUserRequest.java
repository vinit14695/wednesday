package com.wednesday.assignment.relaxicab.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class SignupUserRequest {

    @JsonProperty("first_name")
    @NotNull
    private String firstName = null;

    @JsonProperty("last_name")
    @NotNull
    private String lastName = null;

    @JsonProperty("user_name")
    @NotNull
    private String userName = null;

    @JsonProperty("email_address")
    @NotNull
    private String emailAddress = null;

    @NotNull
    private String password = null;

    @NotNull
    private String country = null;

    @NotNull
    private String dob = null;

    @JsonProperty("contact_number")
    @NotNull
    private String contactNumber = null;
}
