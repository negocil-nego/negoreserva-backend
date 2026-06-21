package com.negoreserva.internal.admin.feature.product_file.api.graphql;

import com.negoreserva.common.feature.concrete.product_file.dto.request.ProductFileRequest;
import com.negoreserva.common.feature.concrete.product_file.dto.response.ProductFilePaginate;
import com.negoreserva.common.feature.concrete.product_file.dto.response.ProductFileResponse;
import com.negoreserva.common.feature.concrete.product_file.service.ProductFileService;
import com.negoreserva.internal.admin.feature.permission.enums.AdminPermissionData;
import com.negoreserva.internal.admin.component.AdminControlAccess;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import jakarta.validation.Valid;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class AdminProductFileResolver {
    private final AdminControlAccess controlAccess;
    private final ProductFileService service;

    public AdminProductFileResolver(AdminControlAccess controlAccess, ProductFileService service) {
        this.controlAccess = controlAccess;
        this.service = service;
    }

    @QueryMapping
    public ProductFileResponse adminFindByUuidProductFile(@Argument UUID uuid, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_PRODUCT_FILE, authentication);
        return service.findByUuid(uuid).toResponse();
    }

    @QueryMapping
    public ProductFilePaginate adminPaginateProductFile(@Argument PaginateRequest paginateRequest, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_PRODUCT_FILE, authentication);
        return ProductFilePaginate.of(service.findAll(paginateRequest));
    }

    @MutationMapping
    public ProductFileResponse adminSaveProductFile(@Argument @Valid ProductFileRequest productFileRequest, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.CREATE_PRODUCT_FILE, authentication);
        return service.save(productFileRequest.toModel(), productFileRequest.productUuid()).toResponse();
    }

    @MutationMapping
    public ProductFileResponse adminUpdateProductFile(@Argument UUID uuid, @Argument @Valid ProductFileRequest productFileRequest, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.UPDATE_PRODUCT_FILE, authentication);
        return service.update(uuid, productFileRequest.toModel(), productFileRequest.productUuid()).toResponse();
    }

    @MutationMapping
    public boolean adminDeleteByUuidProductFile(@Argument UUID uuid, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.DELETE_PRODUCT_FILE, authentication);
        service.deleteByUuid(uuid);
        return true;
    }
}