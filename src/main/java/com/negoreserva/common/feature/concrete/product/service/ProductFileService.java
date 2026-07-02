package com.negoreserva.common.feature.concrete.product.service;

import com.negoreserva.common.feature.concrete.product.service.ProductService;
import com.negoreserva.common.feature.concrete.product.dto.response.ProductFilePaginate;
import com.negoreserva.common.feature.concrete.product.repository.ProductFileRepo;
import com.negoreserva.common.feature.concrete.product.model.Product;
import com.negoreserva.common.feature.concrete.product.exception.notfound.ProductFileNotFoundException;
import com.negoreserva.common.feature.concrete.product.model.ProductFile;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.core.service.ConcreteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductFileService extends ConcreteService<ProductFile> {
    private final ProductFileRepo repository;
    private final ProductService productService;

    public ProductFileService(
            ProductFileRepo repository,
            ProductService productService
    ) {
        super(repository);
        this.repository = repository;
        this.productService = productService;
    }

    public Page<ProductFile> findAll(PaginateRequest paginateRequest) {
        return findAll(PageRequest.of(paginateRequest.pageNumber(), paginateRequest.pageSize()));
    }

    @Override
    public ProductFile findByUuid(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(() -> new ProductFileNotFoundException(uuid));
    }

    public ProductFile save(ProductFile productFile, UUID productUuid) {
        if (productUuid != null) {
            Product product = productService.findByUuid(productUuid);
            productFile.setProduct(product);
        }
        return super.save(productFile);
    }

    public ProductFile update(UUID uuid, ProductFile productFile, UUID productUuid) {
        var item = findByUuid(uuid);
        item.setTitle(productFile.getTitle());
        item.setUrl(productFile.getUrl());
        item.setType(productFile.getType());

        if (productUuid != null) {
            Product product = productService.findByUuid(productUuid);
            item.setProduct(product);
        }

        return repository.save(item);
    }

    @Override
    public ProductFile update(UUID uuid, ProductFile productFile) {
        var item = findByUuid(uuid);
        item.setTitle(productFile.getTitle());
        item.setUrl(productFile.getUrl());
        item.setType(productFile.getType());
        return repository.save(item);
    }
}
