package com.negoreserva.external.product.price;

import com.negoreserva.common.feature.concrete.product.model.ProductPrice;
import com.negoreserva.external.product.price.ProductPriceGetOrgResponse;
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
