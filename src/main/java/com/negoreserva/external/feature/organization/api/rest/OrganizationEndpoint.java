package com.negoreserva.external.feature.organization.api.rest;

import com.negoreserva.external.feature.organization.dto.response.OrganizationDetailResponse;
import com.negoreserva.external.feature.organization.service.ExOrganizationService;
import com.negoreserva.external.feature.organization.util.OrganizationRouteNamed;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(OrganizationRouteNamed.PATH)
public class OrganizationEndpoint {
    private final ExOrganizationService service;

    @GetMapping(OrganizationRouteNamed.FIND_DETAIL)
    @Operation(summary = "Get organization by id")
    public ResponseEntity<OrganizationDetailResponse> findDetail(@PathVariable String uuidOrSlug) {
        var organization = service.findByUuidOrSlug(uuidOrSlug);
        return ResponseEntity.ok(OrganizationDetailResponse.of(organization));
    }
}
