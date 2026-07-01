package com.negoreserva.internal.organization.feature.user_role.service;

import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.internal.organization.feature.role.model.OrgRole;
import com.negoreserva.internal.organization.feature.user_role.model.UserRole;
import com.negoreserva.internal.organization.feature.user_role.repository.OrgUserRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrgUserRoleService {
    private final OrgUserRoleRepository orgUserRoleRepository;

    public OrgUserRoleService(OrgUserRoleRepository orgUserRoleRepository) {
        this.orgUserRoleRepository = orgUserRoleRepository;
    }

    public UserRole findOrCreate(UserRole userRole) {
        return orgUserRoleRepository.findByUserAndOrgRole(
                userRole.getUser(),
                userRole.getOrgRole()
        ).orElseGet(() -> orgUserRoleRepository.save(userRole));
    }

    public UserRole sync(User user, OrgRole orgRole) {
        var userRole = orgUserRoleRepository.findByUserAndOrgRole(user, orgRole)
                .orElseGet(() -> orgUserRoleRepository.save(
                        UserRole.builder().user(user).orgRole(orgRole).build()
                ));
        return userRole;
    }

    public List<OrgRole> findByUser(User user) {
        return orgUserRoleRepository.findByUser(user)
                .stream()
                .map(UserRole::getOrgRole)
                .toList();
    }

    @Transactional
    public void syncRoles(User user, List<OrgRole> roles) {
        var existingLinks = orgUserRoleRepository.findByUser(user);
        orgUserRoleRepository.deleteAll(existingLinks);
        for (var role : roles) {
            orgUserRoleRepository.save(UserRole.builder()
                    .user(user)
                    .orgRole(role)
                    .build());
        }
    }
}
