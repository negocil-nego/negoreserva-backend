package com.negoreserva.common.feature.concrete.product.dto.response;

import com.negoreserva.common.feature.concrete.product.model.ProductPrice;
import com.negoreserva.common.feature.core.dto.response.PageResponse;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor
public class ProductPricePaginate extends PageResponse<ProductPriceResponse> {

    public ProductPricePaginate(
            List<ProductPriceResponse> content,
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

    public static ProductPricePaginate of(Page<ProductPrice> page) {
        return new ProductPricePaginate(
                page.getContent().stream().map(ProductPrice::toResponse).toList(),
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
