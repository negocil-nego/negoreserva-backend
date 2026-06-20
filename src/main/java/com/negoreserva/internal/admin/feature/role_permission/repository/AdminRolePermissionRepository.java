package com.negoreserva.internal.admin.feature.role_permission.repository;

import com.negoreserva.common.feature.concrete.permission.model.Permission;
import com.negoreserva.internal.admin.feature.role.model.Role;
import com.negoreserva.internal.admin.feature.role_permission.model.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRolePermissionRepository extends JpaRepository<RolePermission, Long> {
    Optional<RolePermission> findByRoleAndPermission(Role role, Permission permission);
}

