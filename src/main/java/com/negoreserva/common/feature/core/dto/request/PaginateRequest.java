package com.negoreserva.common.feature.core.dto.request;

import org.springframework.data.domain.PageRequest;

public record PaginateRequest (
        int pageNumber,
        int pageSize
) {
    public PaginateRequest() {
        this(0, 20);
    }

    public PageRequest toPageRequest() {
        return PageRequest.of(pageNumber, pageSize);
    }
}
