package com.negoreserva.common.feature.concrete.address.dto.response;

import com.negoreserva.common.feature.concrete.address.model.Address;
import com.negoreserva.common.feature.concrete.municipality.dto.response.MunicipalityResponse;
import com.negoreserva.common.feature.concrete.province.dto.response.ProvinceResponse;

import java.util.UUID;

public record AddressResponse(
        UUID uuid,
        String complement,
        ProvinceResponse province,
        MunicipalityResponse municipality,
        Double latitude,
        Double longitude,
        boolean isDefault
) {
    public static AddressResponse of(Address address) {
        return new AddressResponse(
                address.getUuid(),
                address.getComplement(),
                address.getProvince().toResponse(),
                address.getMunicipality().toResponse(),
                address.getLatitude(),
                address.getLongitude(),
                address.isDefault()
        );
    }
}
