package com.negoreserva.external.feature.product.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductGetOrganizationResponse(
        UUID uuid,
        String name,
        String slug,
        String description,
        String image
) {
}