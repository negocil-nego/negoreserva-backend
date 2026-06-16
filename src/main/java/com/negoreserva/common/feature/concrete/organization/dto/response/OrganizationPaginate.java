package com.negoreserva.common.feature.concrete.organization.dto.response;

import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.core.dto.response.PageResponse;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor
public class OrganizationPaginate extends PageResponse<OrganizationResponse> {

    public OrganizationPaginate(
            List<OrganizationResponse> content,
            boolean empty,
            boolean first,
            boolean last,
            int number,
            int numberOfElements,
            int size,
            long totalElements,
            int totalPages
    ) {
        super(
                content,
                empty,
                first,
                last,
                number,
                numberOfElements,
                size,
                totalElements,
                totalPages
        );
    }

    public static OrganizationPaginate of(Page<Organization> page) {
        return new OrganizationPaginate(
                page.getContent().stream().map(OrganizationResponse::of).toList(),
                page.isEmpty(),
                page.isFirst(),
                page.isLast(),
                page.getNumber(),
                page.getNumberOfElements(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}