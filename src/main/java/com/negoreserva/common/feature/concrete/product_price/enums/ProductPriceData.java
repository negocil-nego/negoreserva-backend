package com.negoreserva.common.feature.concrete.product_price.enums;

import com.negoreserva.common.feature.concrete.product.model.Product;
import com.negoreserva.common.feature.concrete.product_price.model.ProductPrice;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum ProductPriceData {
    NORMAL_100(ProductPrice.builder().value(new BigDecimal("100.00")).order(1).isPrimary(true).product(Product.builder().name("Quarto Standard").build()).build()),
    NORMAL_150(ProductPrice.builder().value(new BigDecimal("150.00")).order(2).isPrimary(false).product(Product.builder().name("Quarto Standard").build()).build()),
    RESERVATION_200(ProductPrice.builder().value(new BigDecimal("200.00")).order(1).isPrimary(true).product(Product.builder().name("Reserva").build()).build()),
    HOUR_50(ProductPrice.builder().value(new BigDecimal("50.00")).order(1).isPrimary(true).product(Product.builder().name("Hora").build()).build()),
    MONTHLY_2000(ProductPrice.builder().value(new BigDecimal("2000.00")).order(1).isPrimary(true).product(Product.builder().name("Mensalidade").build()).build());

    private final ProductPrice productPrice;
}
