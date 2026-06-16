package com.negoreserva.common.feature.pivot.user_organization.enums;

import com.negoreserva.common.feature.concrete.organization.enums.OrganizationFaker;
import com.negoreserva.common.feature.pivot.user_organization.model.UserOrganization;
import com.negoreserva.common.feature.concrete.user.enums.UserFaker;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserOrganizationFaker {
    BOB_ACME(UserOrganization.builder()
            .type(UserOrganizationType.CREATED)
            .active(true)
            .organization(OrganizationFaker.ACME.getOrganization())
            .user(UserFaker.BOB.getUser())
            .build()
    ),
    JANE_GLOBAL(UserOrganization.builder()
            .type(UserOrganizationType.CREATED)
            .active(true)
            .organization(OrganizationFaker.GLOBAL.getOrganization())
            .user(UserFaker.JANE.getUser())
            .build()
    ),
    JOHN_TECHCORP(UserOrganization.builder()
            .type(UserOrganizationType.CREATED)
            .active(true)
            .organization(OrganizationFaker.TECHCORP.getOrganization())
            .user(UserFaker.JOHN.getUser())
            .build()
    ),
    MARIA_POUSADA(UserOrganization.builder()
            .type(UserOrganizationType.CREATED)
            .active(true)
            .organization(OrganizationFaker.POUSADA_RECANTO.getOrganization())
            .user(UserFaker.MARIA.getUser())
            .build()
    ),
    PEDRO_PENSAO(UserOrganization.builder()
            .type(UserOrganizationType.CREATED)
            .active(true)
            .organization(OrganizationFaker.PENSAO_FAMILIAR.getOrganization())
            .user(UserFaker.PEDRO.getUser())
            .build()
    ),
    ANA_RESTAURANTE(UserOrganization.builder()
            .type(UserOrganizationType.CREATED)
            .active(true)
            .organization(OrganizationFaker.RESTAURANT_SABER.getOrganization())
            .user(UserFaker.ANA.getUser())
            .build()
    ),
    CARLOS_LOJA(UserOrganization.builder()
            .type(UserOrganizationType.CREATED)
            .active(true)
            .organization(OrganizationFaker.LOJA_BAIRR0.getOrganization())
            .user(UserFaker.CARLOS.getUser())
            .build()
    );

    private final UserOrganization userOrganization;
}
