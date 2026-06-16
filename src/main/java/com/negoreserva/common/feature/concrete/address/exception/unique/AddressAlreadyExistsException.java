package com.negoreserva.common.feature.concrete.address.exception.unique;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AddressAlreadyExistsException extends RuntimeException {
    public AddressAlreadyExistsException() {
        super("Address already exists");
    }

    public AddressAlreadyExistsException(String message) {
        super(message);
    }
}
