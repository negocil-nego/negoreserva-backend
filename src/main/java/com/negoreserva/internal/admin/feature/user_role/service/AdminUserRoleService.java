package com.negoreserva.internal.admin.feature.user_role.service;

import com.negoreserva.internal.admin.feature.user_role.model.UserRole;
import com.negoreserva.internal.admin.feature.user_role.repository.AdminUserRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminUserRoleService {
    private final AdminUserRoleRepository adminUserRoleRepository;

    public AdminUserRoleService(AdminUserRoleRepository adminUserRoleRepository) {
        this.adminUserRoleRepository = adminUserRoleRepository;
    }

    public UserRole findOrCreate(UserRole userRole) {
        return adminUserRoleRepository.findByUserAndRole(
                userRole.getUser(),
                userRole.getRole()
        ).orElseGet(() -> adminUserRoleRepository.save(userRole));
    }
}
