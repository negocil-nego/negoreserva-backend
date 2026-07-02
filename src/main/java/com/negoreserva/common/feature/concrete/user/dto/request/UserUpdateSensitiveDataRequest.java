package com.negoreserva.common.feature.concrete.user.dto.request;

import com.negoreserva.common.feature.core.enums.OtpVerificationType;
import com.negoreserva.common.feature.concrete.user.model.UserUpdateSensitiveData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.time.LocalDateTime;

public record UserUpdateSensitiveDataRequest(
        @NotNull
        Long userId,
        @NotBlank
        String input,
        @NotBlank
        String code,
        @NotNull
        OtpVerificationType type,
        @NotNull
        Instant expiredAt
) {
    public UserUpdateSensitiveData toModel() {
        return UserUpdateSensitiveData.builder()
                .code(code)
                .type(type)
                .expiredAt(expiredAt)
                .input(input)
                .build();
    }
}
