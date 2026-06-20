package com.negoreserva.internal.admin.feature.plain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlanNameNotFoundException extends PlanNotFoundException {
    public PlanNameNotFoundException() {
        super("Plan not found by name");
    }

    public PlanNameNotFoundException(String name) {
        super("Plan not found by name %s".formatted(name));
    }
}
