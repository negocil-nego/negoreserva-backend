package com.negoreserva.internal.organization.feature.product.dto.request;

import com.negoreserva.common.feature.concrete.product.dto.request.ProductPriceRequest;
import com.negoreserva.common.feature.concrete.product.dto.request.ProductTagInfoRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record OrgProductCreateRequest(
        @NotBlank
        @Size(max = 100)
        String name,

        @NotBlank
        @Size(max = 255)
        String description,

        @Valid
        List<ProductPriceRequest> prices,

        @Valid
        List<ProductTagInfoRequest> tags
) {}
