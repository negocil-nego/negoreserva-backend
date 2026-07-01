package com.negoreserva.internal.admin.feature.permission.api.graphql;

import com.negoreserva.common.feature.concrete.permission.dto.request.PermissionRequest;
import com.negoreserva.common.feature.concrete.permission.dto.response.PermissionPaginate;
import com.negoreserva.common.feature.concrete.permission.dto.response.PermissionResponse;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.internal.admin.feature.permission.enums.AdminPermissionData;
import com.negoreserva.internal.admin.feature.permission.service.AdminPermissionService;
import com.negoreserva.internal.admin.component.AdminControlAccess;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class AdminPermissionResolver {
    private final AdminControlAccess controlAccess;
    private final AdminPermissionService service;

    @QueryMapping
    public PermissionPaginate adminPaginatePermission(@Argument PaginateRequest paginateRequest, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_PERMISSION, authentication);
        return service.paginate(paginateRequest);
    }

    @QueryMapping
    public PermissionResponse adminFindByUuidPermission(@Argument String uuid, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_PERMISSION, authentication);
        return service.findByUuid(UUID.fromString(uuid)).toResponse();
    }

    @MutationMapping
    public PermissionResponse adminSavePermission(@Argument @Valid PermissionRequest permissionRequest, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.CREATE_PERMISSION, authentication);
        return service.save(permissionRequest.toModel()).toResponse();
    }

    @MutationMapping
    public PermissionResponse adminUpdatePermission(@Argument String uuid, @Argument @Valid PermissionRequest permissionRequest, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.UPDATE_PERMISSION, authentication);
        return service.update(UUID.fromString(uuid), permissionRequest.toModel()).toResponse();
    }

    @MutationMapping
    public boolean adminDeleteByUuidPermission(@Argument String uuid, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.DELETE_PERMISSION, authentication);
        service.deleteByUuid(UUID.fromString(uuid));
        return true;
    }
}
