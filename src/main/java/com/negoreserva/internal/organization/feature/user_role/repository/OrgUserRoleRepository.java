package com.negoreserva.internal.organization.feature.user_role.repository;

import com.negoreserva.internal.organization.feature.role.model.OrgRole;
import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.internal.organization.feature.user_role.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrgUserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByUserAndOrgRole(User user, OrgRole orgRole);
}

