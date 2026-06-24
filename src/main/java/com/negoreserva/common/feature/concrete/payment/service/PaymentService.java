package com.negoreserva.common.feature.concrete.payment.service;

import com.negoreserva.common.feature.concrete.payment.exception.notfound.PaymentNotFoundException;
import com.negoreserva.common.feature.concrete.payment.repository.PaymentRepo;
import com.negoreserva.common.feature.concrete.payment.model.Payment;
import com.negoreserva.common.feature.concrete.transaction.model.Transaction;
import com.negoreserva.common.feature.core.service.ConcreteService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PaymentService extends ConcreteService<Payment> {
    private final PaymentRepo repository;

    public PaymentService(PaymentRepo repository) {
        super(repository);
        this.repository = repository;
    }

    public boolean existsByTransaction_Code(String code) {
        return repository.findByTransaction_Code(code).isPresent();
    }

    public Payment findByTransaction_Code(String code) {
        return repository.findByTransaction_Code(code).orElseThrow(PaymentNotFoundException::new);
    }

    public List<Payment> findByTransaction(Transaction transaction) {
        return repository.findByTransaction(transaction);
    }

    public Payment findOrCreate(Payment payment) {
        return repository.findByTransaction_Code(payment.getTransaction().getCode()).orElseGet(() -> super.save(payment));
    }
}
