package com.negoreserva.common.feature.concrete.product.component;

import com.negoreserva.common.feature.concrete.product.model.Product;
import com.negoreserva.common.feature.concrete.product.model.ProductFile;
import com.negoreserva.internal.admin.feature.product_file.enums.ProductFileData;
import com.negoreserva.common.feature.concrete.product.service.ProductFileService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductFileSeeder {
    private final ProductFileService productFileService;

    @Setter
    private List<Product> products;

    @Transactional
    public List<ProductFile> seed() {
        List<ProductFile> items = new ArrayList<>();
        var productFiles =  productFileService.findAll();

        for (var item: ProductFileData.values()) {
            var entity = item.getProductFile();

            var productFile = productFiles.stream()
                    .filter(pf -> pf.getUrl().equals(entity.getUrl()))
                    .findFirst();

            var product = products.stream().filter(it -> it.getName()
                    .equals(item.getProductFile().getProduct().getName())
            ).findFirst();

            if (productFile.isEmpty()) {
                product.ifPresent((it) -> {
                    entity.setProduct(it);
                    items.add(productFileService.save(entity));
                });
            }
        }
        return items;
    }
}