package com.negoreserva.common.feature.concrete.municipality.dto.response;

import com.negoreserva.common.feature.concrete.municipality.model.Municipality;
import com.negoreserva.common.feature.core.dto.response.PageResponse;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor
public class MunicipalityPaginate extends PageResponse<MunicipalityResponse> {
    public MunicipalityPaginate(
            List<MunicipalityResponse> content, boolean empty, boolean first, boolean last,
            int number, int numberOfElements, int size, long totalElements, int totalPages
    ) {
        super(content, empty, first, last, number, numberOfElements, size, totalElements, totalPages);
    }

    public static MunicipalityPaginate of(Page<Municipality> page) {
        return new MunicipalityPaginate(
                page.getContent().stream().map(MunicipalityResponse::of).toList(),
                page.isEmpty(), page.isFirst(), page.isLast(),
                page.getNumber(), page.getNumberOfElements(), page.getSize(),
                page.getTotalElements(), page.getTotalPages()
        );
    }
}
