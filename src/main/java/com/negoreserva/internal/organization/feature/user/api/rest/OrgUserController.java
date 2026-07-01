package com.negoreserva.internal.organization.feature.user.api.rest;

import com.negoreserva.internal.organization.feature.permission.enums.OrgPermissionData;
import com.negoreserva.internal.organization.feature.user.dto.request.OrgUserCreateRequest;
import com.negoreserva.internal.organization.feature.user.dto.request.OrgUserUpdateRequest;
import com.negoreserva.internal.organization.feature.user.dto.response.OrgUserPaginate;
import com.negoreserva.internal.organization.feature.user.dto.response.OrgUserResponse;
import com.negoreserva.internal.organization.feature.user.service.OrgUserService;
import com.negoreserva.internal.admin.feature.role.dto.response.RoleResponse;
import com.negoreserva.internal.organization.feature.user.util.OrgUserRouteNamed;
import com.negoreserva.internal.organization.component.OrgControlAccess;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/all")
    public ResponseEntity<List<OrgUserResponse>> findAllUsers(Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_USER, authentication);
        var users = service.listAll(authentication);
        var responses = users.stream()
                .map(user -> OrgUserResponse.of(user, service.getUserRoles(user.getUuid()).stream()
                        .map(r -> new RoleResponse(r.getUuid(), r.getName()))
                        .toList()))
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/all/{slug}")
    public ResponseEntity<List<OrgUserResponse>> findAllUsersBySlug(@PathVariable String slug) {
        var users = service.listAllBySlug(slug);
        var responses = users.stream()
                .map(user -> OrgUserResponse.of(user, service.getUserRoles(user.getUuid()).stream()
                        .map(r -> new RoleResponse(r.getUuid(), r.getName()))
                        .toList()))
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<OrgUserResponse> findByUuid(@PathVariable UUID uuid, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_USER, authentication);
        var user = service.findByUuid(uuid);
        var roles = service.getUserRoles(user.getUuid()).stream()
                .map(r -> new RoleResponse(r.getUuid(), r.getName()))
                .toList();
        return ResponseEntity.ok(OrgUserResponse.of(user, roles));
    }

    @PostMapping
    public ResponseEntity<OrgUserResponse> save(@RequestBody @Valid OrgUserCreateRequest request, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.CREATE_USER, authentication);
        var user = service.create(request.toModel(), request.roleUuids(), authentication);
        var roles = service.getUserRoles(user.getUuid()).stream()
                .map(r -> new RoleResponse(r.getUuid(), r.getName()))
                .toList();
        return new ResponseEntity<>(OrgUserResponse.of(user, roles), HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<OrgUserResponse> update(@PathVariable UUID uuid, @RequestBody @Valid OrgUserUpdateRequest request, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.UPDATE_USER, authentication);
        var user = service.update(uuid, request.toModel(), request.roleUuids());
        var roles = service.getUserRoles(user.getUuid()).stream()
                .map(r -> new RoleResponse(r.getUuid(), r.getName()))
                .toList();
        return new ResponseEntity<>(OrgUserResponse.of(user, roles), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteByUuid(@PathVariable UUID uuid, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.DELETE_USER, authentication);
        service.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
