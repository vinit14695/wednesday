package com.wednesday.assignment.relaxicab.controller.exception;



import com.wednesday.assignment.relaxicab.controller.dto.ErrorResponse;
import com.wednesday.assignment.relaxicab.service.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler(SignUpRestrictedException.class)
    public ResponseEntity<ErrorResponse> signUpRestrictedException(SignUpRestrictedException exc, WebRequest request) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .code(exc.getCode())
                .message(exc.getErrorMessage())
                .build(),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<ErrorResponse> authenticationFailedException(AuthenticationFailedException exc, WebRequest request) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .code(exc.getCode())
                .message(exc.getErrorMessage())
                .build(),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SignOutRestrictedException.class)
    public ResponseEntity<ErrorResponse> signOutRestrictedException(SignOutRestrictedException exc, WebRequest request) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .code(exc.getCode())
                .message(exc.getErrorMessage())
                .build(),
                HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(AuthorizationFailedException.class)
    public ResponseEntity<ErrorResponse> authorizationFailedException(AuthorizationFailedException exc, WebRequest request) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .code(exc.getCode())
                .message(exc.getErrorMessage())
                .build(),
                HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(NoDriverAvailableException.class)
    public ResponseEntity<ErrorResponse> invalidQuestionException(NoDriverAvailableException exc, WebRequest request) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .code(exc.getCode())
                .message(exc.getErrorMessage())
                .build(),
                HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(NoTripFoundException.class)
    public ResponseEntity<ErrorResponse> answerNotFoundException(NoTripFoundException exc, WebRequest request) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .code(exc.getCode())
                .message(exc.getErrorMessage())
                .build(),
                HttpStatus.NOT_FOUND);
    }


}