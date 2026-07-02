package com.negoreserva.common.feature.concrete.product.component;

import com.negoreserva.common.feature.concrete.product.model.Product;
import com.negoreserva.common.feature.concrete.product.model.ProductPrice;
import com.negoreserva.common.feature.concrete.product.enums.ProductPriceData;
import com.negoreserva.common.feature.concrete.product.service.ProductPriceService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductPriceSeeder {
    private final ProductPriceService productPriceService;

    @Setter
    private List<Product> products;

    @Transactional
    public List<ProductPrice> seed() {
        List<ProductPrice> items = new ArrayList<>();
        var productPrices =  productPriceService.findAll();

        for (var item: ProductPriceData.values()) {
            var entity = item.getProductPrice();

            var productPrice = productPrices.stream()
                    .filter(pf -> pf.getValue().equals(entity.getValue()))
                    .findFirst();

            var product = products.stream().filter(it -> it.getName()
                    .equals(item.getProductPrice().getProduct().getName())
            ).findFirst();

            if (productPrice.isEmpty()) {
                product.ifPresent((it) -> {
                    entity.setProduct(it);
                    items.add(productPriceService.save(entity));
                });
            }
        }
        return items;
    }
}
