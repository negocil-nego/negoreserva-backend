package com.negoreserva.common.feature.concrete.organization.dto.response;

import com.negoreserva.common.feature.concrete.organization.model.OrganizationUpdateData;
import com.negoreserva.common.feature.core.dto.response.PageResponse;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor
public class OrganizationDataChangeRequestPaginate extends PageResponse<OrganizationDataChangeRequestResponse> {

    public OrganizationDataChangeRequestPaginate(
            List<OrganizationDataChangeRequestResponse> content,
            boolean empty,
            boolean first,
            boolean last,
            int number,
            int numberOfElements,
            int size,
            long totalElements,
            int totalPages
    ) {
        super(content, empty, first, last, number, numberOfElements, size, totalElements, totalPages);
    }

    public static OrganizationDataChangeRequestPaginate of(Page<OrganizationUpdateData> page) {
        return new OrganizationDataChangeRequestPaginate(
                page.getContent().stream().map(OrganizationUpdateData::toResponse).toList(),
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
