package com.negoreserva.internal.admin.feature.plain.exception;

import com.negoreserva.common.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlanNotFoundException extends NotFoundException {
    public PlanNotFoundException() {
        super("Plan not found");
    }

    public PlanNotFoundException(String message) {
        super(message);
    }

    public PlanNotFoundException(UUID uuid) {
        super("Plan not found by uuid %s".formatted(uuid));
    }
}
