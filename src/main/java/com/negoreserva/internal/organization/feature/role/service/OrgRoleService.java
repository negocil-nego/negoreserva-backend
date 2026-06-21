package com.negoreserva.internal.organization.feature.role.service;

import com.negoreserva.internal.admin.feature.role.query.RoleFilterQueryParam;
import com.negoreserva.internal.organization.feature.role.query.OrgRoleFilterSpecification;
import com.negoreserva.internal.organization.feature.role.repository.OrgRoleRepository;
import com.negoreserva.internal.organization.feature.role.model.OrgRole;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.core.service.ConcreteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrgRoleService extends ConcreteService<OrgRole> {
    private final OrgRoleRepository repository;

    public OrgRoleService(OrgRoleRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Page<OrgRole> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<OrgRole> findAll(PaginateRequest paginateRequest) {
        return findAll(PageRequest.of(paginateRequest.pageNumber(), paginateRequest.pageSize()));
    }

    public Page<OrgRole> findAll(RoleFilterQueryParam filter) {
        var pageRequest = PageRequest.of(
                Optional.of(filter.getPageNumber()).orElse(0),
                Optional.of(filter.getPageSize()).orElse(10)
        );
        var spec = new OrgRoleFilterSpecification(filter);
        return findAll(spec, pageRequest);
    }

    public OrgRole findOrCreate(OrgRole orgRole) {
        return repository.findByNameAndOrganization(orgRole.getName(), orgRole.getOrganization())
                .orElseGet(() -> repository.save(orgRole));
    }
}
