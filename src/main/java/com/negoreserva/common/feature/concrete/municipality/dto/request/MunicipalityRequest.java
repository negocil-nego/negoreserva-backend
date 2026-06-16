package com.negoreserva.common.feature.concrete.municipality.dto.request;

import com.negoreserva.common.feature.concrete.municipality.model.Municipality;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record MunicipalityRequest(
        @NotBlank @Size(max = 50) String value,
        @NotBlank @Size(max = 100) String label,
        @NotNull UUID provinceUuid
) {
    public Municipality toModel() {
        return Municipality.builder().value(value).label(label).build();
    }
}
