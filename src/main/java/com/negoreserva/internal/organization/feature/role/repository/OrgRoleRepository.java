package com.negoreserva.internal.organization.feature.role.repository;

import com.negoreserva.common.feature.core.repository.ConcreteRepository;
import com.negoreserva.internal.organization.feature.role.model.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrgRoleRepository extends ConcreteRepository<Role> {
    Optional<Role> findByName(String name);
    Optional<Role> findByCode(String code);
}
