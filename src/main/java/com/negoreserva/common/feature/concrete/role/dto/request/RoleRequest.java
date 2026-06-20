package com.negoreserva.common.feature.concrete.role.dto.request;

import com.negoreserva.common.feature.concrete.role.model.Role;
import jakarta.validation.constraints.NotBlank;

public record RoleRequest(
        @NotBlank String name,
        @NotBlank String code
) {
    public Role toModel() {
        return Role.builder()
                .name(name)
                .code(code)
                .build();
    }
}
