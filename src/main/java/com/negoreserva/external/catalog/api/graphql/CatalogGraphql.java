package com.negoreserva.external.feature.catalog.api.graphql;

import com.negoreserva.common.feature.concrete.catalog.dto.queryparam.CatalogSearchFilterParam;
import com.negoreserva.common.feature.concrete.catalog.service.CatalogService;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.pivot.catalog_products.service.CatalogProductsService;
import com.negoreserva.common.feature.concrete.catalog.dto.response.CatalogResponse;
import com.negoreserva.external.feature.product.dto.response.ProductDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CatalogGraphql {
    private final CatalogService service;
    private final CatalogProductsService catalogProductsService;

    @QueryMapping
    public Page<CatalogResponse> pubSearchCatalogFilter(
            @Argument CatalogSearchFilterParam filter,
            @Argument PaginateRequest paginateRequest
    ) {
        return service.search(filter, PageRequest.of(paginateRequest.pageNumber(), paginateRequest.pageSize()));
    }

    @QueryMapping
    public java.util.List<ProductDetailResponse> pubCatalogProducts(@Argument String uuidOrSlug) {
        var catalog = service.findByUuidOrSlug(uuidOrSlug);
        return catalogProductsService.findAllByCatalogOrderByOrderAsc(catalog)
                .stream()
                .map(catalogProducts -> ProductDetailResponse.of(catalogProducts.getProduct()))
                .toList();
    }
}
