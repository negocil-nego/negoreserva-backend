package com.negoreserva.common.feature.concrete.transaction.repository;

import com.negoreserva.common.feature.concrete.product.model.Product;
import com.negoreserva.common.feature.concrete.transaction.model.Transaction;
import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.common.feature.core.repository.ConcreteRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepo extends ConcreteRepository<Transaction> {
    Optional<Transaction> findByCode(String code);
    Optional<Transaction> findByUserAndProduct(User user, Product product);
}
