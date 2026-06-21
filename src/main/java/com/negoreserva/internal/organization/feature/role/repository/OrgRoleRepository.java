package com.negoreserva.internal.organization.feature.role.repository;

import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.core.repository.ConcreteRepository;
import com.negoreserva.internal.organization.feature.role.model.OrgRole;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrgRoleRepository extends ConcreteRepository<OrgRole> {
    Optional<OrgRole> findByNameAndOrganization(String name, Organization organization);
}
