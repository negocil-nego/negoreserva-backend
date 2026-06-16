package com.negoreserva.internal.admin.feature.province.api.rest;

import com.negoreserva.common.feature.concrete.province.dto.queryparam.ProvinceFilterQueryParam;
import com.negoreserva.common.feature.concrete.province.dto.response.ProvincePaginate;
import com.negoreserva.common.feature.concrete.province.dto.response.ProvinceResponse;
import com.negoreserva.common.feature.concrete.province.dto.request.ProvinceRequest;
import com.negoreserva.internal.admin.feature.province.service.AdminProvinceService;
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
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(ProvinceRouteNamed.PATH)
@Tag(name = "Admin - Province", description = "Endpoints for province management")
public class AdminProvinceController {

    private final AdminProvinceService service;

    @GetMapping
    @Operation(summary = "Get all provinces")
    public ResponseEntity<ProvincePaginate> findAll(@ParameterObject Pageable page) {
        return ResponseEntity.ok(service.paginate(page));
    }

    @GetMapping(ProvinceRouteNamed.FILTER)
    @Operation(summary = "Get provinces by filter")
    public ResponseEntity<ProvincePaginate> findByFilter(@ParameterObject @ModelAttribute ProvinceFilterQueryParam filter) {
        return ResponseEntity.ok(service.paginate(filter));
    }

    @GetMapping(ProvinceRouteNamed.FIND_BY_VALUE)
    @Operation(summary = "Get province by value")
    public ResponseEntity<ProvinceResponse> findByValue(@PathVariable String value) {
        return ResponseEntity.ok(ProvinceResponse.of(service.findByValue(value)));
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "Get province by uuid")
    public ResponseEntity<ProvinceResponse> findByUuid(@PathVariable UUID uuid) {
        return ResponseEntity.ok(ProvinceResponse.of(service.findByUuid(uuid)));
    }

    @PostMapping
    @Operation(summary = "Create province")
    public ResponseEntity<ProvinceResponse> save(@RequestBody @Valid ProvinceRequest provinceDto) {
        Province province = service.save(provinceDto.toModel());
        return new ResponseEntity<>(ProvinceResponse.of(province), HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    @Operation(summary = "Update province")
    public ResponseEntity<ProvinceResponse> update(@PathVariable UUID uuid, @RequestBody @Valid ProvinceRequest provinceDto) {
        Province province = service.update(uuid, provinceDto.toModel());
        return new ResponseEntity<>(ProvinceResponse.of(province), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "Delete province by uuid")
    public ResponseEntity<Void> deleteByUuid(@PathVariable UUID uuid) {
        service.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
