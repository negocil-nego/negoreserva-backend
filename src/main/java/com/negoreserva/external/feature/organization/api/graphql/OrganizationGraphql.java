package com.negoreserva.external.feature.organization.api.graphql;

import com.negoreserva.common.feature.concrete.organization.dto.queryparam.OrganizationSearchFilterParam;
import com.negoreserva.common.feature.concrete.organization.dto.response.OrganizationResponse;
import com.negoreserva.external.feature.organization.dto.response.OrganizationDetailResponse;
import com.negoreserva.external.feature.organization.service.ExOrganizationService;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class OrganizationGraphql {
    private final ExOrganizationService service;

    @QueryMapping
    public OrganizationDetailResponse pubOrganizationDetail(@Argument String uuidOrSlug) {
        var organization = service.findByUuidOrSlug(uuidOrSlug);
        return OrganizationDetailResponse.of(organization);
    }

    @QueryMapping
    public Page<OrganizationResponse> pubSearchOrganization(
            @Argument String q,
            @Argument PaginateRequest paginateRequest
    ) {
        return service.search(q, PageRequest.of(paginateRequest.pageNumber(), paginateRequest.pageSize()));
    }

    @QueryMapping
    public Page<OrganizationResponse> pubSearchOrganizationFilter(
            @Argument OrganizationSearchFilterParam filter,
            @Argument PaginateRequest paginateRequest
    ) {
        return service.search(filter, PageRequest.of(paginateRequest.pageNumber(), paginateRequest.pageSize()));
    }
}
