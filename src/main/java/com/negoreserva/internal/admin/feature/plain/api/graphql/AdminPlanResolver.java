package com.negoreserva.internal.admin.feature.plain.api.graphql;

import com.negoreserva.internal.admin.feature.permission.enums.AdminPermissionData;
import com.negoreserva.internal.admin.feature.plain.dto.queryparam.PlanFilterQueryParam;
import com.negoreserva.internal.admin.feature.plain.dto.request.PlanRequest;
import com.negoreserva.internal.admin.feature.plain.dto.response.PlanPaginate;
import com.negoreserva.internal.admin.feature.plain.dto.response.PlanResponse;
import com.negoreserva.internal.admin.feature.plain.service.PlanService;
import com.negoreserva.internal.admin.component.AdminControlAccess;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import jakarta.validation.Valid;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class AdminPlanResolver {
    private final AdminControlAccess controlAccess;
    private final PlanService service;

    public AdminPlanResolver(AdminControlAccess controlAccess, PlanService service) {
        this.controlAccess = controlAccess;
        this.service = service;
    }

    @QueryMapping
    public PlanResponse adminFindByUuidPlan(@Argument UUID uuid, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_PLAIN, authentication);
        return service.findByUuid(uuid).toResponse();
    }

    @QueryMapping
    public PlanPaginate adminPaginatePlan(@Argument PaginateRequest paginateRequest, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_PLAIN, authentication);
        return PlanPaginate.of(service.findAll(paginateRequest));
    }

    @QueryMapping
    public PlanPaginate adminPaginatePlanFilter(@Argument PlanFilterQueryParam filter, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_PLAIN, authentication);
        return PlanPaginate.of(service.findAll(filter));
    }

    @MutationMapping
    public PlanResponse adminSavePlan(@Argument @Valid PlanRequest planRequest, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.CREATE_PLAIN, authentication);
        return service.save(planRequest.toModel()).toResponse();
    }

    @MutationMapping
    public PlanResponse adminUpdatePlan(@Argument UUID uuid, @Argument @Valid PlanRequest planRequest, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.UPDATE_PLAIN, authentication);
        return service.update(uuid, planRequest.toModel()).toResponse();
    }

    @MutationMapping
    public boolean adminDeleteByUuidPlan(@Argument UUID uuid, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.DELETE_PLAIN, authentication);
        service.deleteByUuid(uuid);
        return true;
    }
}
