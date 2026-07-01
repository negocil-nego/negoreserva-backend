package com.negoreserva.external.feature.payment.dto.response;

import com.negoreserva.common.feature.concrete.payment.enums.PaymentMethod;
import com.negoreserva.common.feature.concrete.payment.enums.PaymentStatus;
import com.negoreserva.common.feature.concrete.payment.model.Payment;

import java.util.UUID;

public record PaymentReceiptResponse(
        UUID uuid,
        UUID transactionUuid,
        PaymentStatus status,
        PaymentMethod type
) {
    public static PaymentReceiptResponse of(Payment payment) {
        return new PaymentReceiptResponse(
                payment.getUuid(),
                payment.getTransaction().getUuid(),
                payment.getStatus(),
                payment.getType()
        );
    }
}
