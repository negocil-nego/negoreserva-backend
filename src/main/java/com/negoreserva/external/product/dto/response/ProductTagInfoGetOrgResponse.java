package com.negoreserva.external.product.dto.response;

import com.negoreserva.common.feature.concrete.product.model.ProductTagInfo;

import java.util.UUID;

public record ProductTagInfoGetOrgResponse(
        UUID uuid,
        String icon,
        String title,
        String value
) {

    public static ProductTagInfoGetOrgResponse of(ProductTagInfo productTagInfo) {
        return new ProductTagInfoGetOrgResponse(
                productTagInfo.getUuid(),
                productTagInfo.getIcon(),
                productTagInfo.getTitle(),
                productTagInfo.getValue()
        );
    }

}
