package com.negoreserva.common.feature.concrete.province.exception.notfound;

import com.negoreserva.common.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProvinceNotFoundException extends NotFoundException {
    public ProvinceNotFoundException() {
        super("Province not found");
    }
    public ProvinceNotFoundException(String message) {
        super(message);
    }
    public ProvinceNotFoundException(UUID uuid) {
        super("Province not found by uuid %s".formatted(uuid));
    }
    public ProvinceNotFoundException(Long id) {
        super("Province not found by id %s".formatted(id));
    }
}
