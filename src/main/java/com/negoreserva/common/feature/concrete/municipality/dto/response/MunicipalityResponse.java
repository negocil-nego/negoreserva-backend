package com.negoreserva.common.feature.concrete.municipality.dto.response;

import com.negoreserva.common.feature.concrete.municipality.model.Municipality;
import com.negoreserva.common.feature.concrete.province.dto.response.ProvinceResponse;

import java.util.UUID;

public record MunicipalityResponse(
        UUID uuid,
        String value,
        String label
) {
    public static MunicipalityResponse of(Municipality municipality) {
        return new MunicipalityResponse(
                municipality.getUuid(),
                municipality.getValue(),
                municipality.getLabel()
        );
    }
}
