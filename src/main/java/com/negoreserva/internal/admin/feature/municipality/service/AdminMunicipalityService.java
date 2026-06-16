package com.negoreserva.internal.admin.feature.municipality.service;

import com.negoreserva.common.feature.concrete.municipality.dto.queryparam.MunicipalityFilterQueryParam;
import com.negoreserva.common.feature.concrete.municipality.dto.response.MunicipalityPaginate;
import com.negoreserva.common.feature.concrete.municipality.exception.notfound.MunicipalityNotFoundException;
import com.negoreserva.internal.admin.feature.municipality.query.MunicipalityFilterSpecification;
import com.negoreserva.common.feature.concrete.municipality.repository.MunicipalityRepo;
import com.negoreserva.common.feature.concrete.municipality.model.Municipality;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.core.service.ConcreteService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AdminMunicipalityService extends ConcreteService<Municipality> {
    private final MunicipalityRepo repository;

    public AdminMunicipalityService(MunicipalityRepo repository) {
        super(repository);
        this.repository = repository;
    }

    public MunicipalityPaginate paginate(Pageable pageable) {
        var page = repository.findAll(pageable);
        return MunicipalityPaginate.of(page);
    }

    public MunicipalityPaginate paginate(PaginateRequest paginateRequest) {
        return paginate(PageRequest.of(paginateRequest.pageNumber(), paginateRequest.pageSize()));
    }

    public MunicipalityPaginate paginate(MunicipalityFilterQueryParam filter) {
        var pageRequest = PageRequest.of(
                Optional.of(filter.getPageNumber()).orElse(0),
                Optional.of(filter.getPageSize()).orElse(10)
        );
        var spec = new MunicipalityFilterSpecification(filter);
        var page = findAll(spec, pageRequest);
        return MunicipalityPaginate.of(page);
    }

    @Override
    public Municipality findByUuid(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(() -> new MunicipalityNotFoundException(uuid));
    }
}
