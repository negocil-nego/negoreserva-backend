package com.negoreserva.common.feature.concrete.province.repository;

import com.negoreserva.common.feature.concrete.province.model.Province;
import com.negoreserva.common.feature.core.repository.ConcreteRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProvinceRepo extends ConcreteRepository<Province> {
    Optional<Province> findByUuid(UUID uuid);
    Optional<Province> findByValue(String value);
}
