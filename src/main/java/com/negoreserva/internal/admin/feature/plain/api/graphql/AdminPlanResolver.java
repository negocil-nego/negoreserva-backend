package com.negoreserva.internal.admin.feature.plain.api.graphql;

import com.negoreserva.internal.admin.feature.plain.dto.queryparam.PlanFilterQueryParam;
import com.negoreserva.internal.admin.feature.plain.dto.request.PlanRequest;
import com.negoreserva.internal.admin.feature.plain.dto.response.PlanPaginate;
import com.negoreserva.internal.admin.feature.plain.dto.response.PlanResponse;
import com.negoreserva.internal.admin.feature.plain.service.PlanService;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import jakarta.validation.Valid;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class AdminPlanResolver {
    private final PlanService service;

    public AdminPlanResolver(PlanService service) {
        this.service = service;
    }

    @QueryMapping
    public PlanResponse adminFindByUuidPlan(@Argument UUID uuid) {
        return service.findByUuid(uuid).toResponse();
    }

    @QueryMapping
    public PlanPaginate adminPaginatePlan(@Argument PaginateRequest paginateRequest) {
        return service.findAll(paginateRequest);
    }

    @QueryMapping
    public PlanPaginate adminPaginatePlanFilter(@Argument PlanFilterQueryParam filter) {
        return service.findAll(filter);
    }

    @MutationMapping
    public PlanResponse adminSavePlan(@Argument @Valid PlanRequest planRequest) {
        return service.save(planRequest.toModel()).toResponse();
    }

    @MutationMapping
    public PlanResponse adminUpdatePlan(@Argument UUID uuid, @Argument @Valid PlanRequest planRequest) {
        return service.update(uuid, planRequest.toModel()).toResponse();
    }

    @MutationMapping
    public boolean adminDeleteByUuidPlan(@Argument UUID uuid) {
        service.deleteByUuid(uuid);
        return true;
    }
}
