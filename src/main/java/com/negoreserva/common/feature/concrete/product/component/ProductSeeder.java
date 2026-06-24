package com.negoreserva.common.feature.concrete.product.component;

import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.product.model.Product;
import com.negoreserva.common.feature.concrete.product.service.ProductService;
import com.negoreserva.common.feature.concrete.product.enums.ProductData;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductSeeder {
    private final ProductService productService;
    @Setter
    private List<Organization> organizations;

    @Transactional
    public List<Product> seed() {
        List<Product> items = new ArrayList<>();
        for (var item : ProductData.values()) {
            var product = item.getProduct();
            var optional = organizations.stream().filter(it -> it.getName().equals(product.getOrganization().getName()))
                    .findFirst();

            if(optional.isPresent()) {
                product.setOrganization(optional.get());
                items.add(productService.findOrCreate(product));
            }
        }
        return items;
    }
}