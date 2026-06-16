package com.negoreserva.common.feature.concrete.province.exception.unique;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ProvinceValueAlreadyExistsException extends RuntimeException {
    public ProvinceValueAlreadyExistsException() {
        super("Province value already exists");
    }

    public ProvinceValueAlreadyExistsException(String message) {
        super(message);
    }
}
