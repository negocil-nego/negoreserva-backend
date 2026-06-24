package com.negoreserva.common.feature.concrete.payment.component;

import com.negoreserva.common.feature.concrete.payment.service.PaymentService;
import com.negoreserva.common.feature.concrete.transaction.model.Transaction;
import com.negoreserva.common.feature.concrete.payment.enums.PaymentData;
import com.negoreserva.common.feature.concrete.payment.model.Payment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentSeeder {
    private final PaymentService paymentService;

    @Setter
    private List<Transaction> transactions;

    @Transactional
    public List<Payment> seed() {
        List<Payment> items = new ArrayList<>();

        for (var transaction : transactions) {
            var existing = paymentService.findByTransaction(transaction);
            if (existing.isEmpty()) {
                var paymentTemplate = PaymentData.values()[0].getPayment();
                var it = Payment.builder()
                        .transaction(transaction)
                        .status(paymentTemplate.getStatus())
                        .type(paymentTemplate.getType())
                        .build();
                items.add(paymentService.save(it));
            }
        }
        return items;
    }
}
