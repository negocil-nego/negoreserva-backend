package com.negoreserva.common.feature.concrete.user.exception;

public class UserOtpVerificationNotExpiredException extends RuntimeException {

    public UserOtpVerificationNotExpiredException() {
        super("User code not expired");
    }
}
