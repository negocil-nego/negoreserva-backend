package com.negoreserva.internal.organization.feature.role.component;

import com.negoreserva.internal.organization.feature.role.model.Role;
import com.negoreserva.internal.organization.feature.role.service.OrgRoleService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class OrgRoleSeeder {
    private final OrgRoleService service;

    public OrgRoleSeeder(OrgRoleService service) {
        this.service = service;
    }

    @Transactional
    public List<Role> seed() {
        return List.of();
    }
}
