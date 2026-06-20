package com.negoreserva.internal.admin.feature.organization.service;

import com.negoreserva.common.feature.concrete.organization.exception.notfound.OrganizationPhoneNotFoundException;
import com.negoreserva.common.feature.concrete.organization.exception.notfound.OrganizationNameNotFoundException;
import com.negoreserva.internal.admin.feature.organization.dto.queryparam.OrganizationFilterQueryParam;
import com.negoreserva.common.feature.concrete.organization.exception.notfound.OrganizationNotFoundException;
import com.negoreserva.internal.admin.feature.organization.query.OrganizationFilterSpecification;
import com.negoreserva.common.feature.concrete.organization.dto.response.OrganizationPaginate;
import com.negoreserva.common.feature.concrete.organization.repository.OrganizationRepository;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.core.service.ConcreteService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AdminOrganizationService extends ConcreteService<Organization> {
    private final OrganizationRepository repository;

    public AdminOrganizationService(OrganizationRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public OrganizationPaginate findAll(Pageable pageable) {
        var page = repository.findAll(pageable);
        return OrganizationPaginate.of(page);
    }

    public OrganizationPaginate findAll(PaginateRequest paginateRequest) {
        return this.findAll(PageRequest.of(paginateRequest.pageNumber(), paginateRequest.pageSize()));
    }

    public OrganizationPaginate findAll(OrganizationFilterQueryParam filter) {
        var pageRequest = PageRequest.of(
                Optional.of(filter.getPageNumber()).orElse(0),
                Optional.of(filter.getPageSize()).orElse(10)
        );
        var spec = new OrganizationFilterSpecification(filter);
        var page = findAll(spec, pageRequest);
        return OrganizationPaginate.of(page);
    }

    public Organization findByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new OrganizationNameNotFoundException(name));
    }

    public Organization findByPhone(String phone) {
        return repository.findByPhone(phone).orElseThrow(() -> new OrganizationPhoneNotFoundException(phone));
    }

    @Override
    public Organization findByUuid(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(() -> new OrganizationNotFoundException(uuid));
    }

    @Override
    public Organization update(UUID uuid, Organization data) {
        var item = findByUuid(uuid);
        item.setDescription(data.getDescription());
        item.setAddress(data.getAddress());
        item.setName(data.getName());
        return repository.save(item);
    }

}