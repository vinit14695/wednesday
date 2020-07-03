package com.wednesday.assignment.relaxicab.controller;


import com.wednesday.assignment.relaxicab.controller.dto.SignupUserRequest;
import com.wednesday.assignment.relaxicab.controller.dto.UserResponse;
import com.wednesday.assignment.relaxicab.data.entity.User;
import com.wednesday.assignment.relaxicab.data.entity.UserAuthentication;
import com.wednesday.assignment.relaxicab.service.UserBusinessService;
import com.wednesday.assignment.relaxicab.service.exception.AuthenticationFailedException;
import com.wednesday.assignment.relaxicab.service.exception.GenericException;
import com.wednesday.assignment.relaxicab.service.exception.SignOutRestrictedException;
import com.wednesday.assignment.relaxicab.service.exception.SignUpRestrictedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserBusinessService userBusinessService;

    @RequestMapping(path = "/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserResponse> userSignUp(SignupUserRequest signupUserRequest) throws SignUpRestrictedException {
        User user = User.builder()
                .firstName(signupUserRequest.getFirstName())
                .lastName(signupUserRequest.getLastName())
                .userName(signupUserRequest.getUserName())
                .password(signupUserRequest.getPassword())
                .dob(signupUserRequest.getDob())
                .country(signupUserRequest.getCountry())
                .email(signupUserRequest.getEmailAddress())
                .contactNumber(signupUserRequest.getContactNumber())
                .role("customer")
                .build();

        User createdUserEntity = userBusinessService.createUser(user);
        UserResponse userResponse = UserResponse.builder().userId(createdUserEntity.getId()).message("USER SUCCESSFULLY REGISTERED").build();
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }


    @RequestMapping(path = "/signin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> userSignIn(@RequestHeader("authorization") final String authorization) throws AuthenticationFailedException, GenericException {
        byte[] decoded = Base64.getDecoder().decode(authorization.split("Basic ")[1]);
        String decodedAuth = new String(decoded);
        String[] decodedArray = decodedAuth.split(":");

        UserAuthentication userAuthentication = userBusinessService.authenticateUser(decodedArray[0], decodedArray[1]);

        UserResponse signinResponse = UserResponse.builder().userId(userAuthentication.getId()).message("SIGNED IN SUCCESSFULLY").build();
        HttpHeaders headers = new HttpHeaders();
        headers.add("access_token", userAuthentication.getAccessToken());
        return new ResponseEntity<>(signinResponse, headers, HttpStatus.OK);
    }


    @RequestMapping(path = "/signout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> userSignOut(@RequestHeader("authorization") final String authorization) throws SignOutRestrictedException {
        UserAuthentication userAuthentication = userBusinessService.signOut(authorization);
        User userEntity = userAuthentication.getUser();
        UserResponse signoutResponse = UserResponse.builder().userId(userEntity.getId()).message("SIGNED OUT SUCCESSFULLY").build();
        return new ResponseEntity<>(signoutResponse, HttpStatus.OK);
    }
}
