package com.negoreserva.internal.organization.feature.payment.api.rest;

import com.negoreserva.common.feature.concrete.payment.dto.request.PaymentRequest;
import com.negoreserva.internal.organization.feature.payment.dto.queryparam.PaymentFilterQueryParam;
import com.negoreserva.internal.organization.feature.payment.dto.response.OrgPaymentPaginate;
import com.negoreserva.internal.organization.feature.payment.dto.response.OrgPaymentResponse;
import com.negoreserva.internal.organization.feature.payment.service.OrgPaymentService;
import com.negoreserva.internal.organization.feature.payment.util.OrgPaymentRouteNamed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(OrgPaymentRouteNamed.PATH)
@Tag(name = "Org - Payment", description = "Endpoints for payments management")
public class OrgPaymentController {

    private final OrgPaymentService service;

    @GetMapping
    @Operation(summary = "Get all payments")
    public ResponseEntity<OrgPaymentPaginate> findAll(@ParameterObject Pageable page) {
        return ResponseEntity.ok(service.findAll(page));
    }

    @GetMapping(OrgPaymentRouteNamed.FILTER)
    @Operation(summary = "Get payments by filter")
    public ResponseEntity<OrgPaymentPaginate> findByFilter(@ParameterObject @ModelAttribute PaymentFilterQueryParam filter) {
        return ResponseEntity.ok(service.findAll(filter));
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "Get payment by uuid")
    public ResponseEntity<OrgPaymentResponse> findByUuid(@PathVariable UUID uuid) {
        return ResponseEntity.ok(OrgPaymentResponse.toResponse(service.findByUuid(uuid)));
    }

    @PostMapping
    @Operation(summary = "Create payment")
    public ResponseEntity<OrgPaymentResponse> save(@RequestBody @Valid PaymentRequest paymentDto) {
        var saved = service.create(paymentDto);
        return new ResponseEntity<>(OrgPaymentResponse.toResponse(saved), HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    @Operation(summary = "Update payment")
    public ResponseEntity<OrgPaymentResponse> update(@PathVariable UUID uuid, @RequestBody @Valid PaymentRequest paymentDto) {
        var payment = service.create(paymentDto);
        var updated = service.update(uuid, payment);
        return ResponseEntity.ok(OrgPaymentResponse.toResponse(updated));
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "Delete payment by uuid")
    public ResponseEntity<Void> deleteByUuid(@PathVariable UUID uuid) {
        service.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
