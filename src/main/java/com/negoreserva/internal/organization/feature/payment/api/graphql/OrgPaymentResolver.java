package com.negoreserva.internal.organization.feature.payment.api.graphql;

import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.concrete.payment.dto.request.PaymentRequest;
import com.negoreserva.internal.organization.feature.payment.dto.queryparam.PaymentFilterQueryParam;
import com.negoreserva.internal.organization.feature.payment.dto.response.OrgPaymentPaginate;
import com.negoreserva.internal.organization.feature.payment.dto.response.OrgPaymentResponse;
import com.negoreserva.internal.organization.feature.payment.service.OrgPaymentService;
import jakarta.validation.Valid;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class OrgPaymentResolver {

    private final OrgPaymentService service;

    public OrgPaymentResolver(OrgPaymentService service) {
        this.service = service;
    }

    @QueryMapping
    public OrgPaymentResponse orgFindByUuidPayment(@Argument UUID uuid) {
        return OrgPaymentResponse.toResponse(service.findByUuid(uuid));
    }

    @QueryMapping
    public OrgPaymentPaginate orgPaginatePayment(@Argument PaginateRequest paginateRequest) {
        return service.findAll(
                org.springframework.data.domain.PageRequest.of(paginateRequest.pageNumber(), paginateRequest.pageSize())
        );
    }

    @QueryMapping
    public OrgPaymentPaginate orgPaginatePaymentFilter(@Argument PaymentFilterQueryParam filter) {
        return service.findAll(filter);
    }

    @MutationMapping
    public OrgPaymentResponse orgSavePayment(@Argument @Valid PaymentRequest paymentRequest) {
        var saved = service.create(paymentRequest);
        return OrgPaymentResponse.toResponse(saved);
    }

    @MutationMapping
    public OrgPaymentResponse orgUpdatePayment(@Argument UUID uuid, @Argument @Valid PaymentRequest paymentRequest) {
        var payment = service.create(paymentRequest);
        var updated = service.update(uuid, payment);
        return OrgPaymentResponse.toResponse(updated);
    }

    @MutationMapping
    public OrgPaymentResponse orgValidatePaymentReceipt(@Argument UUID uuid) {
        var updated = service.validateReceipt(uuid);
        return OrgPaymentResponse.toResponse(updated);
    }

    @MutationMapping
    public boolean orgDeleteByUuidPayment(@Argument UUID uuid) {
        service.deleteByUuid(uuid);
        return true;
    }
}
