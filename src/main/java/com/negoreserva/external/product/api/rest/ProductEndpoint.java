package com.negoreserva.external.feature.product.api.rest;

import com.negoreserva.external.feature.product.dto.response.ProductDetailResponse;
import com.negoreserva.external.feature.product.util.ProductRouteNamed;
import com.negoreserva.common.feature.concrete.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;

@RestController
@RequiredArgsConstructor
@RequestMapping(ProductRouteNamed.PATH)
@Tag(name = "Public - Product", description = "Endpoints for products")
public class ProductEndpoint {
    private final ProductService service;

    @GetMapping(ProductRouteNamed.FIND_DETAIL)
    @Operation(summary = "Get product by id")
    public ResponseEntity<ProductDetailResponse> findDetail(@PathVariable String uuidOrSlug) {
        var product = service.findByUuidOrSlug(uuidOrSlug);
        return ResponseEntity.ok(ProductDetailResponse.of(product));
    }
}
