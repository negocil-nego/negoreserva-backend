package com.negoreserva.external.feature.product.mapper;

import com.negoreserva.common.feature.concrete.product.model.Product;
import com.negoreserva.external.feature.product.dto.response.ProductGetOrganizationResponse;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class ProductGetOrganizationMapper {
    private Product product;

    public ProductGetOrganizationResponse toResponse() {
        return new ProductGetOrganizationResponse(
                product.getUuid(),
                product.getName(),
                product.getSlug(),
                product.getDescription(),
                product.getImage()
        );
    }
}
