package com.negoreserva.common.feature.concrete.municipality.exception.notfound;

import com.negoreserva.common.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MunicipalityNotFoundException extends NotFoundException {
    public MunicipalityNotFoundException() {
        super("Municipality not found");
    }
    public MunicipalityNotFoundException(String message) {
        super(message);
    }
    public MunicipalityNotFoundException(UUID uuid) {
        super("Municipality not found by uuid %s".formatted(uuid));
    }
}
