package com.negoreserva.common.feature.pivot.organization_category.repository;

import com.negoreserva.common.feature.concrete.category.model.Category;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.pivot.organization_category.model.OrganizationCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationCategoryRepo extends JpaRepository<OrganizationCategory, Long> {
    Optional<OrganizationCategory> findByOrganizationAndCategory(Organization organization, Category category);
}
