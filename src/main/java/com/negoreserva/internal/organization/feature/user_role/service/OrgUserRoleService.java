package com.negoreserva.internal.organization.feature.user_role.service;

import com.negoreserva.internal.organization.feature.user_role.model.UserRole;
import com.negoreserva.internal.organization.feature.user_role.repository.OrgUserRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class OrgUserRoleService {
    private final OrgUserRoleRepository orgUserRoleRepository;

    public OrgUserRoleService(OrgUserRoleRepository orgUserRoleRepository) {
        this.orgUserRoleRepository = orgUserRoleRepository;
    }

    public UserRole findOrCreate(UserRole userRole) {
        return orgUserRoleRepository.findByUserAndRole(
                userRole.getUser(),
                userRole.getRole()
        ).orElseGet(() -> orgUserRoleRepository.save(userRole));
    }
}
