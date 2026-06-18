package com.negoreserva.common.feature.general.user_delete_account.api.rest;

import com.negoreserva.common.feature.general.user_delete_account.dto.request.UserDeleteAccountConfirmRequest;
import com.negoreserva.common.feature.general.user_delete_account.dto.request.UserDeleteAccountRequest;
import com.negoreserva.common.feature.general.user_delete_account.dto.response.UserDeleteAccountResponse;
import com.negoreserva.common.feature.general.user_delete_account.service.UserDeleteAccountFacade;
import com.negoreserva.common.feature.general.user_delete_account.util.UserDeleteAccountRouteNamed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(UserDeleteAccountRouteNamed.PATH)
public class UserDeleteAccountEndpoint {
    private final UserDeleteAccountFacade userDeleteAccountFacade;

    @PostMapping
    public ResponseEntity<UserDeleteAccountResponse> sendMessageDeleteAccount(@RequestBody @Valid UserDeleteAccountRequest request) {
        return ResponseEntity.ok(userDeleteAccountFacade.sendMessageDeleteAccount(request));
    }

    @PutMapping
    public ResponseEntity<Boolean> confirmDeleteAccount(@RequestBody @Valid UserDeleteAccountConfirmRequest request) {
        return ResponseEntity.ok(userDeleteAccountFacade.confirmDeleteAccount(request));
    }
}
