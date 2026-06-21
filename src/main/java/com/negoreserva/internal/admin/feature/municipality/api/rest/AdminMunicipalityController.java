package com.negoreserva.internal.admin.feature.municipality.api.rest;

import com.negoreserva.common.feature.concrete.municipality.dto.queryparam.MunicipalityFilterQueryParam;
import com.negoreserva.common.feature.concrete.municipality.dto.response.MunicipalityPaginate;
import com.negoreserva.common.feature.concrete.municipality.dto.response.MunicipalityResponse;
import com.negoreserva.common.feature.concrete.municipality.dto.request.MunicipalityRequest;
import com.negoreserva.common.feature.concrete.municipality.model.Municipality;
import com.negoreserva.common.feature.concrete.province.exception.notfound.ProvinceNotFoundException;
import com.negoreserva.common.feature.concrete.province.repository.ProvinceRepo;
import com.negoreserva.internal.admin.feature.municipality.service.AdminMunicipalityService;
import com.negoreserva.internal.admin.feature.municipality.util.MunicipalityRouteNamed;
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
@RequestMapping(MunicipalityRouteNamed.PATH)
@Tag(name = "Admin - Municipality", description = "Endpoints for municipalityUuid management")
public class AdminMunicipalityController {

    private final AdminControlAccess controlAccess;
    private final AdminMunicipalityService service;
    private final ProvinceRepo provinceRepo;

    @GetMapping
    @Operation(summary = "Get all municipalities")
    public ResponseEntity<MunicipalityPaginate> findAll(@ParameterObject Pageable page, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_MUNICIPALITY, authentication);
        return ResponseEntity.ok(MunicipalityPaginate.of(service.findAll(page)));
    }

    @GetMapping(MunicipalityRouteNamed.FILTER)
    @Operation(summary = "Get municipalities by filter")
    public ResponseEntity<MunicipalityPaginate> findByFilter(@ParameterObject @ModelAttribute MunicipalityFilterQueryParam filter, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_MUNICIPALITY, authentication);
        return ResponseEntity.ok(MunicipalityPaginate.of(service.findAll(filter)));
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "Get municipalityUuid by uuid")
    public ResponseEntity<MunicipalityResponse> findByUuid(@PathVariable UUID uuid, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_MUNICIPALITY, authentication);
        return ResponseEntity.ok(MunicipalityResponse.of(service.findByUuid(uuid)));
    }

    @PostMapping
    @Operation(summary = "Create municipalityUuid")
    public ResponseEntity<MunicipalityResponse> save(@RequestBody @Valid MunicipalityRequest municipalityDto, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.CREATE_MUNICIPALITY, authentication);
        var province = provinceRepo.findByUuid(municipalityDto.provinceUuid())
                .orElseThrow(() -> new ProvinceNotFoundException(municipalityDto.provinceUuid()));
        Municipality municipality = municipalityDto.toModel();
        municipality.setProvince(province);
        municipality = service.save(municipality);
        return new ResponseEntity<>(MunicipalityResponse.of(municipality), HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    @Operation(summary = "Update municipalityUuid")
    public ResponseEntity<MunicipalityResponse> update(@PathVariable UUID uuid, @RequestBody @Valid MunicipalityRequest municipalityDto, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.UPDATE_MUNICIPALITY, authentication);
        var existing = service.findByUuid(uuid);
        existing.setValue(municipalityDto.value());
        existing.setLabel(municipalityDto.label());
        var province = provinceRepo.findByUuid(municipalityDto.provinceUuid())
                .orElseThrow(() -> new ProvinceNotFoundException(municipalityDto.provinceUuid()));
        existing.setProvince(province);
        existing = service.save(existing);
        return new ResponseEntity<>(MunicipalityResponse.of(existing), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "Delete municipalityUuid by uuid")
    public ResponseEntity<Void> deleteByUuid(@PathVariable UUID uuid, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.DELETE_MUNICIPALITY, authentication);
        service.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
