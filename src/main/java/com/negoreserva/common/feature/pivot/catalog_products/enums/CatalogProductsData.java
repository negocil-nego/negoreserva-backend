package com.negoreserva.common.feature.pivot.catalog_products.enums;

import com.negoreserva.common.feature.concrete.catalog.model.Catalog;
import com.negoreserva.common.feature.concrete.product.model.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CatalogProductsData {
    // You'll map specific products to catalogs here
    // For now, we keep it minimal since products are already seeded
    ;
    private final Catalog catalog;
    private final Product product;
    private final Integer order;
}
