package com.negoreserva.internal.organization.feature.user.service;

import com.negoreserva.common.exception.NotFoundException;
import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.common.feature.core.dto.request.PaginateRequest;
import com.negoreserva.common.feature.core.service.ConcreteService;
import com.negoreserva.common.feature.pivot.user_organization.enums.UserOrganizationType;
import com.negoreserva.common.feature.pivot.user_organization.model.UserOrganization;
import com.negoreserva.common.util.PasswordEncoderGenerator;
import com.negoreserva.internal.organization.feature.organization.service.OrgOrganizationService;
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

import java.util.Optional;
import java.util.UUID;

@Service
public class OrgUserService extends ConcreteService<User> {
    private final OrgUserRepository repository;
    private final OrgOrganizationService organizationService;
    private final OrgUserOrganizationService userOrganizationService;
    private final OrgRoleService roleService;
    private final OrgUserRoleService userRoleService;

    public OrgUserService(
            OrgUserRepository repository,
            OrgOrganizationService organizationService,
            OrgUserOrganizationService userOrganizationService,
            OrgRoleService roleService,
            OrgUserRoleService userRoleService
    ) {
        super(repository);
        this.repository = repository;
        this.organizationService = organizationService;
        this.userOrganizationService = userOrganizationService;
        this.roleService = roleService;
        this.userRoleService = userRoleService;
    }

    public OrgUserPaginate paginate(Pageable pageable, Authentication authentication) {
        var organization = organizationService.findBy(authentication);
        return OrgUserPaginate.of(repository.findAllByOrganizationId(organization.getId(), pageable));
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
        return create(data, null, authentication);
    }

    @Transactional
    public User create(User data, UUID roleUuid, Authentication authentication) {
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
        if (roleUuid != null) {
            userRoleService.sync(user, roleService.findByUuid(roleUuid));
        }
        return user;
    }

    @Override
    public User update(UUID uuid, User data) {
        var item = findByUuid(uuid);
        Optional.ofNullable(data.getBirthday()).ifPresent(item::setBirthday);
        Optional.ofNullable(data.getName()).ifPresent(item::setName);
        return repository.save(item);
    }
}
