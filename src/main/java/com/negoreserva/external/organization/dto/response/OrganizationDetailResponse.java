package com.negoreserva.external.organization.dto.response;

import com.negoreserva.common.feature.concrete.address.dto.response.AddressResponse;
import com.negoreserva.common.feature.concrete.category.dto.response.CategoryResponse;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.organization.dto.response.OrganizationSocialMediaDetailResponse;
import com.negoreserva.common.feature.concrete.catalog.dto.response.CatalogResponse;
import com.negoreserva.external.product.dto.response.ProductDetailResponse;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record OrganizationDetailResponse(
        UUID uuid,
        String name,
        String slug,
        String description,
        String address,
        Integer rating,
        String logo,
        String image,
        String video,
        String email,
        String phone,
        List<CategoryResponse> categories,
        List<ProductDetailResponse> products,
        List<CatalogResponse> catalogs,
        OrganizationSocialMediaDetailResponse socialsMedia,
        List<AddressResponse> addresses
) {

    public static OrganizationDetailResponse of(Organization organization) {
        var socialsMedia = Objects.nonNull(organization.getOrganizationSocialMedia()) ? OrganizationSocialMediaDetailResponse.of(organization.getOrganizationSocialMedia()) : null;
        var products =  organization.getProducts().stream().map(ProductDetailResponse::of).toList();
        var catalogs =  organization.getCatalogs().stream().map(CatalogResponse::of).toList();
        var categories =  organization.getCategories().stream().map(CategoryResponse::of).toList();
        var addresses =  organization.getAddresses().stream().map(AddressResponse::of).toList();

        return new OrganizationDetailResponse(
                organization.getUuid(),
                organization.getName(),
                organization.getSlug(),
                organization.getDescription(),
                organization.getAddress(),
                organization.getRating(),
                organization.getLogo(),
                organization.getImage(),
                organization.getVideo(),
                organization.getEmail(),
                organization.getPhone(),
                categories,
                products,
                catalogs,
                socialsMedia,
                addresses
        );
    }

}
