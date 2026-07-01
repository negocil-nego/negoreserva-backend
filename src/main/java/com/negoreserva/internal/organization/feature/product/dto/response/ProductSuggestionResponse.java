package com.negoreserva.internal.organization.feature.product.dto.response;

import java.util.List;

public record ProductSuggestionResponse(
        String categoryType,
        List<String> products,
        List<String> tags
) {}
