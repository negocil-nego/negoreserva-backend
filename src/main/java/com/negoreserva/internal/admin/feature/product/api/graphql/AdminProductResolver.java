package com.negoreserva.internal.admin.feature.product.api.graphql;

import com.negoreserva.internal.admin.feature.product.dto.queryparam.ProductFilterQueryParam;
import com.negoreserva.internal.admin.feature.product.service.AdminProductService;
import com.negoreserva.common.feature.concrete.product.dto.request.ProductRequest;
import com.negoreserva.common.feature.concrete.product.dto.response.ProductPaginate;
import com.negoreserva.common.feature.concrete.product.dto.response.ProductResponse;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import jakarta.validation.Valid;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class AdminProductResolver {
    private final AdminProductService service;

    public AdminProductResolver(AdminProductService service) {
        this.service = service;
    }

    @QueryMapping
    public ProductResponse adminFindByUuidProduct(@Argument UUID uuid) {
        return service.findByUuid(uuid).toResponse();
    }

    @QueryMapping
    public ProductResponse adminFindByNameProduct(@Argument String name) {
        return service.findByName(name).toResponse();
    }

    @QueryMapping
    public ProductPaginate adminPaginateProduct(@Argument PaginateRequest paginateRequest) {
        return service.findAll(paginateRequest);
    }

    @QueryMapping
    public ProductPaginate adminPaginateProductFilter(@Argument ProductFilterQueryParam filter) {
        return service.findAll(filter);
    }

    @MutationMapping
    public ProductResponse adminSaveProduct(@Argument @Valid ProductRequest productRequest) {
        return service.saveWithOrganization(productRequest.toModel(), productRequest.organizationUuid()).toResponse();
    }

    @MutationMapping
    public ProductResponse adminUpdateProduct(@Argument UUID uuid, @Argument @Valid ProductRequest productRequest) {
        return service.updateWithOrganization(uuid, productRequest.toModel(), productRequest.organizationUuid()).toResponse();
    }

    @MutationMapping
    public boolean adminDeleteByUuidProduct(@Argument UUID uuid) {
        service.deleteByUuid(uuid);
        return true;
    }
}