package com.negoreserva.common.feature.concrete.organization.dto.response;

import com.negoreserva.common.feature.concrete.organization.dto.response.OrganizationResponse;

import java.util.UUID;

public record OrganizationSocialMediaResponse(
        UUID uuid,
        UUID organizationUuid,
        String facebook,
        String instagram,
        String youtube,
        String titok,
        String linkedin,
        OrganizationResponse organization
) {
}
