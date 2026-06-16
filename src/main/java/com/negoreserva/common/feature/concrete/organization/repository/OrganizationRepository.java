package com.negoreserva.common.feature.concrete.organization.repository;

import com.negoreserva.common.feature.concrete.organization.enums.OrganizationStatus;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.core.repository.SearchableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationRepository extends SearchableRepository<Organization> {
    @Query(
            value = "SELECT DISTINCT o FROM Organization o JOIN o.categories c WHERE o.status = :status AND c.id IN :categoryIds",
            countQuery = "SELECT COUNT(DISTINCT o) FROM Organization o JOIN o.categories c WHERE o.status = :status AND c.id IN :categoryIds"
    )
    Page<Organization> findAllByStatusAndCategoriesIdIn(OrganizationStatus status, List<Long> categoryIds, Pageable pageable);
    Optional<Organization> findByPhone(String phone);
    Optional<Organization> findBySlug(String slug);
    Optional<Organization> findByName(String name);
}