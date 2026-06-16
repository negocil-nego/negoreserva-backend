package com.negoreserva.common.feature.concrete.plan.service;

import com.negoreserva.internal.admin.feature.plan.dto.queryparam.PlanFilterQueryParam;
import com.negoreserva.internal.admin.feature.plan.dto.response.PlanPaginate;
import com.negoreserva.common.feature.concrete.plan.exception.notfound.PlanNameNotFoundException;
import com.negoreserva.common.feature.concrete.plan.exception.notfound.PlanNotFoundException;
import com.negoreserva.common.feature.concrete.plan.query.PlanFilterSpecification;
import com.negoreserva.common.feature.concrete.plan.repository.PlanRepo;
import com.negoreserva.common.feature.concrete.plan.model.Plan;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.core.service.ConcreteService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PlanService extends ConcreteService<Plan> {
    private final PlanRepo repository;

    public PlanService(PlanRepo repository) {
        super(repository);
        this.repository = repository;
    }

    public PlanPaginate paginate(Pageable pageable) {
        var page = repository.findAll(pageable);
        return PlanPaginate.of(page);
    }

    public PlanPaginate paginate(PaginateRequest paginateRequest) {
        return paginate(PageRequest.of(paginateRequest.pageNumber(), paginateRequest.pageSize()));
    }

    public PlanPaginate paginate(PlanFilterQueryParam filter) {
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
