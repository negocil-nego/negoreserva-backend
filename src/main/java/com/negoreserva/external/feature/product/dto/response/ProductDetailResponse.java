package com.negoreserva.external.feature.product.dto.response;

import com.negoreserva.common.feature.concrete.organization.dto.response.OrganizationResponse;
import com.negoreserva.common.feature.concrete.product.model.Product;
import com.negoreserva.external.feature.product_file.dto.response.ProductFileGetOrgResponse;
import com.negoreserva.external.feature.product_price.dto.response.ProductPriceGetOrgResponse;
import com.negoreserva.external.feature.product_tag_info.response.ProductTagInfoGetOrgResponse;

import java.util.List;
import java.util.UUID;

public record ProductDetailResponse (
        UUID uuid,
        String name,
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
                product.getDescription(),
                product.getImage(),
                OrganizationResponse.of(product.getOrganization()),
                files,
                tags,
                prices
        );
    }

}
