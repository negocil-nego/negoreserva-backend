package com.negoreserva.internal.organization.feature.user.dto.response;

import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.internal.admin.feature.role.dto.response.RoleResponse;

import java.util.List;
import java.util.UUID;

public record OrgUserResponse(UUID uuid, String name, String email, String phone, List<RoleResponse> roles) {
    public static OrgUserResponse of(User user) {
        return new OrgUserResponse(
                user.getUuid(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                List.of()
        );
    }

    public static OrgUserResponse of(User user, List<RoleResponse> roles) {
        return new OrgUserResponse(
                user.getUuid(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                roles
        );
    }
}
