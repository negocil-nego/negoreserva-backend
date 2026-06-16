package com.negoreserva.common.feature.concrete.organization.dto.response;

import java.util.UUID;

public record OrganizationResponse(
        UUID uuid,
        String name,
        String slug,
        String email,
        String description,
        String phone,
        String address,
        Integer rating,
        String image,
        String logo,
        String video,
        Boolean isHighlight
) { }