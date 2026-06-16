package com.negoreserva.common.feature.concrete.municipality.repository;

import com.negoreserva.common.feature.concrete.municipality.model.Municipality;
import com.negoreserva.common.feature.core.repository.ConcreteRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MunicipalityRepo extends ConcreteRepository<Municipality> {
    Optional<Municipality> findByUuid(UUID uuid);
    Optional<Municipality> findByValue(String value);
    List<Municipality> findByProvinceUuid(UUID provinceUuid);
}
