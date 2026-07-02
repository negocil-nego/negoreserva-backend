package com.negoreserva.common.feature.concrete.user.api.rest;

import com.negoreserva.common.feature.concrete.user.dto.request.SendEmailRequest;
import com.negoreserva.common.feature.concrete.user.dto.request.SendPhoneRequest;
import com.negoreserva.common.feature.concrete.user.dto.response.UserUpdateDataResponse;
import com.negoreserva.common.feature.concrete.user.model.UserUpdateSensitiveData;
import com.negoreserva.common.feature.concrete.user.service.UserUpdateSensitiveDataService;
import com.negoreserva.common.feature.concrete.user.util.UserUpdateDataRouteNamed;
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
@Tag(name = "Auth - User/Update data")
@RequestMapping(UserUpdateDataRouteNamed.PATH)
public class UserUpdateDataController {
    private final UserUpdateSensitiveDataService service;

    @PostMapping(UserUpdateDataRouteNamed.SEND_EMAIL)
    @Operation(summary = "Send OTP to email for updating sensitive data")
    public ResponseEntity<UserUpdateDataResponse> sendMessageEmail(@RequestBody @Valid SendEmailRequest request, Authentication authentication) {
        return ResponseEntity.ok(service.sendMessage(request, authentication).toResponse());
    }

    @PostMapping(UserUpdateDataRouteNamed.SEND_PHONE)
    @Operation(summary = "Send OTP to phone for updating sensitive data")
    public ResponseEntity<UserUpdateDataResponse> sendMessagePhone(@RequestBody @Valid SendPhoneRequest request, Authentication authentication) {
        return ResponseEntity.ok(service.sendMessage(request, authentication).toResponse());
    }

    @PutMapping(UserUpdateDataRouteNamed.RESET)
    @Operation(summary = "Reset sensitive data with OTP confirmation")
    public ResponseEntity<Boolean> reset(@RequestBody @Valid UpdateDataRequest request, Authentication authentication) {
        return ResponseEntity.ok(service.reset(request, authentication));
    }

    @GetMapping
    @Operation(summary = "List all user update data requests")
    public ResponseEntity<List<UserUpdateDataResponse>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(UserUpdateSensitiveData::toResponse).toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find user update data by ID")
    public ResponseEntity<UserUpdateDataResponse> findById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id).toResponse());
    }
}
