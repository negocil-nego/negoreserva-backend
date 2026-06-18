package com.negoreserva.common.feature.general.user_forget_password.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PasswordRecoveryProcessIsActiveException extends RuntimeException {

    public PasswordRecoveryProcessIsActiveException() {
        super("The password recovery process is active");
    }

    public PasswordRecoveryProcessIsActiveException(String message) {
        super(message);
    }
}
