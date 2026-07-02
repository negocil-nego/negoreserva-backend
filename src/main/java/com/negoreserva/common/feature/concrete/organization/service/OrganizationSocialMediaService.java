package com.negoreserva.common.feature.concrete.organization.service;

import com.negoreserva.common.feature.concrete.organization.repository.OrganizationSocialMediaRepo;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.organization.model.OrganizationSocialMedia;
import org.springframework.stereotype.Service;

@Service
public class OrganizationSocialMediaService {
    private final OrganizationSocialMediaRepo repository;

    public OrganizationSocialMediaService(OrganizationSocialMediaRepo repository) {
        this.repository = repository;
    }

    public boolean existsByOrganization(Organization organization) {
        return repository.existsByOrganization(organization);
    }

    public OrganizationSocialMedia save(OrganizationSocialMedia organizationSocialMedia) {
        return repository.save(organizationSocialMedia);
    }
}
