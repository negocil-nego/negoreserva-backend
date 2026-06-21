package com.negoreserva.internal.admin.feature.plain.api.rest;

import com.negoreserva.internal.admin.feature.plain.dto.queryparam.PlanFilterQueryParam;
import com.negoreserva.internal.admin.feature.plain.dto.response.PlanPaginate;
import com.negoreserva.internal.admin.feature.plain.dto.request.PlanRequest;
import com.negoreserva.internal.admin.feature.permission.enums.AdminPermissionData;
import com.negoreserva.internal.admin.feature.plain.dto.response.PlanResponse;
import com.negoreserva.internal.admin.feature.plain.service.PlanService;
import com.negoreserva.internal.admin.feature.plain.model.Plan;
import com.negoreserva.internal.admin.feature.plain.util.PlanRouteNamed;
import com.negoreserva.internal.admin.component.AdminControlAccess;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(PlanRouteNamed.PATH)
@Tag(name = "Admin - Plan", description = "Endpoints for plans management")
public class AdminPlanController {

    private final AdminControlAccess controlAccess;
    private final PlanService service;

    @GetMapping
    @Operation(summary = "Get all plans")
    public ResponseEntity<PlanPaginate> findAll(@ParameterObject Pageable page, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_PLAIN, authentication);
        return ResponseEntity.ok(PlanPaginate.of(service.findAll(page)));
    }

    @GetMapping(PlanRouteNamed.FILTER)
    @Operation(summary = "Get plans by filter")
    public ResponseEntity<PlanPaginate> findByFilter(@ParameterObject @ModelAttribute PlanFilterQueryParam filter, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_PLAIN, authentication);
        return ResponseEntity.ok(PlanPaginate.of(service.findAll(filter)));
    }

    @GetMapping(PlanRouteNamed.FIND_BY_NAME)
    @Operation(summary = "Get plan by name")
    public ResponseEntity<PlanResponse> findByName(@PathVariable String name, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_PLAIN, authentication);
        return ResponseEntity.ok(service.findByName(name).toResponse());
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "Get plan by uuid")
    public ResponseEntity<PlanResponse> findByUuid(@PathVariable UUID uuid, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.READ_PLAIN, authentication);
        return ResponseEntity.ok(service.findByUuid(uuid).toResponse());
    }

    @PostMapping
    @Operation(summary = "Create plan")
    public ResponseEntity<PlanResponse> save(@RequestBody @Valid PlanRequest planDto, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.CREATE_PLAIN, authentication);
        Plan plan = service.save(planDto.toModel());
        return new ResponseEntity<>(plan.toResponse(), HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    @Operation(summary = "Update plan")
    public ResponseEntity<PlanResponse> update(@PathVariable UUID uuid, @RequestBody @Valid PlanRequest planDto, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.UPDATE_PLAIN, authentication);
        Plan plan = service.update(uuid, planDto.toModel());
        return new ResponseEntity<>(plan.toResponse(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "Delete plan by uuid")
    public ResponseEntity<Void> deleteByUuid(@PathVariable UUID uuid, Authentication authentication) {
        controlAccess.canPermission(AdminPermissionData.DELETE_PLAIN, authentication);
        service.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
