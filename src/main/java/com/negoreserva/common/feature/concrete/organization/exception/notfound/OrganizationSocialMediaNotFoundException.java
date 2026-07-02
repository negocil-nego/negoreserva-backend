package com.negoreserva.common.feature.concrete.organization.exception.notfound;

import com.negoreserva.common.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrganizationSocialMediaNotFoundException extends NotFoundException {
    public OrganizationSocialMediaNotFoundException() {
        super("OrganizationSocialMedia not found");
    }

    public OrganizationSocialMediaNotFoundException(String message) {
        super(message);
    }

    public OrganizationSocialMediaNotFoundException(UUID uuid) {
        super("OrganizationSocialMedia not found by uuid %s".formatted(uuid));
    }
}
