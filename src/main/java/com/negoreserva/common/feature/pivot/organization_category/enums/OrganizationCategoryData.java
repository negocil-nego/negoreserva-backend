package com.negoreserva.common.feature.pivot.organization_category.enums;

import com.negoreserva.common.feature.concrete.category.model.Category;
import com.negoreserva.common.feature.concrete.organization.enums.OrganizationData;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.category.enums.CategoryData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrganizationCategoryData {
    GLOBAL_HOTEL(OrganizationData.GLOBAL.getOrganization(), CategoryData.HOTEL.getCategory()),
    GLOBAL_RESTAURANT(OrganizationData.GLOBAL.getOrganization(), CategoryData.RESTAURANT.getCategory()),

    TECHCORP_ACCOMMODATION(OrganizationData.TECHCORP.getOrganization(), CategoryData.ACCOMMODATION.getCategory()),
    TECHCORP_BOTEQUIM(OrganizationData.TECHCORP.getOrganization(), CategoryData.BOTEQUIM.getCategory()),

    ACME_HOTEL(OrganizationData.ACME.getOrganization(), CategoryData.HOTEL.getCategory()),
    ACME_TOURISM_BOOKING(OrganizationData.ACME.getOrganization(), CategoryData.HOTEL.getCategory()),
    ACME_SHOP(OrganizationData.ACME.getOrganization(), CategoryData.SHOP.getCategory()),

    POUSADA_GUESTHOUSE(OrganizationData.POUSADA_RECANTO.getOrganization(), CategoryData.GUESTHOUSE.getCategory()),
    POUSADA_HOTEL(OrganizationData.POUSADA_RECANTO.getOrganization(), CategoryData.HOTEL.getCategory()),

    PENSAO_BED_BREAKFAST(OrganizationData.PENSAO_FAMILIAR.getOrganization(), CategoryData.BED_AND_BREAKFAST.getCategory()),

    RESTAURANTE_SABOR_CATEGORY(OrganizationData.RESTAURANT_SABER.getOrganization(), CategoryData.RESTAURANT.getCategory()),
    RESTAURANTE_BOTEQUIM(OrganizationData.RESTAURANT_SABER.getOrganization(), CategoryData.BOTEQUIM.getCategory()),

    LOJA_SHOP(OrganizationData.LOJA_BAIRR0.getOrganization(), CategoryData.SHOP.getCategory()),
    LOJA_TOURISM(OrganizationData.LOJA_BAIRR0.getOrganization(), CategoryData.BOTEQUIM.getCategory());

    private final Organization organization;
    private final Category category;
}
