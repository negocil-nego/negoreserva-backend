package com.negoreserva.internal.organization.feature.role.repository;

import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.core.repository.ConcreteRepository;
import com.negoreserva.internal.organization.feature.role.model.OrgRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrgRoleRepository extends ConcreteRepository<OrgRole> {
    Optional<OrgRole> findByNameAndOrganization(String name, Organization organization);

    @Query("select r from OrgRole r where r.organization.id = :organizationId and r.deletedAt is null")
    Page<OrgRole> findAllByOrganizationId(@Param("organizationId") Long organizationId, Pageable pageable);
}
