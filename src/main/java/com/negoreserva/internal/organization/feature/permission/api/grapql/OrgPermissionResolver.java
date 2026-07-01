package com.negoreserva.internal.organization.feature.permission.api.grapql;

import com.negoreserva.common.feature.concrete.permission.dto.response.PermissionPaginate;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.internal.organization.feature.permission.service.OrgPermissionService;
import com.negoreserva.internal.organization.feature.permission.enums.OrgPermissionData;
import com.negoreserva.internal.organization.component.OrgControlAccess;
import com.negoreserva.common.feature.concrete.permission.dto.response.PermissionResponse;
import com.negoreserva.common.feature.concrete.permission.model.Permission;
import com.negoreserva.internal.organization.feature.role.service.OrgRoleService;
import com.negoreserva.internal.organization.feature.role.model.OrgRole;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class OrgPermissionResolver {
    private final OrgPermissionService permissionService;
    private final OrgRoleService roleService;
    private final OrgControlAccess controlAccess;

    @QueryMapping
    public PermissionPaginate orgPaginate(@Argument PaginateRequest paginateRequest, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_PERMISSION, authentication);
        return PermissionPaginate.of(permissionService.findAll(paginateRequest));
    }

    @QueryMapping
    public List<PermissionResponse> orgFindAll(Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_PERMISSION, authentication);
        var permissions = permissionService.findAll();
        return permissions.stream().map(Permission::toResponse).toList();
    }

    @QueryMapping
    public OrgPermissionService.RolePermissions orgGetRolePermissions(@Argument UUID roleUuid, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_PERMISSION, authentication);
        var role = roleService.findByUuid(roleUuid);
        return permissionService.getRolePermissions(role);
    }
}
