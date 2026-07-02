package com.negoreserva.common.feature.concrete.product.service;

import com.negoreserva.common.feature.concrete.product.repository.ProductPriceRepo;
import com.negoreserva.common.feature.concrete.product.exception.notfound.ProductPriceNotFoundException;
import com.negoreserva.common.feature.concrete.product.model.ProductPrice;
import com.negoreserva.common.feature.core.service.ConcreteService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductPriceService extends ConcreteService<ProductPrice> {
    private final ProductPriceRepo repository;

    public ProductPriceService(ProductPriceRepo repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public ProductPrice findByUuid(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(() -> new ProductPriceNotFoundException(uuid));
    }
}
