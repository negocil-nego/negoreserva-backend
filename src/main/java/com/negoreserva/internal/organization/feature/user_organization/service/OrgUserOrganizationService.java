package com.negoreserva.internal.organization.feature.user_organization.service;

import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.pivot.user_organization.model.UserOrganization;
import com.negoreserva.internal.organization.feature.user_organization.repository.OrgUserOrganizationRepo;
import org.springframework.stereotype.Service;

@Service
public class OrgUserOrganizationService {
    private final OrgUserOrganizationRepo orgUserOrganizationRepo;

    public OrgUserOrganizationService(OrgUserOrganizationRepo orgUserOrganizationRepo) {
        this.orgUserOrganizationRepo = orgUserOrganizationRepo;
    }

    public UserOrganization save(UserOrganization userOrganization) {
        return orgUserOrganizationRepo.save(userOrganization);
    }

    public boolean existsByUserAndOrganization(User user, Organization organization) {
        return orgUserOrganizationRepo.existsByUserAndOrganization(user, organization);
    }
}
