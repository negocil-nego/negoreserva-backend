package com.negoreserva.common.feature.concrete.product.dto.response;

import com.negoreserva.common.feature.concrete.product.dto.response.ProductResponse;

import java.util.UUID;

public record ProductTagInfoResponse(
        UUID uuid,
        UUID productUuid,
        String icon,
        String title,
        String value,
        ProductResponse product
) { }
