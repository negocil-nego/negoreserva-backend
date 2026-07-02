package com.negoreserva.common.feature.concrete.user.dto.response;

import com.negoreserva.common.feature.core.enums.OtpVerificationType;

import java.time.Instant;

public record UserUpdateDataResponse(
        long id,
        Long userId,
        String input,
        String code,
        OtpVerificationType type,
        Instant expiredAt
) { }
