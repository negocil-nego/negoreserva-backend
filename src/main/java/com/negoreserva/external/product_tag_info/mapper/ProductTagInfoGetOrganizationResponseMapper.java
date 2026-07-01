package com.negoreserva.external.feature.product_tag_info.mapper;

import com.negoreserva.common.feature.concrete.product.model.ProductTagInfo;
import com.negoreserva.external.feature.product_tag_info.response.ProductTagInfoGetOrgResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductTagInfoGetOrganizationResponseMapper {
    private ProductTagInfo productTagInfo;

    public ProductTagInfoGetOrgResponse toResponse() {
        return new ProductTagInfoGetOrgResponse(
          productTagInfo.getUuid(),
          productTagInfo.getIcon(),
          productTagInfo.getTitle(),
          productTagInfo.getValue()
        );
    }
}
