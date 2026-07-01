package com.negoreserva.internal.organization.feature.payment.api.graphql;

import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.concrete.payment.dto.request.PaymentRequest;
import com.negoreserva.common.feature.concrete.payment.model.Payment;
import com.negoreserva.internal.organization.feature.payment.dto.queryparam.PaymentFilterQueryParam;
import com.negoreserva.internal.organization.feature.payment.dto.response.OrgPaymentPaginate;
import com.negoreserva.internal.organization.feature.payment.dto.response.OrgPaymentResponse;
import com.negoreserva.internal.organization.feature.payment.service.OrgPaymentService;
import com.negoreserva.internal.organization.feature.permission.enums.OrgPermissionData;
import com.negoreserva.internal.organization.component.OrgControlAccess;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class OrgPaymentResolver {
    private final OrgControlAccess controlAccess;
    private final OrgPaymentService service;

    @QueryMapping
    public OrgPaymentResponse orgFindByUuidPayment(@Argument UUID uuid, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_ORGANIZATION, authentication);
        return OrgPaymentResponse.toResponse(service.findByUuid(uuid));
    }

    @QueryMapping
    public OrgPaymentPaginate orgPaginatePayment(@Argument PaginateRequest paginateRequest, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_ORGANIZATION, authentication);
        Page<Payment> result = service.findAll(PageRequest.of(paginateRequest.pageNumber(), paginateRequest.pageSize()));
        return OrgPaymentPaginate.of(result);
    }

    @QueryMapping
    public OrgPaymentPaginate orgPaginatePaymentFilter(@Argument PaymentFilterQueryParam filter, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.READ_ORGANIZATION, authentication);
        Page<Payment> result = service.findAll(filter);
        return OrgPaymentPaginate.of(result);
    }

    @MutationMapping
    public OrgPaymentResponse orgSavePayment(@Argument @Valid PaymentRequest paymentRequest, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.UPDATE_ORGANIZATION, authentication);
        var saved = service.create(paymentRequest);
        return OrgPaymentResponse.toResponse(saved);
    }

    @MutationMapping
    public OrgPaymentResponse orgUpdatePayment(@Argument UUID uuid, @Argument @Valid PaymentRequest paymentRequest, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.UPDATE_ORGANIZATION, authentication);
        var payment = service.create(paymentRequest);
        var updated = service.update(uuid, payment);
        return OrgPaymentResponse.toResponse(updated);
    }

    @MutationMapping
    public OrgPaymentResponse orgValidatePaymentReceipt(@Argument UUID uuid, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.UPDATE_ORGANIZATION, authentication);
        var updated = service.validateReceipt(uuid);
        return OrgPaymentResponse.toResponse(updated);
    }

    @MutationMapping
    public boolean orgDeleteByUuidPayment(@Argument UUID uuid, Authentication authentication) {
        controlAccess.canPermission(OrgPermissionData.DELETE_ORGANIZATION, authentication);
        service.deleteByUuid(uuid);
        return true;
    }
}
