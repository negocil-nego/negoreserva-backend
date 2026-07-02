package com.negoreserva.common.feature.concrete.user.exception.notfound;

import com.negoreserva.common.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserUpdateSensitiveDataNotFoundException extends NotFoundException {
    public UserUpdateSensitiveDataNotFoundException() {
        super("UserUpdateSensitiveDataNotFoundException not found");
    }

    public UserUpdateSensitiveDataNotFoundException(String message) {
        super(message);
    }

    public UserUpdateSensitiveDataNotFoundException(long id) {
        super("UserUpdateSensitiveDataNotFoundException not found by id %d".formatted(id));
    }
}
