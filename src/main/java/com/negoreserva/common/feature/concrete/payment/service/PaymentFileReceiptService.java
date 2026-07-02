package com.negoreserva.common.feature.concrete.payment.service;

import com.negoreserva.common.feature.concrete.payment.model.PaymentFileReceipt;
import com.negoreserva.common.feature.concrete.payment.repository.PaymentFileReceiptRepo;
import com.negoreserva.common.feature.core.service.ConcreteService;
import org.springframework.stereotype.Service;

@Service
public class PaymentFileReceiptService extends ConcreteService<PaymentFileReceipt> {
    public PaymentFileReceiptService(PaymentFileReceiptRepo repository) {
        super(repository);
    }
}
