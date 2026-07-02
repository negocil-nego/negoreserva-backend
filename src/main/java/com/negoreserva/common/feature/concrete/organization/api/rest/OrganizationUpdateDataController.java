package com.negoreserva.common.feature.concrete.organization.api.rest;

import com.negoreserva.common.feature.concrete.organization.dto.response.OrganizationDataChangeRequestResponse;
import com.negoreserva.common.feature.concrete.organization.model.OrganizationUpdateData;
import com.negoreserva.common.feature.concrete.organization.service.OrganizationUpdateDataService;
import com.negoreserva.common.feature.concrete.organization.util.OrganizationUpdateDataRouteNamed;
import com.negoreserva.common.feature.concrete.user.dto.request.SendEmailRequest;
import com.negoreserva.common.feature.concrete.user.dto.request.SendPhoneRequest;
import com.negoreserva.common.feature.core.dto.request.UpdateDataRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Org - Organization/Update data")
@RequestMapping(OrganizationUpdateDataRouteNamed.PATH)
public class OrganizationUpdateDataController {
    private final OrganizationUpdateDataService service;

    @PostMapping(OrganizationUpdateDataRouteNamed.SEND_EMAIL)
    @Operation(summary = "Send OTP to email for updating organization sensitive data")
    public ResponseEntity<OrganizationDataChangeRequestResponse> sendMessageEmail(@RequestBody @Valid SendEmailRequest request, Authentication authentication) {
        return ResponseEntity.ok(service.sendMessage(request, authentication).toResponse());
    }

    @PostMapping(OrganizationUpdateDataRouteNamed.SEND_PHONE)
    @Operation(summary = "Send OTP to phone for updating organization sensitive data")
    public ResponseEntity<OrganizationDataChangeRequestResponse> sendMessagePhone(@RequestBody @Valid SendPhoneRequest request, Authentication authentication) {
        return ResponseEntity.ok(service.sendMessage(request, authentication).toResponse());
    }

    @PutMapping(OrganizationUpdateDataRouteNamed.RESET)
    @Operation(summary = "Reset organization sensitive data with OTP confirmation")
    public ResponseEntity<Boolean> reset(@RequestBody @Valid UpdateDataRequest request, Authentication authentication) {
        return ResponseEntity.ok(service.reset(request, authentication));
    }

    @GetMapping
    @Operation(summary = "List all organization update data requests")
    public ResponseEntity<List<OrganizationDataChangeRequestResponse>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(OrganizationUpdateData::toResponse).toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find organization update data by ID")
    public ResponseEntity<OrganizationDataChangeRequestResponse> findById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id).toResponse());
    }
}
