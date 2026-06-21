package com.negoreserva.internal.admin.feature.organization.api.rest;

import com.negoreserva.common.feature.concrete.organization.dto.response.OrganizationPaginate;
import com.negoreserva.internal.admin.feature.organization.dto.queryparam.OrganizationFilterQueryParam;
import com.negoreserva.internal.admin.feature.organization.service.AdminOrganizationService;
import com.negoreserva.internal.admin.feature.organization.util.AdminOrganizationRouteNamed;
import com.negoreserva.internal.admin.feature.permission.enums.AdminPermissionData;
import com.negoreserva.internal.admin.component.AdminControlAccess;
import com.negoreserva.common.feature.concrete.organization.dto.request.OrganizationRequest;
import com.negoreserva.common.feature.concrete.organization.dto.response.OrganizationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(AdminOrganizationRouteNamed.PATH)
@Tag(name = "Admin - Organization", description = "Endpoints for organizations management")
public class AdminOrganizationController {
    private final AdminControlAccess controlAccess;
    private final AdminOrganizationService service;

    @GetMapping
    @Operation(summary = "Get all organizations")
    public ResponseEntity<OrganizationPaginate> findAll(@ParameterObject Pageable page, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_ORGANIZATION, authentication);
        return ResponseEntity.ok(OrganizationPaginate.of(service.findAll(page)));
    }

    @GetMapping(AdminOrganizationRouteNamed.FILTER)
    @Operation(summary = "Get organizations by filter")
    public ResponseEntity<OrganizationPaginate> findByFilter(@ParameterObject @ModelAttribute OrganizationFilterQueryParam filter, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_ORGANIZATION, authentication);
        return ResponseEntity.ok(OrganizationPaginate.of(service.findAll(filter)));
    }

    @GetMapping(AdminOrganizationRouteNamed.FIND_BY_NAME)
    @Operation(summary = "Get organization by name")
    public ResponseEntity<OrganizationResponse> findByName(@PathVariable String name, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_ORGANIZATION, authentication);
        var organization = service.findByName(name);
        return ResponseEntity.ok(OrganizationResponse.of(organization));
    }

    @GetMapping(AdminOrganizationRouteNamed.FIND_BY_PHONE)
    @Operation(summary = "Get organization by phone")
    public ResponseEntity<OrganizationResponse> findByPhone(@PathVariable String phone, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_ORGANIZATION, authentication);
        var organization = service.findByPhone(phone);
        return ResponseEntity.ok(OrganizationResponse.of(organization));
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "Get organization by uuid")
    public ResponseEntity<OrganizationResponse> findByUuid(@PathVariable UUID uuid, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_ORGANIZATION, authentication);
        var organization = service.findByUuid(uuid);
        return ResponseEntity.ok(OrganizationResponse.of(organization));
    }

    @PostMapping
    @Operation(summary = "Create organization")
    public ResponseEntity<OrganizationResponse> save(@RequestBody @Valid OrganizationRequest organizationDto, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.CREATE_ORGANIZATION, authentication);
        var organization = service.save(organizationDto.toModel());
        return new ResponseEntity<>(OrganizationResponse.of(organization), HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    @Operation(summary = "Update organization")
    public ResponseEntity<OrganizationResponse> update(@PathVariable UUID uuid, @RequestBody @Valid OrganizationRequest organizationDto, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.UPDATE_ORGANIZATION, authentication);
        var organization = service.update(uuid, organizationDto.toModel());
        return new ResponseEntity<>(OrganizationResponse.of(organization), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "Delete organization by uuid")
    public ResponseEntity<Void> deleteByUuid(@PathVariable UUID uuid, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.DELETE_ORGANIZATION, authentication);
        service.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}