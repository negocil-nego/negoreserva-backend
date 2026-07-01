package com.negoreserva.internal.organization.feature.permission.service;

import com.negoreserva.common.exception.NotFoundException;
import com.negoreserva.common.feature.concrete.permission.model.Permission;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.core.service.ConcreteService;
import com.negoreserva.internal.organization.feature.permission.enums.OrgPermissionData;
import com.negoreserva.internal.organization.feature.permission.repository.OrgPermissionRepository;
import com.negoreserva.internal.organization.feature.role.model.OrgRole;
import com.negoreserva.internal.organization.feature.role_permission.repository.OrgRolePermissionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrgPermissionService extends ConcreteService<Permission> {
    private final OrgPermissionRepository repository;
    private final OrgRolePermissionRepository rolePermissionRepository;

    public OrgPermissionService(OrgPermissionRepository repository, OrgRolePermissionRepository rolePermissionRepository) {
        super(repository);
        this.repository = repository;
        this.rolePermissionRepository = rolePermissionRepository;
    }

    @Override
    public List<Permission> findAll() {
        var names = Arrays.stream(OrgPermissionData.values()).map(OrgPermissionData::getPermission).map(Permission::getName).toList();
        return repository.findByNameIn(names);
    }

    @Override
    public Page<Permission> findAll(Pageable pageable) {
        var names = Arrays.stream(OrgPermissionData.values()).map(OrgPermissionData::getPermission).map(Permission::getName).toList();
        return repository.findByNameIn(names, pageable);
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

    public record RolePermissions(
        List<Permission> assignedPermissions,
        List<Permission> availablePermissions
    ) {}

    public RolePermissions getRolePermissions(OrgRole role) {
        var allPermissions = findAll();
        var assignedPermissionIds = rolePermissionRepository.findByOrgRole(role)
                .stream()
                .map(rp -> rp.getPermission().getId())
                .collect(Collectors.toSet());

        var assigned = allPermissions.stream()
                .filter(p -> assignedPermissionIds.contains(p.getId()))
                .toList();

        var available = allPermissions.stream()
                .filter(p -> !assignedPermissionIds.contains(p.getId()))
                .toList();

        return new RolePermissions(assigned, available);
    }
}
