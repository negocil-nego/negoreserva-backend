package com.negoreserva.common.feature.concrete.address.dto.response;

import com.negoreserva.common.feature.concrete.address.model.Address;
import java.util.UUID;

public record AddressResponse(
        UUID uuid,
        String country,
        String state,
        String city,
        String neighborhood,
        String street,
        String number,
        String zipCode,
        String complement,
        String province,
        String municipality,
        Double latitude,
        Double longitude
) {
    public static AddressResponse of(Address address) {
        return new AddressResponse(
                address.getUuid(),
                address.getCountry(),
                address.getState(),
                address.getCity(),
                address.getNeighborhood(),
                address.getStreet(),
                address.getNumber(),
                address.getZipCode(),
                address.getComplement(),
                address.getProvince(),
                address.getMunicipality(),
                address.getLatitude(),
                address.getLongitude()
        );
    }
}
