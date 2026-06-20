package com.negoreserva.internal.admin.feature.permission.api.graphql;

import com.negoreserva.common.feature.concrete.permission.dto.request.PermissionRequest;
import com.negoreserva.common.feature.concrete.permission.dto.response.PermissionPaginate;
import com.negoreserva.common.feature.concrete.permission.dto.response.PermissionResponse;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.internal.admin.feature.permission.service.AdminPermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class AdminPermissionResolver {
    private final AdminPermissionService service;

    @QueryMapping
    public PermissionPaginate orgPaginatePermission(@Argument PaginateRequest paginateRequest) {
        return service.paginate(paginateRequest);
    }

    @QueryMapping
    public PermissionResponse orgFindByUuidPermission(@Argument String uuid) {
        return service.findByUuid(UUID.fromString(uuid)).toResponse();
    }

    @MutationMapping
    public PermissionResponse orgSavePermission(@Argument @Valid PermissionRequest permissionRequest) {
        return service.save(permissionRequest.toModel()).toResponse();
    }

    @MutationMapping
    public PermissionResponse orgUpdatePermission(@Argument String uuid, @Argument @Valid PermissionRequest permissionRequest) {
        return service.update(UUID.fromString(uuid), permissionRequest.toModel()).toResponse();
    }

    @MutationMapping
    public boolean orgDeleteByUuidPermission(@Argument String uuid) {
        service.deleteByUuid(UUID.fromString(uuid));
        return true;
    }
}
