package com.negoreserva.common.feature.concrete.product.dto.response;

import com.negoreserva.common.feature.concrete.organization.dto.response.OrganizationResponse;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(
        UUID uuid,
        String name,
        String slug,
        String description,
        String image,
        BigDecimal price,
        OrganizationResponse organization
) { }