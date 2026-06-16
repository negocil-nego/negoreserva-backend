package com.negoreserva.internal.organization.feature.payment.service;

import com.negoreserva.common.feature.core.service.ConcreteService;
import com.negoreserva.common.feature.concrete.payment.dto.request.PaymentRequest;
import com.negoreserva.common.feature.concrete.payment.enums.PaymentStatus;
import com.negoreserva.common.feature.concrete.payment.exception.notfound.PaymentNotFoundException;
import com.negoreserva.common.feature.concrete.payment.model.Payment;
import com.negoreserva.common.feature.concrete.transaction.service.TransactionService;
import com.negoreserva.internal.organization.feature.payment.dto.queryparam.PaymentFilterQueryParam;
import com.negoreserva.internal.organization.feature.payment.dto.response.OrgPaymentPaginate;
import com.negoreserva.internal.organization.feature.payment.query.PaymentFilterSpecification;
import com.negoreserva.internal.organization.feature.payment.repository.OrgPaymentRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrgPaymentService extends ConcreteService<Payment> {

    private final OrgPaymentRepo orgPaymentRepo;
    private final TransactionService transactionService;

    public OrgPaymentService(OrgPaymentRepo repository, TransactionService transactionService) {
        super(repository);
        this.orgPaymentRepo = repository;
        this.transactionService = transactionService;
    }

    @Transactional(readOnly = true)
    public OrgPaymentPaginate paginate(Pageable pageable) {
        var page = orgPaymentRepo.findAll(pageable);
        return OrgPaymentPaginate.of(page);
    }

    @Transactional(readOnly = true)
    public OrgPaymentPaginate paginate(PaymentFilterQueryParam filter) {
        var pageRequest = PageRequest.of(Optional.of(filter.getPageNumber()).orElse(0), Optional.of(filter.getPageSize()).orElse(10));
        var spec = new PaymentFilterSpecification(filter);
        var page = orgPaymentRepo.findAll(spec, pageRequest);
        return OrgPaymentPaginate.of(page);
    }

    @Override
    public Payment findByUuid(UUID uuid) {
        return orgPaymentRepo.findByUuid(uuid).orElseThrow(() -> new PaymentNotFoundException(uuid));
    }

    public Payment create(PaymentRequest request) {
        var transaction = transactionService.findByUuid(request.transactionUuid());
        var payment = request.toModel(transaction);
        return orgPaymentRepo.save(payment);
    }

    public Payment validateReceipt(UUID uuid) {
        var payment = findByUuid(uuid);
        payment.setStatus(PaymentStatus.RECEIPT_VALID);
        return orgPaymentRepo.save(payment);
    }

    @Override
    public Payment update(UUID uuid, Payment payment) {
        var item = findByUuid(uuid);
        item.setTransaction(payment.getTransaction());
        item.setStatus(payment.getStatus());
        item.setType(payment.getType());
        return orgPaymentRepo.save(item);
    }
}
