package com.negoreserva.common.feature.concrete.municipality.dto.response;

import com.negoreserva.common.feature.concrete.municipality.model.Municipality;

import java.util.UUID;

public record MunicipalityResponse(
        UUID uuid,
        String value,
        String label,
        UUID provinceUuid,
        String provinceValue,
        String provinceLabel
) {
    public static MunicipalityResponse of(Municipality municipality) {
        return new MunicipalityResponse(
                municipality.getUuid(),
                municipality.getValue(),
                municipality.getLabel(),
                municipality.getProvince().getUuid(),
                municipality.getProvince().getValue(),
                municipality.getProvince().getLabel()
        );
    }
}
