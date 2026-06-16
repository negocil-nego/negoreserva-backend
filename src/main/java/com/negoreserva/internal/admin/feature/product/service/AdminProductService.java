package com.negoreserva.internal.admin.feature.product.service;

import com.negoreserva.internal.admin.feature.organization.service.AdminOrganizationService;
import com.negoreserva.common.feature.concrete.product.dto.response.ProductPaginate;
import com.negoreserva.common.feature.concrete.product.exception.notfound.ProductNameNotFoundException;
import com.negoreserva.common.feature.concrete.product.exception.notfound.ProductNotFoundException;
import com.negoreserva.internal.admin.feature.product.dto.queryparam.ProductFilterQueryParam;
import com.negoreserva.internal.admin.feature.product.query.ProductFilterSpecification;
import com.negoreserva.common.feature.concrete.product.repository.ProductRepository;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.product.model.Product;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.core.service.ConcreteService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AdminProductService extends ConcreteService<Product> {
    private final AdminOrganizationService adminOrganizationService;
    private final ProductRepository productRepository;

    public AdminProductService(ProductRepository productRepository, AdminOrganizationService adminOrganizationService) {
        super(productRepository);
        this.adminOrganizationService = adminOrganizationService;
        this.productRepository = productRepository;
    }

    public ProductPaginate paginate(Pageable pageable) {
        var page = productRepository.findAll(pageable);
        return ProductPaginate.of(page);
    }

    public ProductPaginate paginate(PaginateRequest paginateRequest) {
        return paginate(PageRequest.of(paginateRequest.pageNumber(), paginateRequest.pageSize()));
    }

    public ProductPaginate paginate(ProductFilterQueryParam filter) {
        var pageRequest = PageRequest.of(
                Optional.of(filter.getPageNumber()).orElse(0),
                Optional.of(filter.getPageSize()).orElse(10)
        );
        var spec = new ProductFilterSpecification(filter);
        var page = findAll(spec, pageRequest);
        return ProductPaginate.of(page);
    }

    public Product findByName(String name) {
        return productRepository.findByName(name).orElseThrow(() -> new ProductNameNotFoundException(name));
    }

    @Override
    public Product findByUuid(UUID uuid) {
        return productRepository.findByUuid(uuid).orElseThrow(() -> new ProductNotFoundException(uuid));
    }

    public Product saveWithOrganization(Product product, UUID organizationUuid) {
        if (organizationUuid != null) {
            Organization organization = adminOrganizationService.findByUuid(organizationUuid);
            product.setOrganization(organization);
        }
        return save(product);
    }

    public Product updateWithOrganization(UUID uuid, Product product, UUID organizationUuid) {
        var item = findByUuid(uuid);
        item.setDescription(product.getDescription());
        item.setName(product.getName());
        
        if (organizationUuid != null) {
            Organization organization = adminOrganizationService.findByUuid(organizationUuid);
            item.setOrganization(organization);
        }
        
        return productRepository.save(item);
    }

    @Override
    public Product update(UUID uuid, Product product) {
        var item = findByUuid(uuid);
        item.setDescription(product.getDescription());
        item.setName(product.getName());
        return productRepository.save(item);
    }

}