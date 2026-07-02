package com.negoreserva.common.feature.concrete.organization.repository;

import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.organization.model.OrganizationSocialMedia;
import com.negoreserva.common.feature.core.repository.ConcreteRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrganizationSocialMediaRepo extends ConcreteRepository<OrganizationSocialMedia> {
    boolean existsByOrganization(Organization organization);
    Optional<OrganizationSocialMedia> findByUuid(UUID uuid);
}
