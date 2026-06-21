package com.negoreserva.common.feature.pivot.organization_category.enums;

import com.negoreserva.common.feature.concrete.category.model.Category;
import com.negoreserva.common.feature.concrete.organization.enums.OrganizationData;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.category.enums.CategoryFaker;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrganizationCategoryData {
    GLOBAL_HOTEL(OrganizationData.GLOBAL.getOrganization(), CategoryFaker.HOTEL.getCategory()),
    GLOBAL_RESTAURANT(OrganizationData.GLOBAL.getOrganization(), CategoryFaker.RESTAURANT.getCategory()),

    TECHCORP_ACCOMMODATION(OrganizationData.TECHCORP.getOrganization(), CategoryFaker.ACCOMMODATION.getCategory()),
    TECHCORP_BOTEQUIM(OrganizationData.TECHCORP.getOrganization(), CategoryFaker.BOTEQUIM.getCategory()),

    ACME_HOTEL(OrganizationData.ACME.getOrganization(), CategoryFaker.HOTEL.getCategory()),
    ACME_TOURISM_BOOKING(OrganizationData.ACME.getOrganization(), CategoryFaker.TOURISM_BOOKING.getCategory()),
    ACME_SHOP(OrganizationData.ACME.getOrganization(), CategoryFaker.SHOP.getCategory()),

    POUSADA_GUESTHOUSE(OrganizationData.POUSADA_RECANTO.getOrganization(), CategoryFaker.GUESTHOUSE.getCategory()),
    POUSADA_HOTEL(OrganizationData.POUSADA_RECANTO.getOrganization(), CategoryFaker.HOTEL.getCategory()),

    PENSAO_BED_BREAKFAST(OrganizationData.PENSAO_FAMILIAR.getOrganization(), CategoryFaker.BED_AND_BREAKFAST.getCategory()),

    RESTAURANTE_SABOR_CATEGORY(OrganizationData.RESTAURANT_SABER.getOrganization(), CategoryFaker.RESTAURANT.getCategory()),
    RESTAURANTE_BOTEQUIM(OrganizationData.RESTAURANT_SABER.getOrganization(), CategoryFaker.BOTEQUIM.getCategory()),

    LOJA_SHOP(OrganizationData.LOJA_BAIRR0.getOrganization(), CategoryFaker.SHOP.getCategory()),
    LOJA_TOURISM(OrganizationData.LOJA_BAIRR0.getOrganization(), CategoryFaker.TOURISM_BOOKING.getCategory());

    private final Organization organization;
    private final Category category;
}
