package com.negoreserva.common.feature.concrete.organization.dto.request;

import java.util.UUID;

public record OrganizationSocialMediaRequest(
        UUID organizationUuid,
        String facebook,
        String instagram,
        String youtube,
        String titok,
        String linkedin
) {
}
