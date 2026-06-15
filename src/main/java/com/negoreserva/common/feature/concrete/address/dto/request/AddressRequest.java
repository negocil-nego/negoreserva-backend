package com.negoreserva.common.feature.concrete.address.dto.request;

import com.negoreserva.common.feature.concrete.address.model.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddressRequest(
        @Size(max = 100)
        String country,
        @Size(max = 100)
        String state,
        @Size(max = 100)
        String city,
        @Size(max = 100)
        String neighborhood,
        @Size(max = 255)
        String street,
        @Size(max = 20)
        String number,
        @Size(max = 20)
        String zipCode,
        @Size(max = 255)
        String complement,
        @Size(max = 50)
        String province,
        @Size(max = 50)
        String municipality,
        Double latitude,
        Double longitude
) {
    public Address toModel() {
        return Address.builder()
                .country(country)
                .state(state)
                .city(city)
                .neighborhood(neighborhood)
                .street(street)
                .number(number)
                .zipCode(zipCode)
                .complement(complement)
                .province(province)
                .municipality(municipality)
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }
}
