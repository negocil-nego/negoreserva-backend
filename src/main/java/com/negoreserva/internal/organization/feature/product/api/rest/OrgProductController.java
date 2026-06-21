package com.negoreserva.internal.organization.feature.product.api.rest;

import com.negoreserva.internal.admin.feature.product.dto.queryparam.ProductFilterQueryParam;
import com.negoreserva.internal.organization.feature.permission.enums.OrgPermissionData;
import com.negoreserva.internal.organization.feature.product.dto.response.OrgProductPaginate;
import com.negoreserva.internal.organization.feature.product.dto.response.OrgProductResponse;
import com.negoreserva.internal.organization.feature.product.service.OrgProductService;
import com.negoreserva.internal.organization.feature.product.util.OrgProductRouteNamed;
import com.negoreserva.internal.organization.componet.OrgControlAccess;
import com.negoreserva.common.feature.concrete.product.dto.request.ProductRequest;
import com.negoreserva.internal.organization.feature.product.dto.request.OrgProductCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(OrgProductRouteNamed.PATH)
@Tag(name = "Org - Product", description = "Endpoints for products management")
public class OrgProductController {
    private final OrgControlAccess controlAccess;
    private final OrgProductService service;

    @GetMapping
    @Operation(summary = "Get all products")
    public ResponseEntity<OrgProductPaginate> findAll(@ParameterObject Pageable page, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_PRODUCT, authentication);
        return ResponseEntity.ok(service.paginate(page, authentication));
    }

    @GetMapping(OrgProductRouteNamed.FILTER)
    @Operation(summary = "Get products by filter")
    public ResponseEntity<OrgProductPaginate> findByFilter(@ParameterObject @ModelAttribute ProductFilterQueryParam filter, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_PRODUCT, authentication);
        return ResponseEntity.ok(service.paginate(filter, authentication));
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "Get product by uuid")
    public ResponseEntity<OrgProductResponse> findByUuid(@PathVariable UUID uuid, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_PRODUCT, authentication);
        return ResponseEntity.ok(OrgProductResponse.toResponse(service.findByUuid(uuid)));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Create product")
    public ResponseEntity<OrgProductResponse> save(
            @RequestPart("product") @Valid OrgProductCreateRequest productDto,
            @RequestPart(value = "images", required = false) List<MultipartFile> images,
            @RequestPart(value = "video", required = false) MultipartFile video,
            Authentication authentication
    ) {
        controlAccess.canPermission(OrgPermissionData.CREATE_PRODUCT, authentication);
        var saved = service.createProduct(productDto, images, video, authentication);
        return new ResponseEntity<>(OrgProductResponse.toResponse(saved), HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    @Operation(summary = "Update product")
    public ResponseEntity<OrgProductResponse> update(@PathVariable UUID uuid, @RequestBody @Valid ProductRequest productDto, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.UPDATE_PRODUCT, authentication);
        var product = service.update(uuid, productDto.toModel());
        return ResponseEntity.ok(OrgProductResponse.toResponse(product));
    }

    @PostMapping(value = OrgProductRouteNamed.UPDATE_IMAGE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Edit image of product")
    public ResponseEntity<OrgProductResponse> updateImage(
            @PathVariable UUID uuid,
            @RequestPart("file") MultipartFile file,
            Authentication authentication
    ) {
        controlAccess.canPermission(OrgPermissionData.UPDATE_PRODUCT, authentication);
        return ResponseEntity.ok(OrgProductResponse.toResponse(service.updateImageProduct(uuid, file)));
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "Delete product by uuid")
    public ResponseEntity<Void> deleteByUuid(@PathVariable UUID uuid, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.DELETE_PRODUCT, authentication);
        service.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
