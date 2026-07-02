package com.negoreserva.common.feature.concrete.organization.dto.response;

import com.negoreserva.common.feature.concrete.organization.model.OrganizationSocialMedia;
import com.negoreserva.common.feature.core.dto.response.PageResponse;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor
public class OrganizationSocialMediaPaginate extends PageResponse<OrganizationSocialMediaResponse> {

    public OrganizationSocialMediaPaginate(
            List<OrganizationSocialMediaResponse> content,
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

    public static OrganizationSocialMediaPaginate of(Page<OrganizationSocialMedia> page) {
        return new OrganizationSocialMediaPaginate(
                page.getContent().stream().map(OrganizationSocialMedia::toResponse).toList(),
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
