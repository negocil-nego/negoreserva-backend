package com.negoreserva.common.feature.concrete.user.exception;

public class UserOtpVerificationConfirmException extends RuntimeException {

    public UserOtpVerificationConfirmException() {
        super("User code verification");
    }
}
