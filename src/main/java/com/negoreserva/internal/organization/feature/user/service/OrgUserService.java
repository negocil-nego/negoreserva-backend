package com.negoreserva.internal.organization.feature.user.service;

import com.negoreserva.common.exception.NotFoundException;
import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.core.service.ConcreteService;
import com.negoreserva.common.feature.pivot.user_organization.enums.UserOrganizationType;
import com.negoreserva.common.feature.pivot.user_organization.model.UserOrganization;
import com.negoreserva.common.util.PasswordEncoderGenerator;
import com.negoreserva.internal.organization.feature.organization.service.OrgOrganizationService;
import com.negoreserva.internal.organization.feature.role.model.OrgRole;
import com.negoreserva.internal.organization.feature.role.repository.OrgRoleRepository;
import com.negoreserva.internal.admin.feature.role.dto.response.RoleResponse;
import com.negoreserva.internal.organization.feature.role.service.OrgRoleService;
import com.negoreserva.internal.organization.feature.user.dto.response.OrgUserPaginate;
import com.negoreserva.internal.organization.feature.user.repository.OrgUserRepository;
import com.negoreserva.internal.organization.feature.user_organization.service.OrgUserOrganizationService;
import com.negoreserva.internal.organization.feature.user_role.service.OrgUserRoleService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrgUserService extends ConcreteService<User> {
    private final OrgUserRepository repository;
    private final OrgOrganizationService organizationService;
    private final OrgUserOrganizationService userOrganizationService;
    private final OrgRoleService roleService;
    private final OrgRoleRepository roleRepository;
    private final OrgUserRoleService userRoleService;

    public OrgUserService(
            OrgUserRepository repository,
            OrgOrganizationService organizationService,
            OrgUserOrganizationService userOrganizationService,
            OrgRoleService roleService,
            OrgRoleRepository roleRepository,
            OrgUserRoleService userRoleService
    ) {
        super(repository);
        this.repository = repository;
        this.organizationService = organizationService;
        this.userOrganizationService = userOrganizationService;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
        this.userRoleService = userRoleService;
    }

    public OrgUserPaginate paginate(Pageable pageable, Authentication authentication) {
        var organization = organizationService.findBy(authentication);
        var page = repository.findAllByOrganizationId(organization.getId(), pageable);
        return OrgUserPaginate.of(page, user -> userRoleService.findByUser(user)
                .stream()
                .map(role -> new RoleResponse(role.getUuid(), role.getName()))
                .toList());
    }

    public OrgUserPaginate paginate(PaginateRequest request, Authentication authentication) {
        int pageNumber = Optional.ofNullable(request).map(PaginateRequest::pageNumber).orElse(0);
        int pageSize = Optional.ofNullable(request).map(PaginateRequest::pageSize).orElse(10);
        return paginate(PageRequest.of(pageNumber, pageSize), authentication);
    }

    @Override
    public User findByUuid(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public User create(User data, Authentication authentication) {
        return create(data, List.of(), authentication);
    }

    @Transactional
    public User create(User data, List<UUID> roleUuids, Authentication authentication) {
        var organization = organizationService.findBy(authentication);
        var user = repository.findByEmail(data.getEmail()).orElseGet(() -> {
            data.setPassword(PasswordEncoderGenerator.encode(data.getPassword()));
            return repository.save(data);
        });
        if (!userOrganizationService.existsByUserAndOrganization(user, organization)) {
            userOrganizationService.save(UserOrganization.builder()
                    .user(user)
                    .organization(organization)
                    .type(UserOrganizationType.GUEST)
                    .active(true)
                    .build());
        }
        if (roleUuids != null && !roleUuids.isEmpty()) {
            var roles = roleUuids.stream()
                    .map(roleService::findByUuid)
                    .toList();
            userRoleService.syncRoles(user, roles);
        }
        return user;
    }

    @Override
    public User update(UUID uuid, User data) {
        var item = findByUuid(uuid);
        Optional.ofNullable(data.getName()).ifPresent(item::setName);
        return repository.save(item);
    }

    @Transactional
    public User update(UUID uuid, User data, List<UUID> roleUuids) {
        var item = update(uuid, data);
        if (roleUuids != null) {
            var roles = roleUuids.stream()
                    .map(roleService::findByUuid)
                    .toList();
            userRoleService.syncRoles(item, roles);
        }
        return item;
    }

    public List<OrgRole> getUserRoles(UUID userUuid) {
        var user = findByUuid(userUuid);
        return userRoleService.findByUser(user);
    }

    public List<User> listAll(Authentication authentication) {
        var organization = organizationService.findBy(authentication);
        return repository.findAllUsersByOrganizationId(organization.getId());
    }

    public List<User> listAllBySlug(String slug) {
        return repository.findAllByOrganizationSlug(slug);
    }

    public List<OrgRole> getOrgRoles(Authentication authentication) {
        var organization = organizationService.findBy(authentication);
        return roleRepository.findAllByOrganizationId(organization.getId(), Pageable.unpaged()).getContent();
    }
}
