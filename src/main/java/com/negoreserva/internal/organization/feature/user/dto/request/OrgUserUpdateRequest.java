package com.negoreserva.internal.organization.feature.user.dto.request;

import com.negoreserva.common.feature.concrete.user.model.User;

import java.util.List;
import java.util.UUID;

public record OrgUserUpdateRequest(String name, List<UUID> roleUuids) {
    public User toModel() {
        return User.builder().name(name).build();
    }
}
