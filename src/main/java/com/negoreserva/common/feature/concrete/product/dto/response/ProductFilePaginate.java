package com.negoreserva.common.feature.concrete.product.dto.response;

import com.negoreserva.common.feature.concrete.product.model.ProductFile;
import com.negoreserva.common.feature.core.dto.response.PageResponse;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor
public class ProductFilePaginate extends PageResponse<ProductFileResponse> {

    public ProductFilePaginate(
            List<ProductFileResponse> content,
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

    public static ProductFilePaginate of(Page<ProductFile> page) {
        return new ProductFilePaginate(
                page.getContent().stream().map(ProductFile::toResponse).toList(),
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