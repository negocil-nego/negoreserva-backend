package com.negoreserva.common.feature.concrete.product.model;

import com.negoreserva.common.feature.concrete.product.model.Product;
import com.negoreserva.common.feature.concrete.product.dto.response.ProductPriceResponse;
import com.negoreserva.common.feature.concrete.product.enums.ProductPriceType;
import com.negoreserva.common.feature.core.model.ConcreteModel;
import com.negoreserva.common.variable.EntityVariable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = EntityVariable.PRODUCT_PRICE)
public class ProductPrice extends ConcreteModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ProductPriceType type = ProductPriceType.BASE;

    @NotNull
    @PositiveOrZero
    @Column(name = "price_value", precision = 10, scale = 2)
    private BigDecimal value;

    @NotNull
    @Positive
    @Builder.Default
    @Column(name = "position_order")
    private Integer order = 1;

    @NotNull
    @Positive
    @Builder.Default
    private Integer unit = 1;

    @Builder.Default
    public Boolean isPrimary = false;

    public ProductPriceResponse toResponse() {
        return new ProductPriceResponse(
                uuid,
                product.getUuid(),
                type,
                value,
                order,
                isPrimary,
                unit,
                product.toResponse()
        );
    }
}
