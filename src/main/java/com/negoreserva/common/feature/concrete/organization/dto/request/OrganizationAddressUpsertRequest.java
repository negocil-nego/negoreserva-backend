package com.negoreserva.common.feature.concrete.organization.dto.request;

import jakarta.validation.constraints.Size;
import java.util.UUID;

public record OrganizationAddressUpsertRequest(
        @Size(max = 1000)
        String complement,
        UUID provinceUuid,
        UUID municipalityUuid
) {
}
