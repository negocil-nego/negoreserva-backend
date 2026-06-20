package com.negoreserva.common.feature.concrete.organization.exception;

public class ActiveUserWithoutOrganizationException extends RuntimeException {
    public ActiveUserWithoutOrganizationException() {
        super("User with more than one active organization.");
    }

    public ActiveUserWithoutOrganizationException(String message) {
        super(message);
    }
}
