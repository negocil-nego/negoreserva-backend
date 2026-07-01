package com.negoreserva.internal.organization.feature.user.api.graphql;

import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.internal.admin.feature.role.dto.response.RoleResponse;
import com.negoreserva.internal.organization.feature.permission.enums.OrgPermissionData;
import com.negoreserva.internal.organization.feature.user.dto.request.OrgUserCreateRequest;
import com.negoreserva.internal.organization.feature.user.dto.request.OrgUserUpdateRequest;
import com.negoreserva.internal.organization.feature.user.dto.response.OrgUserPaginate;
import com.negoreserva.internal.organization.feature.user.dto.response.OrgUserResponse;
import com.negoreserva.internal.organization.feature.user.service.OrgUserService;
import com.negoreserva.internal.organization.component.OrgControlAccess;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class OrgUserResolver {
    private final OrgControlAccess controlAccess;
    private final OrgUserService service;

    @QueryMapping
    public OrgUserPaginate orgPaginatePerson(@Argument PaginateRequest paginateRequest, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_USER, authentication);
        return service.paginate(paginateRequest, authentication);
    }

    @QueryMapping
    public OrgUserResponse orgFindByUuidPerson(@Argument String uuid, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_USER, authentication);
        var user = service.findByUuid(UUID.fromString(uuid));
        var roles = service.getUserRoles(user.getUuid()).stream()
                .map(OrgRole -> new RoleResponse(OrgRole.getUuid(), OrgRole.getName()))
                .toList();
        return OrgUserResponse.of(user, roles);
    }

    @MutationMapping
    public OrgUserResponse orgSavePerson(@Argument @Valid OrgUserCreateRequest personCreateRequest, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.CREATE_USER, authentication);
        var user = service.create(personCreateRequest.toModel(), personCreateRequest.roleUuids(), authentication);
        var roles = service.getUserRoles(user.getUuid()).stream()
                .map(OrgRole -> new RoleResponse(OrgRole.getUuid(), OrgRole.getName()))
                .toList();
        return OrgUserResponse.of(user, roles);
    }

    @MutationMapping
    public OrgUserResponse orgUpdatePerson(@Argument String uuid, @Argument @Valid OrgUserUpdateRequest personUpdateRequest, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.UPDATE_USER, authentication);
        var user = service.update(UUID.fromString(uuid), personUpdateRequest.toModel(), personUpdateRequest.roleUuids());
        var roles = service.getUserRoles(user.getUuid()).stream()
                .map(OrgRole -> new RoleResponse(OrgRole.getUuid(), OrgRole.getName()))
                .toList();
        return OrgUserResponse.of(user, roles);
    }

    @MutationMapping
    public boolean orgDeleteByUuidPerson(@Argument String uuid, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.DELETE_USER, authentication);
        service.deleteByUuid(UUID.fromString(uuid));
        return true;
    }

    @QueryMapping
    public OrgPersonRoles orgGetPersonRoles(@Argument UUID userUuid, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_USER, authentication);
        var user = service.findByUuid(userUuid);
        var assignedRoles = service.getUserRoles(user.getUuid());
        var allOrgRoles = service.getOrgRoles(authentication);

        var assignedUuids = assignedRoles.stream()
                .map(OrgRole -> OrgRole.getUuid().toString())
                .collect(java.util.stream.Collectors.toSet());

        var assignedResponses = assignedRoles.stream()
                .map(OrgRole -> new RoleResponse(OrgRole.getUuid(), OrgRole.getName()))
                .toList();

        var availableResponses = allOrgRoles.stream()
                .filter(OrgRole -> !assignedUuids.contains(OrgRole.getUuid().toString()))
                .map(OrgRole -> new RoleResponse(OrgRole.getUuid(), OrgRole.getName()))
                .toList();

        return new OrgPersonRoles(assignedResponses, availableResponses);
    }

    public record OrgPersonRoles(List<RoleResponse> assignedRoles, List<RoleResponse> availableRoles) {}
}
