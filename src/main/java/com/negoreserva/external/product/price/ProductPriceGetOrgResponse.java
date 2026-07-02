package com.negoreserva.external.product.price;

import com.negoreserva.common.feature.concrete.product.enums.ProductPriceType;
import com.negoreserva.common.feature.concrete.product.model.ProductPrice;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductPriceGetOrgResponse(
        UUID uuid,
        ProductPriceType type,
        BigDecimal value,
        Integer order,
        Boolean isPrimary,
        Integer unit
) {

    public static ProductPriceGetOrgResponse of(ProductPrice productPrice) {
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
