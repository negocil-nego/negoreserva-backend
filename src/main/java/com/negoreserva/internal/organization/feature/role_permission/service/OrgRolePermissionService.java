package com.negoreserva.internal.organization.feature.role_permission.service;

import com.negoreserva.internal.organization.feature.role_permission.model.RolePermission;
import com.negoreserva.internal.organization.feature.role_permission.repository.OrgRolePermissionRepository;
import org.springframework.stereotype.Service;

@Service
public class OrgRolePermissionService {
    private final OrgRolePermissionRepository orgRolePermissionRepository;

    public OrgRolePermissionService(OrgRolePermissionRepository orgRolePermissionRepository) {
        this.orgRolePermissionRepository = orgRolePermissionRepository;
    }

    public RolePermission findOrCreate(RolePermission rolePermission) {
        return orgRolePermissionRepository.findByRoleAndPermission(
                rolePermission.getRole(),
                rolePermission.getPermission()
        ).orElseGet(() -> orgRolePermissionRepository.save(rolePermission));
    }
}
