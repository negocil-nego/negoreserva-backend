package com.negoreserva.common.feature.concrete.product.dto.request;

import com.negoreserva.common.feature.concrete.product.enums.ProductPriceType;
import com.negoreserva.common.feature.concrete.product.model.ProductPrice;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductPriceRequest(
        UUID productUuid,
        @NotNull
        ProductPriceType type,
        @NotNull
        @PositiveOrZero
        BigDecimal value,
        @NotNull
        @Positive
        Integer order,
        Boolean isPrimary,
        @NotNull
        @Positive
        Integer unit
) {
    public ProductPrice toModel() {
        return ProductPrice.builder()
                .type(type)
                .value(value)
                .order(order)
                .isPrimary(isPrimary)
                .unit(unit != null ? unit : 1)
                .build();
    }
}
