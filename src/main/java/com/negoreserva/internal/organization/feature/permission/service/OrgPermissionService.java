package com.negoreserva.internal.organization.feature.permission.service;

import com.negoreserva.common.exception.NotFoundException;
import com.negoreserva.common.feature.concrete.org_permission.dto.response.OrgPermissionPaginate;
import com.negoreserva.common.feature.concrete.org_permission.model.OrgPermission;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.core.service.ConcreteService;
import com.negoreserva.internal.organization.feature.permission.repository.OrgPermissionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.UUID;

@Service
public class OrgPermissionService extends ConcreteService<OrgPermission> {
    private final OrgPermissionRepository repository;

    public OrgPermissionService(OrgPermissionRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public OrgPermissionPaginate paginate(Pageable pageable) {
        return OrgPermissionPaginate.of(repository.findAll(pageable));
    }

    public OrgPermissionPaginate paginate(PaginateRequest request) {
        int pageNumber = Optional.ofNullable(request).map(PaginateRequest::pageNumber).orElse(0);
        int pageSize = Optional.ofNullable(request).map(PaginateRequest::pageSize).orElse(10);
        return paginate(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public OrgPermission findByUuid(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(NotFoundException::new);
    }

    public List<OrgPermission> findAllByUuid(List<UUID> uuids) {
        if (uuids == null || uuids.isEmpty()) return List.of();
        return uuids.stream().map(this::findByUuid).toList();
    }

    @Override
    public OrgPermission update(UUID uuid, OrgPermission data) {
        var item = findByUuid(uuid);
        item.setName(data.getName());
        item.setDescription(data.getDescription());
        return repository.save(item);
    }

    public OrgPermission findOrCreate(OrgPermission permission) {
        return repository.findByName(permission.getName()).map(item -> {
            item.setDescription(permission.getDescription());
            return repository.save(item);
        }).orElseGet(() -> repository.save(permission));
    }
}
