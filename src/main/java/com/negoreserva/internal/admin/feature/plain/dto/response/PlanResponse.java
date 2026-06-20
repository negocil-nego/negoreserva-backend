package com.negoreserva.internal.admin.feature.plain.dto.response;

import com.negoreserva.internal.admin.feature.plain.enums.PlanType;

import java.math.BigDecimal;
import java.util.UUID;

public record PlanResponse(UUID uuid, String name, BigDecimal price, String description, PlanType type) { }
