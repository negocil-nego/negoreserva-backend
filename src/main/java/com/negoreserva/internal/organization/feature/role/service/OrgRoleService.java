package com.negoreserva.internal.organization.feature.role.service;

import com.negoreserva.internal.admin.feature.role.query.RoleFilterQueryParam;
import com.negoreserva.internal.organization.feature.permission.service.OrgPermissionService;
import com.negoreserva.internal.organization.feature.role.dto.response.RolePaginate;
import com.negoreserva.internal.organization.feature.role.query.OrgRoleFilterSpecification;
import com.negoreserva.internal.organization.feature.role.repository.OrgRoleRepository;
import com.negoreserva.internal.organization.feature.role.model.OrgRole;
import com.negoreserva.internal.organization.feature.role_permission.model.RolePermission;
import com.negoreserva.internal.organization.feature.role_permission.repository.OrgRolePermissionRepository;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.core.service.ConcreteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrgRoleService extends ConcreteService<OrgRole> {
    private final OrgRoleRepository repository;
    private final OrgRolePermissionRepository rolePermissionRepository;
    private final OrgPermissionService permissionService;

    public OrgRoleService(OrgRoleRepository repository, OrgRolePermissionRepository rolePermissionRepository, OrgPermissionService permissionService) {
        super(repository);
        this.repository = repository;
        this.rolePermissionRepository = rolePermissionRepository;
        this.permissionService = permissionService;
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

    public RolePaginate paginateByOrganization(Long organizationId, PaginateRequest request) {
        int pageNumber = Optional.ofNullable(request).map(PaginateRequest::pageNumber).orElse(0);
        int pageSize = Optional.ofNullable(request).map(PaginateRequest::pageSize).orElse(10);
        var page = repository.findAllByOrganizationId(organizationId, PageRequest.of(pageNumber, pageSize));
        return RolePaginate.of(page);
    }

    public OrgRole findOrCreate(OrgRole orgRole) {
        return repository.findByNameAndOrganization(orgRole.getName(), orgRole.getOrganization())
                .orElseGet(() -> repository.save(orgRole));
    }

    public List<OrgRole> findAllByOrganization(Long organizationId) {
        return repository.findAllByOrganizationId(organizationId, Pageable.unpaged()).getContent();
    }

    public void syncPermissions(OrgRole role, List<String> permissionUuids) {
        var existingLinks = rolePermissionRepository.findByOrgRole(role);
        rolePermissionRepository.deleteAll(existingLinks);

        if (permissionUuids != null) {
            for (var uuidStr : permissionUuids) {
                var permission = permissionService.findByUuid(UUID.fromString(uuidStr));
                var link = RolePermission.builder()
                        .orgRole(role)
                        .permission(permission)
                        .build();
                rolePermissionRepository.save(link);
            }
        }
    }
}
