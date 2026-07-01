package com.negoreserva.internal.organization.feature.role.api.graphql;

import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.internal.admin.feature.role.dto.response.RoleResponse;
import com.negoreserva.internal.organization.feature.organization.service.OrgOrganizationService;
import com.negoreserva.internal.organization.feature.permission.enums.OrgPermissionData;
import com.negoreserva.internal.organization.feature.role.dto.request.OrgRoleRequest;
import com.negoreserva.internal.organization.feature.role.dto.response.RolePaginate;
import com.negoreserva.internal.organization.feature.role.service.OrgRoleService;
import com.negoreserva.internal.organization.component.OrgControlAccess;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class OrgRoleResolver {
    private final OrgOrganizationService organizationService;
    private final OrgControlAccess controlAccess;
    private final OrgRoleService service;

    @QueryMapping
    public RolePaginate orgPaginateRole(@Argument PaginateRequest paginateRequest, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_USER, authentication);
        var organization = organizationService.findBy(authentication);
        return service.paginateByOrganization(organization.getId(), paginateRequest);
    }

    @QueryMapping
    public RoleResponse orgFindByUuidRole(@Argument UUID uuid, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_USER, authentication);
        return service.findByUuid(uuid).toResponse();
    }

    @QueryMapping
    public List<RoleResponse> orgFindAllRole(Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_USER, authentication);
        var organization = organizationService.findBy(authentication);
        return service.findAllByOrganization(organization.getId())
                .stream()
                .map(OrgRole -> new RoleResponse(OrgRole.getUuid(), OrgRole.getName()))
                .toList();
    }

    @MutationMapping
    public RoleResponse orgSaveRole(@Argument OrgRoleRequest roleRequest, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.UPDATE_USER, authentication);
        var organization = organizationService.findBy(authentication);
        var role = com.negoreserva.internal.organization.feature.role.model.OrgRole.builder()
                .name(roleRequest.name())
                .organization(organization)
                .build();
        return service.save(role).toResponse();
    }

    @MutationMapping
    public RoleResponse orgUpdateRole(@Argument UUID uuid, @Argument OrgRoleRequest roleRequest, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.UPDATE_USER, authentication);
        var existing = service.findByUuid(uuid);
        existing.setName(roleRequest.name());
        var updated = service.save(existing);
        if (roleRequest.permissionUuids() != null) {
            service.syncPermissions(updated, roleRequest.permissionUuids());
        }
        return updated.toResponse();
    }

    @MutationMapping
    public boolean orgDeleteByUuidRole(@Argument UUID uuid, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.DELETE_USER, authentication);
        service.deleteByUuid(uuid);
        return true;
    }
}
