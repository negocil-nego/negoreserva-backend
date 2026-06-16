package com.negoreserva.common.feature.concrete.product.service;

import com.negoreserva.common.feature.concrete.product.dto.queryparam.ProductSearchFilterParam;
import com.negoreserva.common.feature.concrete.product.dto.response.ProductResponse;
import com.negoreserva.common.feature.concrete.product.exception.notfound.ProductNotFoundException;
import com.negoreserva.common.feature.concrete.product.exception.notfound.ProductSlugNotFoundException;
import com.negoreserva.common.feature.concrete.product.repository.ProductRepository;
import com.negoreserva.common.feature.core.service.ConcreteService;
import com.negoreserva.common.feature.concrete.product.model.Product;
import com.negoreserva.common.util.RegexValidators;
import com.negoreserva.common.feature.concrete.product.query.ProductSearchSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService extends ConcreteService<Product> {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        super(productRepository);
        this.productRepository = productRepository;
    }

    @Override
    public Product findByUuid(UUID uuid) {
        return productRepository.findByUuid(uuid).orElseThrow(() -> new ProductNotFoundException(uuid));
    }

    public Product findBySlug(String slug) {
        return productRepository.findBySlug(slug).orElseThrow(() -> new ProductSlugNotFoundException(slug));
    }

    public Product findByUuidOrSlug(String uuidOrSlug) {
        return RegexValidators.isUuid(uuidOrSlug) ? findByUuid(UUID.fromString(uuidOrSlug)) : findBySlug(uuidOrSlug);
    }

    public Product findOrCreate(Product product) {
        return productRepository.findByName(product.getName()).orElseGet(() -> save(product));
    }

    public Page<ProductResponse> search(String query, Pageable pageable) {
        return productRepository.findByLikeContact(query, pageable)
                .map(Product::toResponse);
    }

    public Page<ProductResponse> search(ProductSearchFilterParam filter, Pageable pageable) {
        var spec = new ProductSearchSpecification(filter);
        return productRepository.findAll(spec, pageable)
                .map(Product::toResponse);
    }
}