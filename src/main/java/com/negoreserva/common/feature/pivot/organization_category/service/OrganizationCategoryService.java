package com.negoreserva.common.feature.pivot.organization_category.service;

import com.negoreserva.common.feature.concrete.category.model.Category;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.pivot.organization_category.model.OrganizationCategory;
import com.negoreserva.common.feature.pivot.organization_category.repository.OrganizationCategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class OrganizationCategoryService {
    private final OrganizationCategoryRepo organizationCategoryRepo;

    public OrganizationCategoryService(OrganizationCategoryRepo organizationCategoryRepo) {
        this.organizationCategoryRepo = organizationCategoryRepo;
    }

    public OrganizationCategory findOrCreate(OrganizationCategory organizationCategory) {
        return organizationCategoryRepo.findByOrganizationAndCategory(
                organizationCategory.getOrganization(),
                organizationCategory.getCategory()
        ).orElseGet(() -> organizationCategoryRepo.save(organizationCategory));
    }
}
