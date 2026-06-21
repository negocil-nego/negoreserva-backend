package com.negoreserva.internal.organization.feature.catalog.api.graphql;

import com.negoreserva.internal.organization.feature.catalog.dto.request.CatalogProductOrderInput;
import com.negoreserva.internal.organization.feature.catalog.dto.request.OrgCatalogUpdateRequest;
import com.negoreserva.internal.organization.feature.catalog.dto.response.OrgCatalogPaginate;
import com.negoreserva.internal.organization.feature.catalog.dto.response.OrgCatalogResponse;
import com.negoreserva.internal.organization.feature.catalog.dto.queryparam.CatalogFilterQueryParam;
import com.negoreserva.internal.organization.feature.catalog.service.OrgCatalogService;
import com.negoreserva.internal.organization.feature.permission.enums.OrgPermissionData;
import com.negoreserva.internal.organization.feature.product.dto.response.OrgProductPaginate;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.internal.organization.componet.OrgControlAccess;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class OrgCatalogGraphql {
    private final OrgControlAccess controlAccess;
    private final OrgCatalogService service;

    @QueryMapping
    public OrgCatalogResponse orgFindByUuidCatalog(@Argument UUID uuid, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_ORGANIZATION, authentication);
        return OrgCatalogResponse.toResponse(service.findByUuid(uuid));
    }

    @QueryMapping
    public OrgCatalogResponse orgFindByUuidOrSlugCatalog(@Argument String uuidOrSlug, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_ORGANIZATION, authentication);
        return OrgCatalogResponse.toResponse(service.findByUuidOrSlug(uuidOrSlug));
    }

    @QueryMapping
    public OrgCatalogPaginate orgPaginateCatalog(@Argument PaginateRequest paginateRequest, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_ORGANIZATION, authentication);
        return service.paginate(paginateRequest, authentication);
    }

    @QueryMapping
    public OrgCatalogPaginate orgPaginateCatalogFilter(@Argument CatalogFilterQueryParam filter, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_ORGANIZATION, authentication);
        return service.paginate(filter, authentication);
    }

    @QueryMapping
    public OrgProductPaginate orgPaginateCatalogProducts(@Argument String uuidOrSlug, @Argument PaginateRequest paginateRequest, Authentication authentication) {
        var pageable = PageRequest.of(paginateRequest.pageNumber(), paginateRequest.pageSize());
        return service.paginateCatalogProducts(uuidOrSlug, pageable, authentication);
    }

    @QueryMapping
    public OrgProductPaginate orgPaginateCatalogProductsNotIn(@Argument String uuidOrSlug, @Argument PaginateRequest paginateRequest, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_ORGANIZATION, authentication);
        var pageable = PageRequest.of(paginateRequest.pageNumber(), paginateRequest.pageSize());
        return service.paginateProductsNotInCatalog(uuidOrSlug, pageable, authentication);
    }

    @MutationMapping
    public boolean orgAddProductsToCatalog(@Argument String uuidOrSlug, @Argument List<CatalogProductOrderInput> products, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.CREATE_CATALOG_PRODUCT, authentication);
        service.addProductsToCatalog(uuidOrSlug, products, authentication);
        return true;
    }

    @MutationMapping
    public boolean orgRemoveProductsFromCatalog(@Argument String uuidOrSlug, @Argument List<UUID> productUuids, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.DELETE_CATALOG_PRODUCT, authentication);
        service.removeProductsFromCatalog(uuidOrSlug, productUuids, authentication);
        return true;
    }

    @MutationMapping
    public OrgCatalogResponse orgUpdateCatalog(@Argument UUID uuid, @Argument OrgCatalogUpdateRequest catalogRequest, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.UPDATE_ORGANIZATION, authentication);
        var updated = service.update(uuid, catalogRequest);
        return OrgCatalogResponse.toResponse(updated);
    }

    @MutationMapping
    public boolean orgDeleteByUuidCatalog(@Argument UUID uuid, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.DELETE_ORGANIZATION, authentication);
        service.delete(uuid);
        return true;
    }
}
