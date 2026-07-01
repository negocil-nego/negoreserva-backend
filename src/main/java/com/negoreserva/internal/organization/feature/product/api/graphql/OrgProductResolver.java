package com.negoreserva.internal.organization.feature.product.api.graphql;

import com.negoreserva.internal.admin.feature.product.dto.queryparam.ProductFilterQueryParam;
import com.negoreserva.internal.organization.feature.permission.enums.OrgPermissionData;
import com.negoreserva.internal.organization.feature.product.dto.response.OrgProductPaginate;
import com.negoreserva.internal.organization.feature.product.dto.response.OrgProductResponse;
import com.negoreserva.internal.organization.feature.product.dto.response.ProductSuggestionResponse;
import com.negoreserva.internal.organization.feature.product.service.OrgProductService;
import com.negoreserva.internal.organization.feature.product.service.OrgProductSuggestionService;
import com.negoreserva.internal.organization.component.OrgControlAccess;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.concrete.product.dto.request.ProductRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class OrgProductResolver {
    private final OrgControlAccess controlAccess;
    private final OrgProductService service;
    private final OrgProductSuggestionService suggestionService;

    @QueryMapping
    public OrgProductResponse orgFindByUuidProduct(@Argument UUID uuid, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_PRODUCT, authentication);
        return OrgProductResponse.toResponse(service.findByUuid(uuid));
    }

    @QueryMapping
    public OrgProductPaginate orgPaginateProduct(@Argument PaginateRequest paginateRequest, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_PRODUCT, authentication);
        return service.paginate(paginateRequest, authentication);
    }

    @QueryMapping
    public OrgProductPaginate orgPaginateProductFilter(@Argument ProductFilterQueryParam filter, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_PRODUCT, authentication);
        return service.paginate(filter, authentication);
    }

    @QueryMapping
    public java.util.List<ProductSuggestionResponse> orgProductSuggestions(Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_PRODUCT, authentication);
        return suggestionService.findBy(authentication);
    }

    @MutationMapping
    public OrgProductResponse orgSaveProduct(@Argument @Valid ProductRequest productRequest, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.CREATE_PRODUCT, authentication);
        var organization = service.find(authentication);
        var product = productRequest.toModel();
        product.setOrganization(organization);
        var saved = service.save(product);
        return OrgProductResponse.toResponse(saved);
    }

    @MutationMapping
    public OrgProductResponse orgUpdateProduct(@Argument UUID uuid, @Argument @Valid ProductRequest productRequest, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.UPDATE_PRODUCT, authentication);
        var product = service.update(uuid, productRequest.toModel());
        return OrgProductResponse.toResponse(product);
    }

    @MutationMapping
    public boolean orgDeleteByUuidProduct(@Argument UUID uuid, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.DELETE_PRODUCT, authentication);
        service.deleteByUuid(uuid);
        return true;
    }
}
