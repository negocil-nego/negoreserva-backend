package com.negoreserva.external.product.dto.response;

import com.negoreserva.common.feature.concrete.organization.dto.response.OrganizationResponse;
import com.negoreserva.common.feature.concrete.product.model.Product;
import com.negoreserva.external.product.price.ProductPriceGetOrgResponse;

import java.util.List;
import java.util.UUID;

public record ProductDetailResponse (
        UUID uuid,
        String name,
        String slug,
        String description,
        String image,
        OrganizationResponse organization,
        List<ProductFileGetOrgResponse> files,
        List<ProductTagInfoGetOrgResponse> tags,
        List<ProductPriceGetOrgResponse> prices
){

    public static ProductDetailResponse of(Product product) {

        var files = product.getProductFiles().stream()
                .map(ProductFileGetOrgResponse::of)
                .toList();

        var tags = product.getProductTagInfos().stream()
                .map(ProductTagInfoGetOrgResponse::of)
                .toList();

        var prices = product.getProductPrices().stream()
                .map(ProductPriceGetOrgResponse::of)
                .toList();

        return new ProductDetailResponse(
                product.getUuid(),
                product.getName(),
                product.getSlug(),
                product.getDescription(),
                product.getImage(),
                OrganizationResponse.of(product.getOrganization()),
                files,
                tags,
                prices
        );
    }

}
