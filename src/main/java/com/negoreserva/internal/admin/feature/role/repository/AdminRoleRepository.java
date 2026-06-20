package com.negoreserva.internal.admin.feature.role.repository;

import com.negoreserva.internal.admin.feature.role.model.Role;
import com.negoreserva.common.feature.core.repository.ConcreteRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRoleRepository extends ConcreteRepository<Role> {
    Optional<Role> findByCode(String code);
}
