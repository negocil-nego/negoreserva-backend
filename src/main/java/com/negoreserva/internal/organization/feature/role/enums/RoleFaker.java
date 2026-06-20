package com.negoreserva.internal.organization.feature.role.enums;

import com.negoreserva.internal.organization.feature.role.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleFaker {
    ADMIN(
            Role.builder()
                    .name("Administrator system")
                    .code("ADMIN_SYSTEM")
                    .build()
    );
    
    private final Role role;
}
