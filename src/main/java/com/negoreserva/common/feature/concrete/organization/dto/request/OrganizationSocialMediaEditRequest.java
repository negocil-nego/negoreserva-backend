package com.negoreserva.common.feature.concrete.organization.dto.request;

import jakarta.validation.constraints.Size;

public record OrganizationSocialMediaEditRequest(
        @Size(max = 500)
        String facebook,
        @Size(max = 500)
        String instagram,
        @Size(max = 500)
        String youtube,
        @Size(max = 500)
        String tiktok,
        @Size(max = 500)
        String linkedin
) {
}
