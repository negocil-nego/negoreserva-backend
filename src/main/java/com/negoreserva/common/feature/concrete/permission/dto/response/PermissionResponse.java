package com.negoreserva.common.feature.concrete.permission.dto.response;

import java.util.UUID;

public record PermissionResponse(UUID uuid, String name, String description) {}
