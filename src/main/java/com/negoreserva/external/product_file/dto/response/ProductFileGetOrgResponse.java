package com.negoreserva.external.feature.product_file.dto.response;

import com.negoreserva.common.feature.concrete.product.enums.ProductFileType;
import com.negoreserva.common.feature.concrete.product.model.ProductFile;

import java.util.UUID;

public record ProductFileGetOrgResponse(
        UUID uuid,
        String title,
        String description,
        String url,
        ProductFileType type,
        Boolean isPrimary
) {

    public static ProductFileGetOrgResponse of(ProductFile productFile) {
        return new ProductFileGetOrgResponse(
                productFile.getUuid(),
                productFile.getTitle(),
                productFile.getDescription(),
                productFile.getUrl(),
                productFile.getType(),
                productFile.getIsPrimary()
        );
    }

}
