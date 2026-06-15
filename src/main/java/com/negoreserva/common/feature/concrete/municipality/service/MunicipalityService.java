package com.negoreserva.common.feature.concrete.municipality.service;

import com.negoreserva.common.feature.concrete.municipality.dto.response.MunicipalityResponse;
import com.negoreserva.common.feature.concrete.municipality.model.Municipality;
import com.negoreserva.common.feature.concrete.municipality.repository.MunicipalityRepo;
import com.negoreserva.common.feature.core.service.ConcreteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipalityService extends ConcreteService<Municipality> {
    private final MunicipalityRepo repository;

    public MunicipalityService(MunicipalityRepo repository) {
        super(repository);
        this.repository = repository;
    }

    public List<MunicipalityResponse> findByProvinceValue(String provinceValue) {
        return repository.findByProvinceValue(provinceValue)
                .stream()
                .map(MunicipalityResponse::of)
                .toList();
    }
}
