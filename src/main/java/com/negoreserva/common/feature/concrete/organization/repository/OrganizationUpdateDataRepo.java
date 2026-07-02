package com.negoreserva.common.feature.concrete.organization.repository;

import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.organization.model.OrganizationUpdateData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationUpdateDataRepo extends JpaRepository<OrganizationUpdateData, Long> {
    Optional<OrganizationUpdateData> findByOrganization(Organization organization);
}
