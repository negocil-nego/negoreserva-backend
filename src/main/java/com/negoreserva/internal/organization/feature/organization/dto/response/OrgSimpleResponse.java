package com.negoreserva.internal.organization.feature.organization.dto.response;

import com.negoreserva.common.feature.concrete.organization.model.Organization;

import java.util.UUID;

public record OrgSimpleResponse(UUID uuid, String name, String logo) {
    public static OrgSimpleResponse of(Organization organization) {
        return new OrgSimpleResponse(
                organization.getUuid(),
                organization.getName(),
                organization.getLogo()
        );
    }
}
