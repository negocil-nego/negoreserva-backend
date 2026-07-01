package com.negoreserva.external.feature.product_price.mapper;

import com.negoreserva.common.feature.concrete.product.model.ProductPrice;
import com.negoreserva.external.feature.product_price.dto.response.ProductPriceGetOrgResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductPriceGetOrganizationMapper {
    private ProductPrice productPrice;

    public ProductPriceGetOrgResponse toResponse() {
        return new ProductPriceGetOrgResponse(
                productPrice.getUuid(),
                productPrice.getType(),
                productPrice.getValue(),
                productPrice.getOrder(),
                productPrice.getIsPrimary(),
                productPrice.getUnit()
        );
    }
}
