package com.negoreserva.internal.admin.feature.address.api.rest;

import com.negoreserva.common.feature.concrete.address.dto.queryparam.AddressFilterQueryParam;
import com.negoreserva.common.feature.concrete.address.dto.request.AddressRequest;
import com.negoreserva.common.feature.concrete.address.dto.response.AddressPaginate;
import com.negoreserva.common.feature.concrete.address.dto.response.AddressResponse;
import com.negoreserva.common.feature.concrete.address.model.Address;
import com.negoreserva.common.feature.concrete.address.service.AddressService;
import com.negoreserva.internal.admin.feature.address.util.AddressRouteNamed;
import com.negoreserva.internal.admin.feature.permission.enums.AdminPermissionData;
import com.negoreserva.internal.admin.component.AdminControlAccess;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(AddressRouteNamed.PATH)
@Tag(name = "Admin - Address", description = "Endpoints for addresses management")
public class AdminAddressController {
    private final AdminControlAccess controlAccess;
    private final AddressService service;

    @GetMapping
    @Operation(summary = "Get all addresses")
    public ResponseEntity<AddressPaginate> findAll(@ParameterObject Pageable page, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_ADDRESS, authentication);
        return ResponseEntity.ok(AddressPaginate.of(service.findAll(page)));
    }

    @GetMapping(AddressRouteNamed.FILTER)
    @Operation(summary = "Get addresses by filter")
    public ResponseEntity<AddressPaginate> findByFilter(@ParameterObject @ModelAttribute AddressFilterQueryParam filter, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_ADDRESS, authentication);
        return ResponseEntity.ok(service.findAll(filter));
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "Get address by uuid")
    public ResponseEntity<AddressResponse> findByUuid(@PathVariable UUID uuid, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_ADDRESS, authentication);
        return ResponseEntity.ok(service.findByUuid(uuid).toResponse());
    }

    @PostMapping
    @Operation(summary = "Create address")
    public ResponseEntity<AddressResponse> save(@RequestBody @Valid AddressRequest addressDto, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.CREATE_ADDRESS, authentication);
        Address address = service.save(addressDto.toModel());
        return new ResponseEntity<>(address.toResponse(), HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    @Operation(summary = "Update address")
    public ResponseEntity<AddressResponse> update(@PathVariable UUID uuid, @RequestBody @Valid AddressRequest addressDto, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.UPDATE_ADDRESS, authentication);
        Address address = service.update(uuid, addressDto.toModel());
        return new ResponseEntity<>(address.toResponse(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "Delete address by uuid")
    public ResponseEntity<Void> deleteByUuid(@PathVariable UUID uuid,  Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.DELETE_ADDRESS, authentication);
        service.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
