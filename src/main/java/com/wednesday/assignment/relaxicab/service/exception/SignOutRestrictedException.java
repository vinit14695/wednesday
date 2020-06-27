package com.wednesday.assignment.relaxicab.service.exception;

import lombok.Builder;
import lombok.Getter;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * SignOutRestrictedException is thrown when a user is not signed in the application and tries to sign out of the application.
 */
@Getter
public class SignOutRestrictedException extends Exception {
    private final String code;
    private final String errorMessage;

    @Builder
    public SignOutRestrictedException(final String code, final String errorMessage) {
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
