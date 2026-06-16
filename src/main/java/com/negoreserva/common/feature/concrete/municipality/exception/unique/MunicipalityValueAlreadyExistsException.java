package com.negoreserva.common.feature.concrete.municipality.exception.unique;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class MunicipalityValueAlreadyExistsException extends RuntimeException {
    public MunicipalityValueAlreadyExistsException() {
        super("Municipality value already exists");
    }

    public MunicipalityValueAlreadyExistsException(String message) {
        super(message);
    }
}
