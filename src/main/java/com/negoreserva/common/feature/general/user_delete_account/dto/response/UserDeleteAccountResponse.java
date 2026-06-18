package com.negoreserva.common.feature.general.user_delete_account.dto.response;

import java.time.Instant;

public record UserDeleteAccountResponse(
        String input,
        Instant expiredAt
) { }
