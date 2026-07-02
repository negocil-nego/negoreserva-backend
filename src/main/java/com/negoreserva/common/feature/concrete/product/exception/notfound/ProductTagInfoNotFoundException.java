package com.negoreserva.common.feature.concrete.product.exception.notfound;

import com.negoreserva.common.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductTagInfoNotFoundException extends NotFoundException {
    public ProductTagInfoNotFoundException() {
        super("ProductTagInfo not found");
    }

    public ProductTagInfoNotFoundException(String message) {
        super(message);
    }

    public ProductTagInfoNotFoundException(UUID uuid) {
        super("ProductTagInfo not found by uuid %s".formatted(uuid));
    }
}
