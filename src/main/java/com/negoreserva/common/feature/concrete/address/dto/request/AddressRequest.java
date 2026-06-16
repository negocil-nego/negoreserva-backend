package com.negoreserva.common.feature.concrete.address.dto.request;

import com.negoreserva.common.feature.concrete.address.model.Address;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record AddressRequest(
        String complement,
        @Size(max = 50)
        UUID provinceUuid,
        @Size(max = 50)
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
