package com.negoreserva.common.feature.concrete.organization.dto.request;

import com.negoreserva.common.feature.concrete.organization.model.OrganizationUpdateData;
import com.negoreserva.common.feature.core.enums.OtpVerificationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record OrganizationSensitiveDataChangeRequestRequest(
        @NotNull
        Long organizationId,
        @NotBlank
        String input,
        @NotBlank
        String code,
        @NotNull
        OtpVerificationType type,
        @NotNull
        Instant expiredAt
) {
    public OrganizationUpdateData toModel() {
        return OrganizationUpdateData.builder()
                .code(code)
                .type(type)
                .expiredAt(expiredAt)
                .input(input)
                .build();
    }
}
