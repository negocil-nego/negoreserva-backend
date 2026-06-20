package com.negoreserva.internal.admin.feature.plain.service;

import com.negoreserva.internal.admin.feature.plain.dto.queryparam.PlanFilterQueryParam;
import com.negoreserva.internal.admin.feature.plain.dto.response.PlanPaginate;
import com.negoreserva.internal.admin.feature.plain.exception.PlanNameNotFoundException;
import com.negoreserva.internal.admin.feature.plain.exception.PlanNotFoundException;
import com.negoreserva.internal.admin.feature.plain.query.PlanFilterSpecification;
import com.negoreserva.internal.admin.feature.plain.repository.AdminPlanRepo;
import com.negoreserva.internal.admin.feature.plain.model.Plan;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.core.service.ConcreteService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PlanService extends ConcreteService<Plan> {
    private final AdminPlanRepo repository;

    public PlanService(AdminPlanRepo repository) {
        super(repository);
        this.repository = repository;
    }

    public PlanPaginate findAll(Pageable pageable) {
        var page = repository.findAll(pageable);
        return PlanPaginate.of(page);
    }

    public PlanPaginate findAll(PaginateRequest paginateRequest) {
        return this.findAll(PageRequest.of(paginateRequest.pageNumber(), paginateRequest.pageSize()));
    }

    public PlanPaginate findAll(PlanFilterQueryParam filter) {
        var pageRequest = PageRequest.of(
                Optional.of(filter.getPageNumber()).orElse(0),
                Optional.of(filter.getPageSize()).orElse(10)
        );
        var spec = new PlanFilterSpecification(filter);
        var page = findAll(spec, pageRequest);
        return PlanPaginate.of(page);
    }

    public Plan findByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new PlanNameNotFoundException(name));
    }

    @Override
    public Plan findByUuid(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(() -> new PlanNotFoundException(uuid));
    }

    @Override
    public Plan update(UUID uuid, Plan plan) {
        var item = findByUuid(uuid);
        item.setDescription(plan.getDescription());
        item.setName(plan.getName());
        item.setPrice(plan.getPrice());
        item.setType(plan.getType());
        return repository.save(item);
    }

}
