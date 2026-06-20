package com.negoreserva.internal.admin.feature.permission.repository;

import com.negoreserva.common.feature.concrete.permission.model.Permission;
import com.negoreserva.common.feature.core.repository.ConcreteRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminPermissionRepository extends ConcreteRepository<Permission> {
    Optional<Permission> findByName(String name);
}
