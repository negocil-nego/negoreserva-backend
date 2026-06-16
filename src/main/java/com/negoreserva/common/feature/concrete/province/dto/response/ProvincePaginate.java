package com.negoreserva.common.feature.concrete.province.dto.response;

import com.negoreserva.common.feature.concrete.province.model.Province;
import com.negoreserva.common.feature.core.dto.response.PageResponse;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor
public class ProvincePaginate extends PageResponse<ProvinceResponse> {
    public ProvincePaginate(
            List<ProvinceResponse> content, boolean empty, boolean first, boolean last,
            int number, int numberOfElements, int size, long totalElements, int totalPages
    ) {
        super(content, empty, first, last, number, numberOfElements, size, totalElements, totalPages);
    }

    public static ProvincePaginate of(Page<Province> page) {
        return new ProvincePaginate(
                page.getContent().stream().map(ProvinceResponse::of).toList(),
                page.isEmpty(), page.isFirst(), page.isLast(),
                page.getNumber(), page.getNumberOfElements(), page.getSize(),
                page.getTotalElements(), page.getTotalPages()
        );
    }
}
