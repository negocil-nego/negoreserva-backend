package com.negoreserva.internal.admin.feature.product.api.graphql;

import com.negoreserva.internal.admin.feature.permission.enums.AdminPermissionData;
import com.negoreserva.internal.admin.feature.product.dto.queryparam.ProductFilterQueryParam;
import com.negoreserva.internal.admin.feature.product.service.AdminProductService;
import com.negoreserva.internal.admin.component.AdminControlAccess;
import com.negoreserva.common.feature.concrete.product.dto.request.ProductRequest;
import com.negoreserva.common.feature.concrete.product.dto.response.ProductPaginate;
import com.negoreserva.common.feature.concrete.product.dto.response.ProductResponse;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import jakarta.validation.Valid;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class AdminProductResolver {
    private final AdminControlAccess controlAccess;
    private final AdminProductService service;

    public AdminProductResolver(AdminControlAccess controlAccess, AdminProductService service) {
        this.controlAccess = controlAccess;
        this.service = service;
    }

    @QueryMapping
    public ProductResponse adminFindByUuidProduct(@Argument UUID uuid, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_PRODUCT, authentication);
        return service.findByUuid(uuid).toResponse();
    }

    @QueryMapping
    public ProductResponse adminFindByNameProduct(@Argument String name, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_PRODUCT, authentication);
        return service.findByName(name).toResponse();
    }

    @QueryMapping
    public ProductPaginate adminPaginateProduct(@Argument PaginateRequest paginateRequest, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_PRODUCT, authentication);
        return ProductPaginate.of(service.findAll(paginateRequest));
    }

    @QueryMapping
    public ProductPaginate adminPaginateProductFilter(@Argument ProductFilterQueryParam filter, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_PRODUCT, authentication);
        return ProductPaginate.of(service.findAll(filter));
    }

    @MutationMapping
    public ProductResponse adminSaveProduct(@Argument @Valid ProductRequest productRequest, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.CREATE_PRODUCT, authentication);
        return service.saveWithOrganization(productRequest.toModel(), productRequest.organizationUuid()).toResponse();
    }

    @MutationMapping
    public ProductResponse adminUpdateProduct(@Argument UUID uuid, @Argument @Valid ProductRequest productRequest, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.UPDATE_PRODUCT, authentication);
        return service.updateWithOrganization(uuid, productRequest.toModel(), productRequest.organizationUuid()).toResponse();
    }

    @MutationMapping
    public boolean adminDeleteByUuidProduct(@Argument UUID uuid, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.DELETE_PRODUCT, authentication);
        service.deleteByUuid(uuid);
        return true;
    }
}