package com.negoreserva.internal.organization.feature.payment.api.rest;

import com.negoreserva.common.feature.concrete.payment.dto.request.PaymentRequest;
import com.negoreserva.common.feature.concrete.payment.model.Payment;
import com.negoreserva.internal.organization.feature.payment.dto.queryparam.PaymentFilterQueryParam;
import com.negoreserva.internal.organization.feature.payment.dto.response.OrgPaymentPaginate;
import com.negoreserva.internal.organization.feature.payment.dto.response.OrgPaymentResponse;
import com.negoreserva.internal.organization.feature.payment.service.OrgPaymentService;
import com.negoreserva.internal.organization.feature.payment.util.OrgPaymentRouteNamed;
import com.negoreserva.internal.organization.feature.permission.enums.OrgPermissionData;
import com.negoreserva.internal.organization.componet.OrgControlAccess;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(OrgPaymentRouteNamed.PATH)
@Tag(name = "Org - Payment", description = "Endpoints for payments management")
public class OrgPaymentController {
    private final OrgControlAccess controlAccess;
    private final OrgPaymentService service;

    @GetMapping
    @Operation(summary = "Get all payments")
    public ResponseEntity<OrgPaymentPaginate> findAll(@ParameterObject Pageable page, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_ORGANIZATION, authentication);
        Page<Payment> result = service.findAll(page);
        return ResponseEntity.ok(OrgPaymentPaginate.of(result));
    }

    @GetMapping(OrgPaymentRouteNamed.FILTER)
    @Operation(summary = "Get payments by filter")
    public ResponseEntity<OrgPaymentPaginate> findByFilter(@ParameterObject @ModelAttribute PaymentFilterQueryParam filter, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_ORGANIZATION, authentication);
        Page<Payment> result = service.findAll(filter);
        return ResponseEntity.ok(OrgPaymentPaginate.of(result));
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "Get payment by uuid")
    public ResponseEntity<OrgPaymentResponse> findByUuid(@PathVariable UUID uuid, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_ORGANIZATION, authentication);
        return ResponseEntity.ok(OrgPaymentResponse.toResponse(service.findByUuid(uuid)));
    }

    @PostMapping
    @Operation(summary = "Create payment")
    public ResponseEntity<OrgPaymentResponse> save(@RequestBody @Valid PaymentRequest paymentDto, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.UPDATE_ORGANIZATION, authentication);
        var saved = service.create(paymentDto);
        return new ResponseEntity<>(OrgPaymentResponse.toResponse(saved), HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    @Operation(summary = "Update payment")
    public ResponseEntity<OrgPaymentResponse> update(@PathVariable UUID uuid, @RequestBody @Valid PaymentRequest paymentDto, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.UPDATE_ORGANIZATION, authentication);
        var payment = service.create(paymentDto);
        var updated = service.update(uuid, payment);
        return ResponseEntity.ok(OrgPaymentResponse.toResponse(updated));
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "Delete payment by uuid")
    public ResponseEntity<Void> deleteByUuid(@PathVariable UUID uuid, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.DELETE_ORGANIZATION, authentication);
        service.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
