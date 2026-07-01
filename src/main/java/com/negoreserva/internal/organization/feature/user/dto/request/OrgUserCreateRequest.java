package com.negoreserva.internal.organization.feature.user.dto.request;

import com.negoreserva.common.feature.concrete.user.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record OrgUserCreateRequest(
        @NotBlank @Size(max = 255) String name,
        @NotBlank @Email String email,
        @NotBlank @Pattern(regexp = "^\\+?[0-9]{7,15}$") String phone,
        String password,
        List<UUID> roleUuids
) {
    public User toModel() {
        return User.builder()
                .name(name)
                .email(email)
                .phone(phone)
                .password(password)
                .build();
    }
}
