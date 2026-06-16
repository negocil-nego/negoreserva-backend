package com.negoreserva.common.feature.concrete.transaction.exception.unique;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TransactionAlreadyExistsException extends RuntimeException {
    public TransactionAlreadyExistsException() {
        super("Transaction already exists");
    }

    public TransactionAlreadyExistsException(String message) {
        super(message);
    }
}
