package com.negoreserva.common.feature.pivot.organization_category.enums;

import com.negoreserva.common.feature.concrete.category.model.Category;
import com.negoreserva.common.feature.concrete.organization.enums.OrganizationFaker;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.category.enums.CategoryFaker;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrganizationCategoryData {
    GLOBAL_HOTEL(OrganizationFaker.GLOBAL.getOrganization(), CategoryFaker.HOTEL.getCategory()),
    GLOBAL_RESTAURANT(OrganizationFaker.GLOBAL.getOrganization(), CategoryFaker.RESTAURANT.getCategory()),

    TECHCORP_ACCOMMODATION(OrganizationFaker.TECHCORP.getOrganization(), CategoryFaker.ACCOMMODATION.getCategory()),
    TECHCORP_BOTEQUIM(OrganizationFaker.TECHCORP.getOrganization(), CategoryFaker.BOTEQUIM.getCategory()),

    ACME_HOTEL(OrganizationFaker.ACME.getOrganization(), CategoryFaker.HOTEL.getCategory()),
    ACME_TOURISM_BOOKING(OrganizationFaker.ACME.getOrganization(), CategoryFaker.TOURISM_BOOKING.getCategory()),
    ACME_SHOP(OrganizationFaker.ACME.getOrganization(), CategoryFaker.SHOP.getCategory()),

    POUSADA_GUESTHOUSE(OrganizationFaker.POUSADA_RECANTO.getOrganization(), CategoryFaker.GUESTHOUSE.getCategory()),
    POUSADA_HOTEL(OrganizationFaker.POUSADA_RECANTO.getOrganization(), CategoryFaker.HOTEL.getCategory()),

    PENSAO_BED_BREAKFAST(OrganizationFaker.PENSAO_FAMILIAR.getOrganization(), CategoryFaker.BED_AND_BREAKFAST.getCategory()),

    RESTAURANTE_SABOR_CATEGORY(OrganizationFaker.RESTAURANT_SABER.getOrganization(), CategoryFaker.RESTAURANT.getCategory()),
    RESTAURANTE_BOTEQUIM(OrganizationFaker.RESTAURANT_SABER.getOrganization(), CategoryFaker.BOTEQUIM.getCategory()),

    LOJA_SHOP(OrganizationFaker.LOJA_BAIRR0.getOrganization(), CategoryFaker.SHOP.getCategory()),
    LOJA_TOURISM(OrganizationFaker.LOJA_BAIRR0.getOrganization(), CategoryFaker.TOURISM_BOOKING.getCategory());

    private final Organization organization;
    private final Category category;
}
