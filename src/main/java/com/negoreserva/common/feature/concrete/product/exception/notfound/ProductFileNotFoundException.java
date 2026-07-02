package com.negoreserva.common.feature.concrete.product.exception.notfound;

import com.negoreserva.common.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductFileNotFoundException extends NotFoundException {
    public ProductFileNotFoundException() {
        super("ProductFile not found");
    }

    public ProductFileNotFoundException(String message) {
        super(message);
    }

    public ProductFileNotFoundException(UUID uuid) {
        super("ProductFile not found by uuid %s".formatted(uuid));
    }
}