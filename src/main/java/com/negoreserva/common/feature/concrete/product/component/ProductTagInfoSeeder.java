package com.negoreserva.common.feature.concrete.product.component;

import com.negoreserva.common.feature.concrete.product.service.ProductTagInfoService;
import com.negoreserva.common.feature.concrete.product.enums.ProductTagInfoFaker;
import com.negoreserva.common.feature.concrete.product.model.ProductTagInfo;
import com.negoreserva.common.feature.concrete.product.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductTagInfoSeeder {
    private final ProductTagInfoService productTagInfoService;

    @Setter
    private List<Product> products;

    @Transactional
    public List<ProductTagInfo> seed() {
        List<ProductTagInfo> items = new ArrayList<>();
        var productTagInfos =  productTagInfoService.findAll();

        for (var item: ProductTagInfoFaker.values()) {
            var product = products.stream().filter(it -> it.getName()
                    .equals(item.getProductTagInfo().getProduct().getName())
            ).findFirst();

            product.ifPresent((it) -> {
                var productTagInfo = productTagInfos.stream().filter((a) ->
                        a.getProduct().getName().equals(it.getName()) && a.getTitle().equals(item.getProductTagInfo().getTitle())
                ).findFirst();

                if (productTagInfo.isEmpty()) {
                    var data = item.getProductTagInfo();
                    data.setProduct(it);
                    items.add(productTagInfoService.save(data));
                }

            });
        }
        return items;
    }
}