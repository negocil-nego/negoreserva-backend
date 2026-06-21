package com.negoreserva.common.feature.concrete.organization.repository;

import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.core.repository.SearchableRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationRepository extends SearchableRepository<Organization> {
    Optional<Organization> findByPhone(String phone);
    Optional<Organization> findBySlug(String slug);
    Optional<Organization> findByName(String name);

    List<Organization> findByNameIn(List<String> names);
}