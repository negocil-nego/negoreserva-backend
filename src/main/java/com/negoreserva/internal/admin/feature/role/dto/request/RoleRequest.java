package com.negoreserva.internal.admin.feature.role.dto.request;

import com.negoreserva.internal.admin.feature.role.model.Role;
import jakarta.validation.constraints.NotBlank;

public record RoleRequest(
        @NotBlank String name
) {
    public Role toModel() {
        return Role.builder()
                .name(name)
                .build();
    }
}
