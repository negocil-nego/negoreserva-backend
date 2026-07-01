package com.negoreserva.common.feature.concrete.payment.enums;

import com.negoreserva.common.feature.concrete.payment.model.Payment;
import com.negoreserva.common.feature.concrete.transaction.enums.TransactionData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentData {
    L1(Payment.builder().transaction(TransactionData.L1.getTransaction()).type(PaymentMethod.REFERENCIA).status(PaymentStatus.PAID).build()),
    L2(Payment.builder().transaction(TransactionData.L2.getTransaction()).type(PaymentMethod.MULTCAIXA_EXPRESS).status(PaymentStatus.PAID).build()),
    L3(Payment.builder().transaction(TransactionData.L3.getTransaction()).type(PaymentMethod.REFERENCIA).status(PaymentStatus.PAID).build()),
    L4(Payment.builder().transaction(TransactionData.L4.getTransaction()).type(PaymentMethod.RECEIPT).status(PaymentStatus.PENDING).build()),
    L5(Payment.builder().transaction(TransactionData.L5.getTransaction()).type(PaymentMethod.REFERENCIA).status(PaymentStatus.PAID).build()),
    L6(Payment.builder().transaction(TransactionData.L6.getTransaction()).type(PaymentMethod.MULTCAIXA_EXPRESS).status(PaymentStatus.PAID).build()),
    L7(Payment.builder().transaction(TransactionData.L7.getTransaction()).type(PaymentMethod.RECEIPT).status(PaymentStatus.PAID).build()),
    L8(Payment.builder().transaction(TransactionData.L8.getTransaction()).type(PaymentMethod.MULTCAIXA_EXPRESS).status(PaymentStatus.PAID).build()),
    L9(Payment.builder().transaction(TransactionData.L9.getTransaction()).type(PaymentMethod.RECEIPT).status(PaymentStatus.RECEIPT_VALID).build()),
    L10(Payment.builder().transaction(TransactionData.L10.getTransaction()).type(PaymentMethod.RECEIPT).status(PaymentStatus.PAID).build()),
    L11(Payment.builder().transaction(TransactionData.L11.getTransaction()).type(PaymentMethod.REFERENCIA).status(PaymentStatus.PENDING).build()),
    L12(Payment.builder().transaction(TransactionData.L12.getTransaction()).type(PaymentMethod.RECEIPT).status(PaymentStatus.PAID).build()),
    L13(Payment.builder().transaction(TransactionData.L13.getTransaction()).type(PaymentMethod.REFERENCIA).status(PaymentStatus.PENDING).build()),
    L14(Payment.builder().transaction(TransactionData.L14.getTransaction()).type(PaymentMethod.MULTCAIXA_EXPRESS).status(PaymentStatus.PAID).build()),
    L15(Payment.builder().transaction(TransactionData.L15.getTransaction()).type(PaymentMethod.REFERENCIA).status(PaymentStatus.CANCELED).build()),
    L16(Payment.builder().transaction(TransactionData.L16.getTransaction()).type(PaymentMethod.MULTCAIXA_EXPRESS).status(PaymentStatus.PAID).build()),
    L17(Payment.builder().transaction(TransactionData.L17.getTransaction()).type(PaymentMethod.REFERENCIA).status(PaymentStatus.PAID).build()),
    L18(Payment.builder().transaction(TransactionData.L18.getTransaction()).type(PaymentMethod.RECEIPT).status(PaymentStatus.CANCELED).build());

    private final Payment payment;
}
