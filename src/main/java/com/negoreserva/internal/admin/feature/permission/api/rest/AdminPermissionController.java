package com.negoreserva.internal.admin.feature.permission.api.rest;

import com.negoreserva.common.feature.concrete.permission.dto.request.PermissionRequest;
import com.negoreserva.common.feature.concrete.permission.dto.response.PermissionPaginate;
import com.negoreserva.common.feature.concrete.permission.dto.response.PermissionResponse;
import com.negoreserva.internal.admin.feature.permission.service.AdminPermissionService;
import com.negoreserva.internal.admin.feature.permission.util.AdminPermissionRouteNamed;
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
@RequestMapping(AdminPermissionRouteNamed.PATH)
public class AdminPermissionController {
    private final AdminPermissionService service;

    @GetMapping
    public ResponseEntity<PermissionPaginate> findAll(@ParameterObject Pageable page) {
        return ResponseEntity.ok(service.findAll(page));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<PermissionResponse> findByUuid(@PathVariable UUID uuid) {
        return ResponseEntity.ok(service.findByUuid(uuid).toResponse());
    }

    @PostMapping
    public ResponseEntity<PermissionResponse> save(@RequestBody @Valid PermissionRequest request) {
        return new ResponseEntity<>(service.save(request.toModel()).toResponse(), HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<PermissionResponse> update(@PathVariable UUID uuid, @RequestBody @Valid PermissionRequest request) {
        return new ResponseEntity<>(service.update(uuid, request.toModel()).toResponse(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteByUuid(@PathVariable UUID uuid) {
        service.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
