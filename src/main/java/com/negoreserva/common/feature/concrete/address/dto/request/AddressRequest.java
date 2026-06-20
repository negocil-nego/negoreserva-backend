package com.negoreserva.common.feature.concrete.address.dto.request;

import com.negoreserva.common.feature.concrete.address.model.Address;

import java.util.UUID;

public record AddressRequest(
        String complement,
        UUID provinceUuid,
        UUID municipalityUuid,
        Double latitude,
        Double longitude
) {
    public Address toModel() {
        return Address.builder()
                .complement(complement)
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }
}
