package com.negoreserva.internal.admin.feature.role_permission.service;

import com.negoreserva.common.feature.concrete.permission.model.Permission;
import com.negoreserva.internal.admin.feature.role.model.Role;
import com.negoreserva.internal.admin.feature.role_permission.repository.AdminRolePermissionRepository;
import com.negoreserva.internal.admin.feature.role_permission.model.RolePermission;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminRolePermissionService {
    private final AdminRolePermissionRepository adminRolePermissionRepository;

    @Setter
    private List<Permission> permissions;

    @Setter
    private List<Role> roles;


    public AdminRolePermissionService(AdminRolePermissionRepository adminRolePermissionRepository) {
        this.adminRolePermissionRepository = adminRolePermissionRepository;
    }

    public RolePermission findOrCreate(RolePermission rolePermission) {
        return adminRolePermissionRepository.findByRoleAndPermission(
                rolePermission.getRole(),
                rolePermission.getPermission()
        ).orElseGet(() -> adminRolePermissionRepository.save(rolePermission));
    }
}
