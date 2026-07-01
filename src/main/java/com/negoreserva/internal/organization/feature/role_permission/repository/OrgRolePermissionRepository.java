package com.negoreserva.internal.organization.feature.role_permission.repository;

import com.negoreserva.common.feature.concrete.permission.model.Permission;
import com.negoreserva.internal.organization.feature.role.model.OrgRole;
import com.negoreserva.internal.organization.feature.role_permission.model.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrgRolePermissionRepository extends JpaRepository<RolePermission, Long> {
    Optional<RolePermission> findByOrgRoleAndPermission(OrgRole orgRole, Permission permission);
    List<RolePermission> findByOrgRole(OrgRole orgRole);
}

