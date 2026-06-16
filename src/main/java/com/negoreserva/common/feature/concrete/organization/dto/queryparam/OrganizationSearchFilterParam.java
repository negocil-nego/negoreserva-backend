package com.negoreserva.common.feature.concrete.organization.dto.queryparam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationSearchFilterParam {
    private String q;
    private List<UUID> categoriesUuid;
    private String province;
    private String municipality;
    private Boolean isHighlight;
}
