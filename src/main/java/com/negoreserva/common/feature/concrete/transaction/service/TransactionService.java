package com.negoreserva.common.feature.concrete.transaction.service;

import com.negoreserva.common.feature.concrete.transaction.dto.response.TransactionPaginate;
import com.negoreserva.common.feature.concrete.transaction.exception.notfound.TransactionNotFoundException;
import com.negoreserva.common.feature.concrete.transaction.repository.TransactionRepo;
import com.negoreserva.common.feature.concrete.transaction.model.Transaction;
import com.negoreserva.common.feature.core.service.ConcreteService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService extends ConcreteService<Transaction> {
    private final TransactionRepo repository;

    public TransactionService(TransactionRepo repository) {
        super(repository);
        this.repository = repository;
    }


    @Override
    public Transaction findByUuid(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(() -> new TransactionNotFoundException(uuid));
    }
}
