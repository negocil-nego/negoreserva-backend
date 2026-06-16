package com.negoreserva.common.feature.concrete.product_file.repository;

import com.negoreserva.common.feature.concrete.product_file.model.ProductFile;
import com.negoreserva.common.feature.core.repository.ConcreteRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductFileRepo extends ConcreteRepository<ProductFile> {
    Optional<ProductFile> findByUuid(UUID uuid);
}
