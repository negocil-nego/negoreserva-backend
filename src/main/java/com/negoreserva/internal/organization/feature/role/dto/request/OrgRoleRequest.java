package com.negoreserva.internal.organization.feature.role.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record OrgRoleRequest(
        @NotBlank String name,
        List<String> permissionUuids
) {}
