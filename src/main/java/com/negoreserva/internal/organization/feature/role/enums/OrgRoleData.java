package com.negoreserva.internal.organization.feature.role.enums;

import com.negoreserva.internal.organization.feature.role.model.OrgRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrgRoleData {
    ADMIN(OrgRole.builder()
                    .name("Gestor de sistema da organização")
                    .code("ORG_MANAGER_ORGANIZATION")
                    .build()
    );
    
    private final OrgRole orgRole;
}
