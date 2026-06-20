package com.negoreserva.common.feature.general.me.api.rest;

import com.negoreserva.common.feature.general.me.dto.response.AccountSituationResponse;
import com.negoreserva.common.feature.general.me.service.MeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/me")
public class MeEndpoint {
    private final MeService service;

    @GetMapping
    public List<AccountSituationResponse> me(Authentication authentication) {
        return service.accountSituations(authentication);
    }
}
