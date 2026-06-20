package com.negoreserva.internal.admin.feature.plain.dto.request;

import com.negoreserva.internal.admin.feature.plain.model.Plan;
import com.negoreserva.internal.admin.feature.plain.enums.PlanType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record PlanRequest(
        @NotNull
        @Size(max = 100)
        String name,
        @NotNull
        @DecimalMin(value = "0.0", inclusive = false)
        BigDecimal price,
        String description,
        @NotNull
        PlanType type
) {
    public Plan toModel() {
        return Plan.builder()
                .name(name)
                .price(price)
                .description(description)
                .type(type)
                .build();
    }
}
