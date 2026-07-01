package com.negoreserva.external.feature.product.api.graphql;

import com.negoreserva.common.feature.concrete.product.dto.queryparam.ProductSearchFilterParam;
import com.negoreserva.common.feature.concrete.product.dto.response.ProductResponse;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.external.feature.product.dto.response.ProductDetailResponse;
import com.negoreserva.common.feature.concrete.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductGraphql {
    private final ProductService service;

    @QueryMapping
    public ProductDetailResponse pubProductDetail(@Argument String uuidOrSlug) {
        var product = service.findByUuidOrSlug(uuidOrSlug);
        return ProductDetailResponse.of(product);
    }

    @QueryMapping
    public Page<ProductResponse> pubSearchProduct(
            @Argument String q,
            @Argument PaginateRequest paginateRequest
    ) {
        return service.search(q, PageRequest.of(paginateRequest.pageNumber(), paginateRequest.pageSize()));
    }

    @QueryMapping
    public Page<ProductResponse> pubSearchProductFilter(
            @Argument ProductSearchFilterParam filter,
            @Argument PaginateRequest paginateRequest
    ) {
        return service.search(filter, PageRequest.of(paginateRequest.pageNumber(), paginateRequest.pageSize()));
    }
}
