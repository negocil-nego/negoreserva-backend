package com.negoreserva.common.feature.concrete.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class OtpInvalidException extends RuntimeException {

    public OtpInvalidException() {
        super("OTP invalid");
    }
}
