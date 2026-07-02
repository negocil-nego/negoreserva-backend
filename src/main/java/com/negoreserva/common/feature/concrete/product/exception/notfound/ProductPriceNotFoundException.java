package com.negoreserva.common.feature.concrete.product.exception.notfound;

import com.negoreserva.common.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductPriceNotFoundException extends NotFoundException {
    public ProductPriceNotFoundException() {
        super("ProductPrice not found");
    }

    public ProductPriceNotFoundException(String message) {
        super(message);
    }

    public ProductPriceNotFoundException(UUID uuid) {
        super("ProductPrice not found by uuid %s".formatted(uuid));
    }
}
