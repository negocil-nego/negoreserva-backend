package com.negoreserva.external.product.mapper;

import com.negoreserva.common.feature.concrete.product.model.ProductFile;
import com.negoreserva.external.product.dto.response.ProductFileGetOrgResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductFileGetOrganizationMapper {
    private ProductFile productFile;

    public ProductFileGetOrgResponse toResponse() {
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
