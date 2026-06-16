package com.negoreserva.common.feature.concrete.catalog.dto.response;

import com.negoreserva.common.feature.concrete.catalog.enums.CatalogType;
import com.negoreserva.common.feature.concrete.catalog.model.Catalog;
import com.negoreserva.common.feature.concrete.organization.dto.response.OrganizationResponse;
import com.negoreserva.common.feature.concrete.organization.model.Organization;

import java.util.Optional;
import java.util.UUID;

public record CatalogResponse(
        UUID uuid,
        String name,
        String description,
        String imgUrl,
        String slug,
        CatalogType type,
        OrganizationResponse organization
) {
    public static CatalogResponse of(Catalog catalog) {
        var imageUrl = Optional.ofNullable(catalog.getImgUrl())
                .filter(url -> !url.isBlank())
                .or(() -> Optional.ofNullable(catalog.getOrganization()).map(Organization::getImage))
                .orElse(null);

        return new CatalogResponse(
                catalog.getUuid(),
                catalog.getName(),
                catalog.getDescription(),
                imageUrl,
                catalog.getSlug(),
                catalog.getType(),
                OrganizationResponse.of(catalog.getOrganization())
        );
    }
}
