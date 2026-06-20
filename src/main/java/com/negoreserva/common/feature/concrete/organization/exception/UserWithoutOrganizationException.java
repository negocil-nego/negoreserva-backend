package com.negoreserva.common.feature.concrete.organization.exception;

public class UserWithoutOrganizationException extends RuntimeException {
    public UserWithoutOrganizationException() {
        super("User without organization");
    }

    public UserWithoutOrganizationException(String message) {
        super(message);
    }
}
