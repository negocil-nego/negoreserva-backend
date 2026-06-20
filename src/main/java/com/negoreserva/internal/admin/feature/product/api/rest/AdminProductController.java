package com.negoreserva.internal.admin.feature.product.api.rest;

import com.negoreserva.internal.admin.feature.product.dto.queryparam.ProductFilterQueryParam;
import com.negoreserva.internal.admin.feature.product.service.AdminProductService;
import com.negoreserva.internal.admin.feature.product.util.ProductRouteNamed;
import com.negoreserva.common.feature.concrete.product.dto.request.ProductRequest;
import com.negoreserva.common.feature.concrete.product.dto.response.ProductPaginate;
import com.negoreserva.common.feature.concrete.product.dto.response.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(ProductRouteNamed.PATH)
@Tag(name = "Admin - Product", description = "Endpoints for products management")
public class AdminProductController {

    private final AdminProductService service;

    @GetMapping
    @Operation(summary = "Get all products")
    public ResponseEntity<ProductPaginate> findAll(@ParameterObject Pageable page) {
        return ResponseEntity.ok(service.findAll(page));
    }

    @GetMapping(ProductRouteNamed.FILTER)
    @Operation(summary = "Get products by filter")
    public ResponseEntity<ProductPaginate> findByFilter(@ParameterObject @ModelAttribute ProductFilterQueryParam filter) {
        return ResponseEntity.ok(service.findAll(filter));
    }

    @GetMapping(ProductRouteNamed.FIND_BY_NAME)
    @Operation(summary = "Get product by name")
    public ResponseEntity<ProductResponse> findByName(@PathVariable String name) {
        return ResponseEntity.ok(service.findByName(name).toResponse());
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "Get product by uuid")
    public ResponseEntity<ProductResponse> findByUuid(@PathVariable UUID uuid) {
        return ResponseEntity.ok(service.findByUuid(uuid).toResponse());
    }

    @PostMapping
    @Operation(summary = "Create product")
    public ResponseEntity<ProductResponse> save(@RequestBody @Valid ProductRequest productDto) {
        var product = service.saveWithOrganization(productDto.toModel(), productDto.organizationUuid());
        return new ResponseEntity<>(product.toResponse(), HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    @Operation(summary = "Update product")
    public ResponseEntity<ProductResponse> update(@PathVariable UUID uuid, @RequestBody @Valid ProductRequest productDto) {
        var product = service.updateWithOrganization(uuid, productDto.toModel(), productDto.organizationUuid());
        return new ResponseEntity<>(product.toResponse(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "Delete product by uuid")
    public ResponseEntity<Void> deleteByUuid(@PathVariable UUID uuid) {
        service.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}