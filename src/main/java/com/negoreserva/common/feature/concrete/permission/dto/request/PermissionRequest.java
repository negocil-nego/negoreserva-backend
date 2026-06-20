package com.negoreserva.common.feature.concrete.permission.dto.request;

import com.negoreserva.common.feature.concrete.permission.model.Permission;
import jakarta.validation.constraints.NotBlank;

public record PermissionRequest(@NotBlank String name, String description) {
    public Permission toModel() {
        return Permission.builder().name(name).description(description).build();
    }
}
