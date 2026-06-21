package com.negoreserva.internal.organization.feature.user.api.rest;

import com.negoreserva.internal.organization.feature.permission.enums.OrgPermissionData;
import com.negoreserva.internal.organization.feature.user.dto.request.OrgUserCreateRequest;
import com.negoreserva.internal.organization.feature.user.dto.request.OrgUserUpdateRequest;
import com.negoreserva.internal.organization.feature.user.dto.response.OrgUserPaginate;
import com.negoreserva.internal.organization.feature.user.dto.response.OrgUserResponse;
import com.negoreserva.internal.organization.feature.user.service.OrgUserService;
import com.negoreserva.internal.organization.feature.user.util.OrgUserRouteNamed;
import com.negoreserva.internal.organization.componet.OrgControlAccess;
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
@RequestMapping(OrgUserRouteNamed.PATH)
public class OrgUserController {
    private final OrgControlAccess controlAccess;
    private final OrgUserService service;

    @GetMapping
    public ResponseEntity<OrgUserPaginate> findAll(@ParameterObject Pageable page, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_USER, authentication);
        return ResponseEntity.ok(service.paginate(page, authentication));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<OrgUserResponse> findByUuid(@PathVariable UUID uuid, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_USER, authentication);
        return ResponseEntity.ok(OrgUserResponse.of(service.findByUuid(uuid)));
    }

    @PostMapping
    public ResponseEntity<OrgUserResponse> save(@RequestBody @Valid OrgUserCreateRequest request, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.CREATE_USER, authentication);
        return new ResponseEntity<>(OrgUserResponse.of(service.create(request.toModel(), request.roleUuid(), authentication)), HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<OrgUserResponse> update(@PathVariable UUID uuid, @RequestBody @Valid OrgUserUpdateRequest request, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.UPDATE_USER, authentication);
        return new ResponseEntity<>(OrgUserResponse.of(service.update(uuid, request.toModel())), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteByUuid(@PathVariable UUID uuid, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.DELETE_USER, authentication);
        service.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
