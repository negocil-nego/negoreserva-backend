package com.negoreserva.common.feature.concrete.province.dto.request;

import com.negoreserva.common.feature.concrete.province.model.Province;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProvinceRequest(
        @NotBlank @Size(max = 50) String value,
        @NotBlank @Size(max = 100) String label
) {
    public Province toModel() {
        return Province.builder().value(value).label(label).build();
    }
}
