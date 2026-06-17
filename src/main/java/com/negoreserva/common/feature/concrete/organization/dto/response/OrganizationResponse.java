package com.negoreserva.common.feature.concrete.organization.dto.response;

import com.negoreserva.common.feature.concrete.category.dto.response.CategoryResponse;
import com.negoreserva.common.feature.concrete.organization.model.Organization;

import java.util.List;
import java.util.UUID;

public record OrganizationResponse(
        UUID uuid,
        String name,
        String slug,
        String email,
        String description,
        String phone,
        String address,
        String province,
        String municipality,
        Integer rating,
        String image,
        String logo,
        String video,
        Boolean isHighlight,
        List<CategoryResponse> categories
) {

    public static OrganizationResponse of(Organization organization) {
        var addresses = organization.getAddresses();

        var categories = organization.getCategories().stream().map(CategoryResponse::of).toList();

        var address = addresses.stream().filter(a -> addresses.size() == 1 || a.isDefault()).findFirst();
        var municipality = address.map(a -> a.getMunicipality().getLabel()).orElse("");
        var province = address.map(a -> a.getProvince().getLabel()).orElse("");

        return new OrganizationResponse(
                organization.getUuid(),
                organization.getName(),
                organization.getSlug(),
                organization.getEmail(),
                organization.getDescription(),
                organization.getPhone(),
                organization.getAddress(),
                province,
                municipality,
                organization.getRating(),
                organization.getImage(),
                organization.getLogo(),
                organization.getVideo(),
                organization.getIsHighlight(),
                categories
        );
    }
}