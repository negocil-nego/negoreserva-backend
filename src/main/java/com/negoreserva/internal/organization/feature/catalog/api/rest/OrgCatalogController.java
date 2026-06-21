package com.negoreserva.internal.organization.feature.catalog.api.rest;

import com.negoreserva.internal.organization.feature.catalog.dto.request.OrgCatalogCreateRequest;
import com.negoreserva.internal.organization.feature.catalog.dto.request.OrgCatalogUpdateRequest;
import com.negoreserva.internal.organization.feature.catalog.dto.response.OrgCatalogPaginate;
import com.negoreserva.internal.organization.feature.catalog.dto.response.OrgCatalogResponse;
import com.negoreserva.internal.organization.feature.catalog.service.OrgCatalogService;
import com.negoreserva.internal.organization.feature.catalog.util.OrgCatalogRouteNamed;
import com.negoreserva.internal.organization.feature.permission.enums.OrgPermissionData;
import com.negoreserva.internal.organization.componet.OrgControlAccess;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(OrgCatalogRouteNamed.PATH)
@Tag(name = "Org - Catalog", description = "Endpoints for catalog management")
public class OrgCatalogController {
    private final OrgControlAccess controlAccess;
    private final OrgCatalogService service;

    @GetMapping
    @Operation(summary = "Get all catalogs")
    public ResponseEntity<OrgCatalogPaginate> findAll(@ParameterObject Pageable page, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_ORGANIZATION, authentication);
        return ResponseEntity.ok(service.paginate(page, authentication));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Create catalog with image")
    public ResponseEntity<OrgCatalogResponse> save(
            @RequestPart("catalog") @Valid OrgCatalogCreateRequest catalogDto,
            @RequestPart(value = "image", required = false) MultipartFile image,
            Authentication authentication
    ) {
        controlAccess.canPermission(OrgPermissionData.CREATE_ORGANIZATION, authentication);
        var saved = service.create(catalogDto, image, authentication);
        return new ResponseEntity<>(OrgCatalogResponse.toResponse(saved), HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    @Operation(summary = "Update catalog data")
    public ResponseEntity<OrgCatalogResponse> update(
            @PathVariable UUID uuid,
            @RequestBody @Valid OrgCatalogUpdateRequest catalogDto,
            Authentication authentication
    ) {
        controlAccess.canPermission(OrgPermissionData.UPDATE_ORGANIZATION, authentication);
        var updated = service.update(uuid, catalogDto);
        return ResponseEntity.ok(OrgCatalogResponse.toResponse(updated));
    }

    @PostMapping(value = OrgCatalogRouteNamed.UPDATE_IMAGE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Update catalog image")
    public ResponseEntity<OrgCatalogResponse> updateImage(
            @RequestParam UUID uuid,
            @RequestPart("image") MultipartFile image,
            Authentication authentication
    ) {
        controlAccess.canPermission(OrgPermissionData.UPDATE_ORGANIZATION, authentication);
        var updated = service.updateImage(uuid, image);
        return ResponseEntity.ok(OrgCatalogResponse.toResponse(updated));
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "Delete catalog by uuid")
    public ResponseEntity<Void> deleteByUuid(@PathVariable UUID uuid, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.DELETE_ORGANIZATION, authentication);
        service.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}
