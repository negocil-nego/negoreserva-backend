package com.negoreserva.common.feature.concrete.product.dto.request;

import com.negoreserva.common.feature.concrete.product.model.ProductTagInfo;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record ProductTagInfoRequest(
        UUID productUuid,
        @Size(max = 100)
        String icon,
        @Size(max = 255)
        String title,
        @Size(max = 255)
        String value
) {
    public ProductTagInfo toModel() {
        return ProductTagInfo.builder()
                .icon(icon)
                .title(title)
                .value(value)
                .build();
    }
}
