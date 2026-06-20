package com.negoreserva.internal.admin.feature.role.service;

import com.negoreserva.common.feature.concrete.role.dto.queryparam.RoleFilterQueryParam;
import com.negoreserva.internal.admin.feature.role.dto.response.RolePaginate;
import com.negoreserva.internal.admin.feature.role.repository.AdminRoleRepository;
import com.negoreserva.internal.admin.feature.role.model.Role;
import com.negoreserva.internal.admin.feature.role.query.RoleFilterSpecification;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.core.service.ConcreteService;
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

    public RolePaginate findAll(Pageable pageable) {
        var page = repository.findAll(pageable);
        return RolePaginate.of(page);
    }

    public RolePaginate findAll(PaginateRequest paginateRequest) {
        return findAll(PageRequest.of(paginateRequest.pageNumber(), paginateRequest.pageSize()));
    }

    public RolePaginate findAll(RoleFilterQueryParam filter) {
        var pageRequest = PageRequest.of(
                Optional.of(filter.getPageNumber()).orElse(0),
                Optional.of(filter.getPageSize()).orElse(10)
        );
        var spec = new RoleFilterSpecification(filter);
        var page = findAll(spec, pageRequest);
        return RolePaginate.of(page);
    }

    public Role findOrCreate(Role role) {
        return repository.findByCode(role.getName()).map(repository::save).orElseGet(() -> repository.save(role));
    }
}
