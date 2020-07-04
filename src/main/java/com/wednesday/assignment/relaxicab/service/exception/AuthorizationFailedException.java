package com.wednesday.assignment.relaxicab.service.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.PrintStream;
import java.io.PrintWriter;

@Getter
@Setter
public class AuthorizationFailedException extends Exception {
    private final String code;
    private final String errorMessage;

    @Builder
    public AuthorizationFailedException(final String code, final String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }

    @Override
    public void printStackTrace(PrintStream s) {
        super.printStackTrace(s);
    }

    @Override
    public void printStackTrace(PrintWriter s) {
        super.printStackTrace(s);
    }

}
