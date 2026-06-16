package com.negoreserva.common.feature.concrete.catalog.service;

import com.negoreserva.common.exception.NotFoundException;
import com.negoreserva.common.feature.concrete.catalog.dto.queryparam.CatalogSearchFilterParam;
import com.negoreserva.common.feature.concrete.catalog.repository.CatalogRepo;
import com.negoreserva.common.feature.concrete.catalog.model.Catalog;
import com.negoreserva.common.feature.core.service.ConcreteService;
import com.negoreserva.common.util.RegexValidators;
import com.negoreserva.common.feature.concrete.catalog.dto.response.CatalogResponse;
import com.negoreserva.common.feature.concrete.catalog.query.CatalogSearchSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CatalogService extends ConcreteService<Catalog> {
    private final CatalogRepo repository;

    public CatalogService(CatalogRepo repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Catalog findByUuid(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(NotFoundException::new);
    }

    public Catalog findBySlug(String slug) {
        return repository.findBySlug(slug).orElseThrow(NotFoundException::new);
    }

    public Catalog findByUuidOrSlug(String uuidOrSlug) {
        return RegexValidators.isUuid(uuidOrSlug) ? findByUuid(UUID.fromString(uuidOrSlug)) : findBySlug(uuidOrSlug);
    }

    public Catalog findOrCreate(Catalog catalog) {
        return repository.findByName(catalog.getName()).orElseGet(() -> save(catalog));
    }

    public Page<CatalogResponse> search(CatalogSearchFilterParam filter, Pageable pageable) {
        var spec = new CatalogSearchSpecification(filter);
        return repository.findAll(spec, pageable).map(CatalogResponse::of);
    }
}
