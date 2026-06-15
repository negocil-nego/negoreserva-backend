package com.negoreserva.common.feature.concrete.province.service;

import com.negoreserva.common.feature.concrete.province.dto.response.ProvinceResponse;
import com.negoreserva.common.feature.concrete.province.model.Province;
import com.negoreserva.common.feature.concrete.province.repository.ProvinceRepo;
import com.negoreserva.common.feature.core.service.ConcreteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceService extends ConcreteService<Province> {
    private final ProvinceRepo repository;

    public ProvinceService(ProvinceRepo repository) {
        super(repository);
        this.repository = repository;
    }

    public List<ProvinceResponse> findAllProvinceResponses() {
        return repository.findAll().stream().map(ProvinceResponse::of).toList();
    }
}
