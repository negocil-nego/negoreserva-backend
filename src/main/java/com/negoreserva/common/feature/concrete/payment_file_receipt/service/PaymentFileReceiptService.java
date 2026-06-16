package com.negoreserva.common.feature.concrete.payment_file_receipt.service;

import com.negoreserva.common.feature.concrete.payment_file_receipt.model.PaymentFileReceipt;
import com.negoreserva.common.feature.concrete.payment_file_receipt.repository.PaymentFileReceiptRepo;
import com.negoreserva.common.feature.core.service.ConcreteService;
import org.springframework.stereotype.Service;

@Service
public class PaymentFileReceiptService extends ConcreteService<PaymentFileReceipt> {
    public PaymentFileReceiptService(PaymentFileReceiptRepo repository) {
        super(repository);
    }
}
