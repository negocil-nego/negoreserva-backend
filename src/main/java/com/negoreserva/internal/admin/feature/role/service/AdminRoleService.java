package com.negoreserva.internal.admin.feature.role.service;

import com.negoreserva.internal.admin.feature.role.query.RoleFilterQueryParam;
import com.negoreserva.internal.admin.feature.role.repository.AdminRoleRepository;
import com.negoreserva.internal.admin.feature.role.model.Role;
import com.negoreserva.internal.admin.feature.role.query.RoleFilterSpecification;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.core.service.ConcreteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminRoleService extends ConcreteService<Role> {
    private final AdminRoleRepository repository;

    public AdminRoleService(AdminRoleRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Page<Role> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Role> findAll(PaginateRequest paginateRequest) {
        return findAll(PageRequest.of(paginateRequest.pageNumber(), paginateRequest.pageSize()));
    }

    public Page<Role> findAll(RoleFilterQueryParam filter) {
        var pageRequest = PageRequest.of(
                Optional.of(filter.getPageNumber()).orElse(0),
                Optional.of(filter.getPageSize()).orElse(10)
        );
        var spec = new RoleFilterSpecification(filter);
        return findAll(spec, pageRequest);
    }

    public Role findOrCreate(Role role) {
        return repository.findByCode(role.getCode()).orElseGet(() -> repository.save(role));
    }
}
