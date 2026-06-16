package com.negoreserva.common.feature.concrete.role.service;

import com.negoreserva.common.feature.concrete.role.dto.queryparam.RoleFilterQueryParam;
import com.negoreserva.common.feature.concrete.role.dto.response.RolePaginate;
import com.negoreserva.common.feature.concrete.role.repository.RoleDao;
import com.negoreserva.common.feature.concrete.role.model.Role;
import com.negoreserva.common.feature.concrete.role.query.RoleFilterSpecification;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.core.service.ConcreteService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleFacade extends ConcreteService<Role> {
    private final RoleDao repository;

    public RoleFacade(RoleDao repository) {
        super(repository);
        this.repository = repository;
    }

    public RolePaginate paginate(Pageable pageable) {
        var page = repository.findAll(pageable);
        return RolePaginate.of(page);
    }

    public RolePaginate paginate(PaginateRequest paginateRequest) {
        return paginate(PageRequest.of(paginateRequest.pageNumber(), paginateRequest.pageSize()));
    }

    public RolePaginate paginate(RoleFilterQueryParam filter) {
        var pageRequest = PageRequest.of(
                Optional.ofNullable(filter.getPageNumber()).orElse(0),
                Optional.ofNullable(filter.getPageSize()).orElse(10)
        );
        var spec = new RoleFilterSpecification(filter);
        var page = findAll(spec, pageRequest);
        return RolePaginate.of(page);
    }
}
