package com.negoreserva.common.feature.concrete.organization.dto.response;

import com.negoreserva.common.feature.core.enums.OtpVerificationType;

import java.time.Instant;
import java.time.LocalDateTime;

public record OrganizationDataChangeRequestResponse(
        long id,
        Long organizationId,
        String input,
        String code,
        OtpVerificationType type,
        Instant expiredAt
) { }
