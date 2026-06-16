package com.negoreserva.internal.organization.feature.user_organization.repository;

import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.pivot.user_organization.model.UserOrganization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgUserOrganizationRepo extends JpaRepository<UserOrganization, Long> {
    boolean existsByUserAndOrganization(User user, Organization organization);
}
