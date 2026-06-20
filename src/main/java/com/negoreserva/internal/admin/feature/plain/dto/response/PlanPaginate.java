package com.negoreserva.internal.admin.feature.plain.dto.response;

import com.negoreserva.internal.admin.feature.plain.model.Plan;
import com.negoreserva.common.feature.core.dto.response.PageResponse;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor
public class PlanPaginate extends PageResponse<PlanResponse> {

    public PlanPaginate(
            List<PlanResponse> content,
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

    public static PlanPaginate of(Page<Plan> page) {
        return new PlanPaginate(
                page.getContent().stream().map(Plan::toResponse).toList(),
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
