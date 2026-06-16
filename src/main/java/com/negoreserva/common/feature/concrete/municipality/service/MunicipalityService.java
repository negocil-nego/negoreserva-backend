package com.negoreserva.common.feature.concrete.municipality.service;

import com.negoreserva.common.feature.concrete.municipality.model.Municipality;
import com.negoreserva.common.feature.concrete.municipality.repository.MunicipalityRepo;
import com.negoreserva.common.feature.concrete.province.service.ProvinceService;
import com.negoreserva.common.feature.core.service.ConcreteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MunicipalityService extends ConcreteService<Municipality> {
    private final MunicipalityRepo repository;
    private final ProvinceService provinceService;

    public MunicipalityService(MunicipalityRepo repository, ProvinceService provinceService) {
        super(repository);
        this.repository = repository;
        this.provinceService = provinceService;
    }

    public List<Municipality> findByProvince(UUID provinceUuid) {
        provinceService.findByUuid(provinceUuid);
        return repository.findByProvinceUuid(provinceUuid);
    }

    public Municipality findOrCreate(Municipality municipality) {
        return repository.findByValue(municipality.getValue()).orElseGet(() -> save(municipality));
    }
}
