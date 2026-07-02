package com.negoreserva.common.feature.concrete.product.enums;

import com.negoreserva.common.feature.concrete.product.model.ProductPrice;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.random.RandomGenerator;

@Getter
@AllArgsConstructor
public enum ProductPriceFaker {
    NORMAL_1(ProductPrice.builder().value(new BigDecimal("100.00")).order(1).isPrimary(true).build()),
    NORMAL_2(ProductPrice.builder().value(new BigDecimal("150.00")).order(2).isPrimary(false).build()),
    RESERVATION_1(ProductPrice.builder().value(new BigDecimal("200.00")).order(1).isPrimary(true).build()),
    HOUR_1(ProductPrice.builder().value(new BigDecimal("50.00")).order(1).isPrimary(true).build()),
    MONTHLY_1(ProductPrice.builder().value(new BigDecimal("2000.00")).order(1).isPrimary(true).build()),
    YEAR_1(ProductPrice.builder().value(new BigDecimal("20000.00")).order(1).isPrimary(true).build());

    private final ProductPrice productPrice;

    public static List<ProductPrice> listProductPrices() {
        return Arrays.stream(ProductPriceFaker.values()).map(ProductPriceFaker::getProductPrice).toList();
    }

    public static ProductPrice random() {
        var prices = listProductPrices();
        var random = RandomGenerator.getDefault();
        var index = random.nextInt(prices.size());
        return prices.get(index);
    }
}
