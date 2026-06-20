package com.negoreserva.internal.admin.feature.product_file.api.rest;

import com.negoreserva.common.feature.concrete.product_file.dto.request.ProductFileRequest;
import com.negoreserva.common.feature.concrete.product_file.dto.response.ProductFilePaginate;
import com.negoreserva.common.feature.concrete.product_file.dto.response.ProductFileResponse;
import com.negoreserva.common.feature.concrete.product_file.service.ProductFileService;
import com.negoreserva.internal.admin.feature.product_file.util.ProductFileRouteNamed;
import com.negoreserva.common.feature.concrete.product_file.model.ProductFile;
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
@RequestMapping(ProductFileRouteNamed.PATH)
@Tag(name = "Admin - ProductFile", description = "Endpoints for files product management")
public class AdminProductFileController {

    private final ProductFileService service;

    @GetMapping
    @Operation(summary = "Get all product files")
    public ResponseEntity<ProductFilePaginate> findAll(@ParameterObject Pageable page) {
        return ResponseEntity.ok(service.findAll(page));
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "Get product file by uuid")
    public ResponseEntity<ProductFileResponse> findByUuid(@PathVariable UUID uuid) {
        return ResponseEntity.ok(service.findByUuid(uuid).toResponse());
    }

    @PostMapping
    @Operation(summary = "Create product file")
    public ResponseEntity<ProductFileResponse> save(@RequestBody @Valid ProductFileRequest productFileDto) {
        ProductFile productFile = service.save(productFileDto.toModel(), productFileDto.productUuid());
        return new ResponseEntity<>(productFile.toResponse(), HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    @Operation(summary = "Update product file")
    public ResponseEntity<ProductFileResponse> update(@PathVariable UUID uuid, @RequestBody @Valid ProductFileRequest productFileDto) {
        ProductFile productFile = service.update(uuid, productFileDto.toModel(), productFileDto.productUuid());
        return new ResponseEntity<>(productFile.toResponse(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "Delete product file by uuid")
    public ResponseEntity<Void> deleteByUuid(@PathVariable UUID uuid) {
        service.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}