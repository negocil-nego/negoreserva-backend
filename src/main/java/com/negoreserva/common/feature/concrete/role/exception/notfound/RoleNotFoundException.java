package com.negoreserva.common.feature.concrete.role.exception.notfound;

import com.negoreserva.common.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoleNotFoundException extends NotFoundException {
    public RoleNotFoundException() {
        super("Role not found");
    }

    public RoleNotFoundException(String message) {
        super(message);
    }

    public RoleNotFoundException(UUID uuid) {
        super("Role not found by uuid %s".formatted(uuid));
    }
}
