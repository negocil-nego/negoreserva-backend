package com.negoreserva.common.feature.concrete.role.exception.notfound;

import com.negoreserva.common.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoleCodeNotFoundException extends NotFoundException {
    public RoleCodeNotFoundException() {
        super("Role code not found");
    }

    public RoleCodeNotFoundException(String message) {
        super(message);
    }

    public RoleCodeNotFoundException(UUID uuid) {
        super("Role code not found by uuid %s".formatted(uuid));
    }
}
