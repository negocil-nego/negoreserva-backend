package com.negoreserva.common.feature.concrete.payment.repository;

import com.negoreserva.common.feature.concrete.payment.model.PaymentFileReceipt;
import com.negoreserva.common.feature.core.repository.ConcreteRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentFileReceiptRepo extends ConcreteRepository<PaymentFileReceipt> {
}
