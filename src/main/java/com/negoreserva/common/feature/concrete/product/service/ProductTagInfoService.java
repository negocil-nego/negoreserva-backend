package com.negoreserva.common.feature.concrete.product.service;

import com.negoreserva.common.feature.core.service.ConcreteService;
import com.negoreserva.common.feature.concrete.product.model.ProductTagInfo;
import com.negoreserva.common.feature.concrete.product.repository.ProductTagInfoRepo;
import org.springframework.stereotype.Service;

@Service
public class ProductTagInfoService extends ConcreteService<ProductTagInfo> {
    private final ProductTagInfoRepo repository;

    public ProductTagInfoService(ProductTagInfoRepo repository) {
        super(repository);
        this.repository = repository;
    }

    public ProductTagInfo save(ProductTagInfo productTagInfo) {
        return repository.save(productTagInfo);
    }
}
