package com.negoreserva.common.feature.concrete.province.service;

import com.negoreserva.common.feature.concrete.province.model.Province;
import com.negoreserva.common.feature.concrete.province.repository.ProvinceRepo;
import com.negoreserva.common.feature.core.service.ConcreteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProvinceService extends ConcreteService<Province> {
    private final ProvinceRepo repository;

    public ProvinceService(ProvinceRepo repository) {
        super(repository);
        this.repository = repository;
    }

    public List<Province> findAllProvinceResponses() {
        return repository.findAll();
    }

    @Transactional
    public Province findOrCreate(Province province) {
        return repository.findByValue(province.getValue()).orElseGet(() -> save(province));
    }
}
