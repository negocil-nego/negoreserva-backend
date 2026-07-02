package com.negoreserva.common.feature.concrete.organization.service;

import com.negoreserva.common.feature.concrete.organization.exception.notfound.OrganizationNotFoundException;
import com.negoreserva.common.feature.concrete.organization.exception.notfound.OrganizationSlugNotFoundException;
import com.negoreserva.common.feature.concrete.organization.repository.OrganizationRepository;
import com.negoreserva.common.feature.concrete.organization.usecase.OrgOrganizationUseCase;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.user.service.UserService;
import com.negoreserva.common.feature.core.service.ConcreteService;
import com.negoreserva.common.util.RegexValidators;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrganizationService extends ConcreteService<Organization> {
    private final OrganizationRepository repository;
    private final UserService userService;

    public OrganizationService(OrganizationRepository repository, UserService userService) {
        super(repository);
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public Organization findByUuid(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow( () -> new OrganizationNotFoundException(uuid));
    }

    public Organization findBy(Authentication authentication) {
        var because = new OrgOrganizationUseCase(authentication, userService);
        return because.applyUseCase();
    }

    @Transactional
    public Organization findOrCreate(Organization organization) {
        return repository.findByName(organization.getName())
                .orElseGet(() -> save(organization));
    }

    public Organization findBySlug(String slug) {
        return repository.findBySlug(slug).orElseThrow(() -> new OrganizationSlugNotFoundException(slug));
    }

    public Organization findByUuidOrSlug(String uuidOrSlug) {
        return RegexValidators.isUuid(uuidOrSlug) ? findByUuid(UUID.fromString(uuidOrSlug)) : findBySlug(uuidOrSlug);
    }

}