package com.negoreserva.external.feature.user.dto.response;

import com.negoreserva.common.feature.concrete.user.model.User;

import java.util.UUID;

public record UserBriefResponse(UUID uuid, String name, String email, String phone) {
    public static UserBriefResponse of(User user) {
        return new UserBriefResponse(
                user.getUuid(),
                user.getName(),
                user.getEmail(),
                user.getPhone()
        );
    }
}
