package com.negoreserva.internal.organization.feature.payment.dto.response;

import com.negoreserva.common.feature.concrete.payment.enums.PaymentMethod;
import com.negoreserva.common.feature.concrete.payment.enums.PaymentStatus;
import com.negoreserva.common.feature.concrete.payment.model.Payment;
import com.negoreserva.common.feature.concrete.payment.dto.response.PaymentFileReceiptResponse;
import com.negoreserva.common.feature.concrete.transaction.dto.response.TransactionResponse;

import java.util.UUID;

public record OrgPaymentResponse(
        UUID uuid,
        TransactionResponse transaction,
        PaymentStatus status,
        PaymentMethod type,
        PaymentFileReceiptResponse paymentFileReceipt
) {
    public static OrgPaymentResponse toResponse(Payment payment) {
        var receipts = payment.getPaymentFileReceipts();
        var receipt = receipts.isEmpty() ? null : receipts.getFirst();
        return new OrgPaymentResponse(
                payment.getUuid(),
                TransactionResponse.of(payment.getTransaction()),
                payment.getStatus(),
                payment.getType(),
                receipt != null ? PaymentFileReceiptResponse.of(receipt) : null
        );
    }
}
