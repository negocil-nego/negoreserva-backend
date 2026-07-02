package com.negoreserva.external.organization.service;

import com.negoreserva.common.feature.concrete.organization.exception.notfound.OrganizationNotFoundException;
import com.negoreserva.common.feature.concrete.organization.exception.notfound.OrganizationSlugNotFoundException;
import com.negoreserva.common.feature.concrete.organization.dto.queryparam.OrganizationSearchFilterParam;
import com.negoreserva.common.feature.concrete.organization.dto.response.OrganizationResponse;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.organization.repository.OrganizationRepository;
import com.negoreserva.common.util.RegexValidators;
import com.negoreserva.external.organization.query.OrganizationSearchSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExOrganizationService {

    private final OrganizationRepository organizationRepository;

    public Organization findByUuid(UUID uuid) {
        return organizationRepository.findByUuid(uuid).orElseThrow(() -> new OrganizationNotFoundException(uuid));
    }

    public Organization findBySlug(String slug) {
        return organizationRepository.findBySlug(slug).orElseThrow(() -> new OrganizationSlugNotFoundException(slug));
    }

    public Organization findByUuidOrSlug(String uuidOrSlug) {
        return RegexValidators.isUuid(uuidOrSlug) ? findByUuid(UUID.fromString(uuidOrSlug)) : findBySlug(uuidOrSlug);
    }

    public Page<OrganizationResponse> search(String query, Pageable pageable) {
        return organizationRepository.findByLikeContact(query, pageable)
                .map(OrganizationResponse::of);
    }

    public Page<OrganizationResponse> search(OrganizationSearchFilterParam filter, Pageable pageable) {
        var spec = new OrganizationSearchSpecification(filter);
        return organizationRepository.findAll(spec, pageable).map(OrganizationResponse::of);
    }
}
