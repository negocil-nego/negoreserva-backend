package com.negoreserva.internal.organization.feature.permission.service;

import com.negoreserva.common.exception.NotFoundException;
import com.negoreserva.common.feature.concrete.permission.dto.response.PermissionPaginate;
import com.negoreserva.common.feature.concrete.permission.model.Permission;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.core.service.ConcreteService;
import com.negoreserva.internal.organization.feature.permission.repository.OrgPermissionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrgPermissionService extends ConcreteService<Permission> {
    private final OrgPermissionRepository repository;

    public OrgPermissionService(OrgPermissionRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public PermissionPaginate findAll(Pageable pageable) {
        return PermissionPaginate.of(repository.findAll(pageable));
    }

    public PermissionPaginate findAll(PaginateRequest request) {
        int pageNumber = Optional.ofNullable(request).map(PaginateRequest::pageNumber).orElse(0);
        int pageSize = Optional.ofNullable(request).map(PaginateRequest::pageSize).orElse(10);
        return this.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Permission findByUuid(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(NotFoundException::new);
    }

    public Permission findOrCreate(Permission permission) {
        return repository.findByName(permission.getName()).map(item -> {
            item.setDescription(permission.getDescription());
            return repository.save(item);
        }).orElseGet(() -> repository.save(permission));
    }
}
