package com.negoreserva.internal.organization.feature.product_file.dto.response;

import com.negoreserva.common.feature.concrete.product.enums.ProductFileType;
import com.negoreserva.common.feature.concrete.product.model.ProductFile;

import java.util.UUID;

public record OrgProductFileResponse(
        UUID uuid,
        String title,
        String description,
        String url,
        ProductFileType type
) {

    public static OrgProductFileResponse toResponse(ProductFile productFile) {
        return new OrgProductFileResponse(
                productFile.getUuid(),
                productFile.getTitle(),
                productFile.getDescription(),
                productFile.getUrl(),
                productFile.getType()
        );
    }

}
