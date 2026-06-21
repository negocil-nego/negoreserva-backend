package com.negoreserva.internal.admin.feature.organization.api.graphql;

import com.negoreserva.common.feature.concrete.organization.dto.response.OrganizationPaginate;
import com.negoreserva.internal.admin.feature.organization.dto.queryparam.OrganizationFilterQueryParam;
import com.negoreserva.internal.admin.feature.organization.service.AdminOrganizationService;
import com.negoreserva.internal.admin.feature.permission.enums.AdminPermissionData;
import com.negoreserva.internal.admin.component.AdminControlAccess;
import com.negoreserva.common.feature.concrete.organization.dto.request.OrganizationRequest;
import com.negoreserva.common.feature.concrete.organization.dto.response.OrganizationResponse;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
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
public class AdminOrganizationResolver {
    private final AdminControlAccess controlAccess;
    private final AdminOrganizationService service;

    @QueryMapping
    public OrganizationResponse adminFindByUuidOrganization(@Argument String uuid, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_ORGANIZATION, authentication);
        var organization = service.findByUuid(UUID.fromString(uuid));
        return OrganizationResponse.of(organization);
    }

    @QueryMapping
    public OrganizationResponse adminFindByNameOrganization(@Argument String name, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_ORGANIZATION, authentication);
        var organization = service.findByName(name);
        return OrganizationResponse.of(organization);

    }

    @QueryMapping
    public OrganizationResponse adminFindByPhoneOrganization(@Argument String phone, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_ORGANIZATION, authentication);
        var organization = service.findByPhone(phone);
        return OrganizationResponse.of(organization);
    }

    @QueryMapping
    public OrganizationPaginate adminPaginateOrganization(@Argument PaginateRequest paginateRequest, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_ORGANIZATION, authentication);
        return OrganizationPaginate.of(service.findAll(paginateRequest));
    }

    @QueryMapping
    public OrganizationPaginate adminPaginateOrganizationFilter(@Argument OrganizationFilterQueryParam filter, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_ORGANIZATION, authentication);
        return OrganizationPaginate.of(service.findAll(filter));
    }

    @MutationMapping
    public OrganizationResponse adminSaveOrganization(@Argument @Valid OrganizationRequest organizationRequest, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.CREATE_ORGANIZATION, authentication);
        var organization = service.save(organizationRequest.toModel());
        return OrganizationResponse.of(organization);
    }

    @MutationMapping
    public OrganizationResponse adminUpdateOrganization(@Argument String uuid, @Argument @Valid OrganizationRequest organizationRequest, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.UPDATE_ORGANIZATION, authentication);
        var organization = service.update(UUID.fromString(uuid), organizationRequest.toModel());
        return OrganizationResponse.of(organization);
    }

    @MutationMapping
    public boolean adminDeleteByUuidOrganization(@Argument String uuid, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.DELETE_ORGANIZATION, authentication);
        service.deleteByUuid(UUID.fromString(uuid));
        return true;
    }
}