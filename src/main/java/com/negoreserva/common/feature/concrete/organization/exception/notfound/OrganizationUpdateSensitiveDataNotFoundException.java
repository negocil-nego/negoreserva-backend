package com.negoreserva.common.feature.concrete.organization.exception.notfound;

import com.negoreserva.common.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrganizationUpdateSensitiveDataNotFoundException extends NotFoundException {
    public OrganizationUpdateSensitiveDataNotFoundException() {
        super("OrganizationUpdateSensitiveData not found");
    }

    public OrganizationUpdateSensitiveDataNotFoundException(String message) {
        super(message);
    }

    public OrganizationUpdateSensitiveDataNotFoundException(long id) {
        super("OrganizationUpdateSensitiveData not found by id %d".formatted(id));
    }
}
