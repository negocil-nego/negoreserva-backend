package com.negoreserva.common.feature.concrete.role.dto.response;

import com.negoreserva.common.feature.concrete.role.model.RoleType;
import java.util.UUID;

public record RoleResponse(UUID uuid, String name, String code, RoleType type) {}
