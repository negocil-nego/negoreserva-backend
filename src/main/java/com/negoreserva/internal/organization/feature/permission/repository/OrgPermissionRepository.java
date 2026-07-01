package com.negoreserva.internal.organization.feature.permission.repository;

import com.negoreserva.common.feature.concrete.permission.model.Permission;
import com.negoreserva.common.feature.core.repository.ConcreteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrgPermissionRepository extends ConcreteRepository<Permission> {
    Optional<Permission> findByName(String name);
    List<Permission> findByNameIn(Collection<String> names);
    Page<Permission> findByNameIn(Collection<String> names, Pageable pageable);
}
