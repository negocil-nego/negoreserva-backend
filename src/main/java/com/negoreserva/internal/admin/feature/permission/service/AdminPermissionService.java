package com.negoreserva.internal.admin.feature.permission.service;

import com.negoreserva.common.exception.NotFoundException;
import com.negoreserva.common.feature.concrete.permission.dto.response.PermissionPaginate;
import com.negoreserva.common.feature.concrete.permission.model.Permission;
import com.negoreserva.internal.admin.feature.permission.repository.AdminPermissionRepository;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.core.service.ConcreteService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.UUID;

@Service
public class AdminPermissionService extends ConcreteService<Permission> {
    private final AdminPermissionRepository repository;

    public AdminPermissionService(AdminPermissionRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public PermissionPaginate paginate(Pageable pageable) {
        return PermissionPaginate.of(repository.findAll(pageable));
    }

    public PermissionPaginate paginate(PaginateRequest request) {
        int pageNumber = Optional.ofNullable(request).map(PaginateRequest::pageNumber).orElse(0);
        int pageSize = Optional.ofNullable(request).map(PaginateRequest::pageSize).orElse(10);
        return paginate(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Permission findByUuid(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(NotFoundException::new);
    }

    @Override
    public Permission update(UUID uuid, Permission data) {
        var item = findByUuid(uuid);
        item.setName(data.getName());
        item.setDescription(data.getDescription());
        return repository.save(item);
    }

    public Permission findOrCreate(Permission permission) {
        return repository.findByName(permission.getName()).orElseGet(() -> repository.save(permission));
    }
}
