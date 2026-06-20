package com.negoreserva.common.feature.concrete.organization.exception;

public class UserWithMoreThanOneActiveOrganizationException extends RuntimeException {
    public UserWithMoreThanOneActiveOrganizationException() {
        super("User with more than one active organization.");
    }

    public UserWithMoreThanOneActiveOrganizationException(String message) {
        super(message);
    }
}
