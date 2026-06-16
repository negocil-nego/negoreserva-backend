package com.negoreserva.common.exception;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Not Found");
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(UUID uuid) {
        super("Not found register by uuid %s".formatted(uuid));
    }
}
