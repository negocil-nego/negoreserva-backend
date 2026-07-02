package com.negoreserva.common.feature.concrete.payment.exception.notfound;

import com.negoreserva.common.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PaymentFileReceiptNotFoundException extends NotFoundException {
    public PaymentFileReceiptNotFoundException() { super("Payment file receipt not found"); }
    public PaymentFileReceiptNotFoundException(String message) { super(message); }
    public PaymentFileReceiptNotFoundException(UUID uuid) { super("Payment file receipt not found by uuid %s".formatted(uuid)); }
}
