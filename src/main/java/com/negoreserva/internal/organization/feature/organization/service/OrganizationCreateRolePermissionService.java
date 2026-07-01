package com.negoreserva.internal.organization.feature.organization.service;

import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.internal.organization.feature.permission.enums.OrgPermissionData;
import com.negoreserva.internal.organization.feature.permission.service.OrgPermissionService;
import com.negoreserva.internal.organization.feature.role.enums.OrgRoleData;
import com.negoreserva.internal.organization.feature.role.model.OrgRole;
import com.negoreserva.internal.organization.feature.role.service.OrgRoleService;
import com.negoreserva.internal.organization.feature.role_permission.model.RolePermission;
import com.negoreserva.internal.organization.feature.role_permission.service.OrgRolePermissionService;
import com.negoreserva.common.feature.concrete.permission.model.Permission;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class OrganizationCreateRolePermissionService {
    private final OrgRolePermissionService orgRolePermissionService;
    private final OrgPermissionService orgPermissionService;
    private final OrgRoleService orgRoleService;

    public RolePermission createRoleAdmin(Organization organization) {
        var orgRole = OrgRole.builder()
                .name(OrgRoleData.ADMIN.getOrgRole().getName())
                .organization(organization)
                .build();

        orgRole = orgRoleService.findOrCreate(orgRole);

        var permission = Permission.builder()
                .name(OrgPermissionData.TOTAL.getPermission().getName())
                .description(OrgPermissionData.TOTAL.getPermission().getDescription())
                .build();

        permission = orgPermissionService.findOrCreate(permission);

        var rolePermission = RolePermission
                .builder()
                .orgRole(orgRole)
                .permission(permission)
                .build();

        final OrgRole finalOrgRole = orgRole;
        CompletableFuture.runAsync(() -> createAllPermission(finalOrgRole));
        return orgRolePermissionService.findOrCreate(rolePermission);
    }

    private void createAllPermission(OrgRole orgRole) {
        var permissions = Arrays.stream(OrgPermissionData.values())
                .filter(it -> !it.equals(OrgPermissionData.TOTAL))
                .map(OrgPermissionData::getPermission)
                .toList();

        for (var permission: permissions) {
            permission = orgPermissionService.findOrCreate(permission);
            var rolePermission = RolePermission
                    .builder()
                    .orgRole(orgRole)
                    .permission(permission)
                    .build();
            orgRolePermissionService.findOrCreate(rolePermission);
        }
    }
}