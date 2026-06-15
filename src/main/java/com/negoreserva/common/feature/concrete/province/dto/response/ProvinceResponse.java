package com.negoreserva.common.feature.concrete.province.dto.response;

import com.negoreserva.common.feature.concrete.province.model.Province;

import java.util.UUID;

public record ProvinceResponse(
        UUID uuid,
        String value,
        String label
) {
    public static ProvinceResponse of(Province province) {
        return new ProvinceResponse(
                province.getUuid(),
                province.getValue(),
                province.getLabel()
        );
    }
}
