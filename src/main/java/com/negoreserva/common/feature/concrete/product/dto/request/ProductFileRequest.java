package com.negoreserva.common.feature.concrete.product.dto.request;

import com.negoreserva.common.feature.concrete.product.enums.ProductFileType;
import com.negoreserva.common.feature.concrete.product.model.ProductFile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record ProductFileRequest(
        UUID productUuid,
        @Size(max = 100)
        String title,
        @Size(max = 255)
        String description,
        @NotBlank
        @Size(max = 255)
        String url,
        @NotNull
        ProductFileType type
) {
    public ProductFile toModel() {
        return ProductFile.builder()
                .title(title)
                .description(description)
                .url(url)
                .type(type)
                .build();
    }
}