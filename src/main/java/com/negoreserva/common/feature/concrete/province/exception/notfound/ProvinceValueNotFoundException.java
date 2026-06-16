package com.negoreserva.common.feature.concrete.province.exception.notfound;

import com.negoreserva.common.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProvinceValueNotFoundException extends NotFoundException {
    public ProvinceValueNotFoundException() {
        super("Province value not found");
    }

    public ProvinceValueNotFoundException(String message) {
        super(message);
    }

    public ProvinceValueNotFoundException(UUID uuid) {
        super("Province value not found by uuid %s".formatted(uuid));
    }
}
