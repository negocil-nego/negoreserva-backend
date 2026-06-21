package com.negoreserva.internal.organization.feature.permission.service;

import com.negoreserva.common.exception.NotFoundException;
import com.negoreserva.common.feature.concrete.permission.model.Permission;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.core.service.ConcreteService;
import com.negoreserva.internal.organization.feature.permission.repository.OrgPermissionRepository;
import org.springframework.data.domain.Page;
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

    @Override
    public Page<Permission> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Permission> findAll(PaginateRequest request) {
        int pageNumber = Optional.ofNullable(request).map(PaginateRequest::pageNumber).orElse(0);
        int pageSize = Optional.ofNullable(request).map(PaginateRequest::pageSize).orElse(10);
        return findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Permission findByUuid(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(NotFoundException::new);
    }

    public Permission findOrCreate(Permission permission) {
        return repository.findByName(permission.getName()).orElseGet(() -> repository.save(permission));
    }
}
