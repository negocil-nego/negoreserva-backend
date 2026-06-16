package com.negoreserva.common.feature.concrete.product.dto.request;

import com.negoreserva.common.feature.concrete.product.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record ProductRequest(
        @NotBlank
        @Size(max = 100)
        String name,
        @NotBlank
        @Size(max = 255)
        String description,
        UUID organizationUuid
) {
    public Product toModel() {
        return Product.builder()
                .name(name)
                .description(description)
                .build();
    }
}