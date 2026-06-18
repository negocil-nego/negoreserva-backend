package com.negoreserva.common.feature.general.user_delete_account.dto.request;

public record UserDeleteAccountConfirmRequest(
        String input,
        String code,
        String password
) { }
