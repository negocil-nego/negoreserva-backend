package com.negoreserva.common.feature.concrete.role.exception.notfound;

import com.negoreserva.common.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoleNameNotFoundException extends NotFoundException {
    public RoleNameNotFoundException() {
        super("Role name not found");
    }

    public RoleNameNotFoundException(String message) {
        super(message);
    }

    public RoleNameNotFoundException(UUID uuid) {
        super("Role name not found by uuid %s".formatted(uuid));
    }
}
