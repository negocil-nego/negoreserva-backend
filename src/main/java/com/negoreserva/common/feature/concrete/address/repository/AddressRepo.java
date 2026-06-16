package com.negoreserva.common.feature.concrete.address.repository;

import com.negoreserva.common.feature.concrete.address.model.Address;
import com.negoreserva.common.feature.concrete.municipality.model.Municipality;
import com.negoreserva.common.feature.concrete.province.model.Province;
import com.negoreserva.common.feature.core.repository.ConcreteRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.jspecify.annotations.Nullable;

@Repository
public interface AddressRepo extends ConcreteRepository<Address> {

    Optional<Address> findByComplementAndProvinceAndMunicipality(
            @Nullable String complement,
            @Nullable Province province,
            @Nullable Municipality municipality
    );
}
