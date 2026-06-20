package com.negoreserva.internal.admin.feature.province.service;

import com.negoreserva.common.feature.concrete.province.dto.queryparam.ProvinceFilterQueryParam;
import com.negoreserva.common.feature.concrete.province.dto.response.ProvincePaginate;
import com.negoreserva.common.feature.concrete.province.exception.notfound.ProvinceNotFoundException;
import com.negoreserva.internal.admin.feature.province.query.ProvinceFilterSpecification;
import com.negoreserva.common.feature.concrete.province.repository.ProvinceRepo;
import com.negoreserva.common.feature.concrete.province.model.Province;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.core.service.ConcreteService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AdminProvinceService extends ConcreteService<Province> {
    private final ProvinceRepo repository;

    public AdminProvinceService(ProvinceRepo repository) {
        super(repository);
        this.repository = repository;
    }

    public ProvincePaginate findAll(Pageable pageable) {
        var page = repository.findAll(pageable);
        return ProvincePaginate.of(page);
    }

    public ProvincePaginate findAll(PaginateRequest paginateRequest) {
        return this.findAll(PageRequest.of(paginateRequest.pageNumber(), paginateRequest.pageSize()));
    }

    public ProvincePaginate findAll(ProvinceFilterQueryParam filter) {
        var pageRequest = PageRequest.of(
                Optional.of(filter.getPageNumber()).orElse(0),
                Optional.of(filter.getPageSize()).orElse(10)
        );
        var spec = new ProvinceFilterSpecification(filter);
        var page = findAll(spec, pageRequest);
        return ProvincePaginate.of(page);
    }

    @Override
    public Province findByUuid(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(() -> new ProvinceNotFoundException(uuid));
    }

    public Province findByValue(String value) {
        return repository.findByValue(value).orElseThrow(() -> new ProvinceNotFoundException("Province not found by value %s".formatted(value)));
    }
}
