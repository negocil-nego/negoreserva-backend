package com.negoreserva.internal.organization.feature.role.service;

import com.negoreserva.common.exception.NotFoundException;
import com.negoreserva.common.feature.concrete.org_role.dto.response.OrgRolePaginate;
import com.negoreserva.common.feature.concrete.org_role.model.OrgRole;
import com.negoreserva.common.feature.concrete.org_role.dto.request.OrgRoleRequest;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.core.service.ConcreteService;
import com.negoreserva.internal.organization.feature.permission.service.OrgPermissionService;
import com.negoreserva.internal.organization.feature.role_permission.service.OrgRolePermissionService;
import com.negoreserva.internal.organization.feature.role.repository.OrgRoleRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrgRoleService extends ConcreteService<OrgRole> {
    private final OrgRoleRepository repository;
    private final OrgPermissionService permissionService;
    private final OrgRolePermissionService rolePermissionService;

    public OrgRoleService(
            OrgRoleRepository repository,
            OrgPermissionService permissionService,
            OrgRolePermissionService rolePermissionService
    ) {
        super(repository);
        this.repository = repository;
        this.permissionService = permissionService;
        this.rolePermissionService = rolePermissionService;
    }

    public OrgRolePaginate paginate(Pageable pageable) {
        return OrgRolePaginate.of(repository.findAll(pageable));
    }

    public OrgRolePaginate paginate(PaginateRequest request) {
        int pageNumber = Optional.ofNullable(request).map(PaginateRequest::pageNumber).orElse(0);
        int pageSize = Optional.ofNullable(request).map(PaginateRequest::pageSize).orElse(10);
        return paginate(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public OrgRole findByUuid(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public OrgRole save(OrgRoleRequest request) {
        var role = save(request.toModel());
        rolePermissionService.sync(role, permissionService.findAllByUuid(request.permissionUuids()));
        return role;
    }

    @Override
    public OrgRole update(UUID uuid, OrgRole data) {
        var item = findByUuid(uuid);
        item.setName(data.getName());
        item.setDescription(data.getDescription());
        return repository.save(item);
    }

    @Transactional
    public OrgRole update(UUID uuid, OrgRoleRequest request) {
        var role = update(uuid, request.toModel());
        rolePermissionService.sync(role, permissionService.findAllByUuid(request.permissionUuids()));
        return role;
    }

    public OrgRole findOrCreate(OrgRole role) {
        return repository.findByName(role.getName()).map(item -> {
            item.setDescription(role.getDescription());
            return repository.save(item);
        }).orElseGet(() -> repository.save(role));
    }
}
