package com.negoreserva.common.feature.general.user_delete_account.api.graphql;

import com.negoreserva.common.feature.general.user_delete_account.dto.request.UserDeleteAccountConfirmRequest;
import com.negoreserva.common.feature.general.user_delete_account.dto.request.UserDeleteAccountRequest;
import com.negoreserva.common.feature.general.user_delete_account.dto.response.UserDeleteAccountResponse;
import com.negoreserva.common.feature.general.user_delete_account.service.UserDeleteAccountFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserDeleteAccountGraphql {
    private final UserDeleteAccountFacade userDeleteAccountFacade;

    @MutationMapping
    public UserDeleteAccountResponse pubSendMessageDeleteAccount(@Argument UserDeleteAccountRequest request) {
        return userDeleteAccountFacade.sendMessageDeleteAccount(request);
    }

    @MutationMapping
    public boolean pubConfirmDeleteAccount(@Argument UserDeleteAccountConfirmRequest request) {
        return userDeleteAccountFacade.confirmDeleteAccount(request);
    }
}
