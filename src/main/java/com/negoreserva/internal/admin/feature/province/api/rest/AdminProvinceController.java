package com.negoreserva.internal.admin.feature.province.api.rest;

import com.negoreserva.common.feature.concrete.province.dto.queryparam.ProvinceFilterQueryParam;
import com.negoreserva.common.feature.concrete.province.dto.response.ProvincePaginate;
import com.negoreserva.common.feature.concrete.province.dto.response.ProvinceResponse;
import com.negoreserva.common.feature.concrete.province.dto.request.ProvinceRequest;
import com.negoreserva.internal.admin.feature.permission.enums.AdminPermissionData;
import com.negoreserva.internal.admin.feature.province.service.AdminProvinceService;
import com.negoreserva.internal.admin.component.AdminControlAccess;
import com.negoreserva.common.feature.concrete.province.model.Province;
import com.negoreserva.internal.admin.feature.province.util.ProvinceRouteNamed;
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
@RequestMapping(ProvinceRouteNamed.PATH)
@Tag(name = "Admin - Province", description = "Endpoints for provinceUuid management")
public class AdminProvinceController {

    private final AdminControlAccess controlAccess;
    private final AdminProvinceService service;

    @GetMapping
    @Operation(summary = "Get all provinces")
    public ResponseEntity<ProvincePaginate> findAll(@ParameterObject Pageable page, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_PROVINCE, authentication);
        return ResponseEntity.ok(ProvincePaginate.of(service.findAll(page)));
    }

    @GetMapping(ProvinceRouteNamed.FILTER)
    @Operation(summary = "Get provinces by filter")
    public ResponseEntity<ProvincePaginate> findByFilter(@ParameterObject @ModelAttribute ProvinceFilterQueryParam filter, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_PROVINCE, authentication);
        return ResponseEntity.ok(ProvincePaginate.of(service.findAll(filter)));
    }

    @GetMapping(ProvinceRouteNamed.FIND_BY_VALUE)
    @Operation(summary = "Get provinceUuid by value")
    public ResponseEntity<ProvinceResponse> findByValue(@PathVariable String value, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_PROVINCE, authentication);
        return ResponseEntity.ok(ProvinceResponse.of(service.findByValue(value)));
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "Get provinceUuid by uuid")
    public ResponseEntity<ProvinceResponse> findByUuid(@PathVariable UUID uuid, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_PROVINCE, authentication);
        return ResponseEntity.ok(ProvinceResponse.of(service.findByUuid(uuid)));
    }

    @PostMapping
    @Operation(summary = "Create provinceUuid")
    public ResponseEntity<ProvinceResponse> save(@RequestBody @Valid ProvinceRequest provinceDto, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.CREATE_PROVINCE, authentication);
        Province province = service.save(provinceDto.toModel());
        return new ResponseEntity<>(ProvinceResponse.of(province), HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    @Operation(summary = "Update provinceUuid")
    public ResponseEntity<ProvinceResponse> update(@PathVariable UUID uuid, @RequestBody @Valid ProvinceRequest provinceDto, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.UPDATE_PROVINCE, authentication);
        Province province = service.update(uuid, provinceDto.toModel());
        return new ResponseEntity<>(ProvinceResponse.of(province), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "Delete provinceUuid by uuid")
    public ResponseEntity<Void> deleteByUuid(@PathVariable UUID uuid, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.DELETE_PROVINCE, authentication);
        service.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
