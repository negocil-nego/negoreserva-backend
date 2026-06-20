package com.negoreserva.internal.admin.feature.user_role.repository;

import com.negoreserva.internal.admin.feature.role.model.Role;
import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.internal.admin.feature.user_role.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminUserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByUserAndRole(User user, Role role);
}

