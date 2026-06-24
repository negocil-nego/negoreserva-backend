package com.negoreserva.common.feature.concrete.payment.repository;

import com.negoreserva.common.feature.concrete.payment.model.Payment;
import com.negoreserva.common.feature.concrete.transaction.model.Transaction;
import com.negoreserva.common.feature.core.repository.ConcreteRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepo extends ConcreteRepository<Payment> {
    Optional<Payment> findByTransaction_Code(String transactionCode);
    List<Payment> findByTransaction(Transaction transaction);
}
