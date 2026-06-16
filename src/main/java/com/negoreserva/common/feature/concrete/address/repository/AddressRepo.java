package com.negoreserva.common.feature.concrete.address.repository;

import com.negoreserva.common.feature.concrete.address.model.Address;
import com.negoreserva.common.feature.core.repository.ConcreteRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.jspecify.annotations.Nullable;

@Repository
public interface AddressRepo extends ConcreteRepository<Address> {

    Optional<Address> findByStreetAndNumberAndCityAndState(
            @Nullable String street,
            @Nullable String number,
            @Nullable String city,
            @Nullable String state
    );
}
