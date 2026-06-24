package com.negoreserva.common.feature.pivot.user_organization.enums;

import com.negoreserva.common.feature.concrete.organization.enums.OrganizationData;
import com.negoreserva.common.feature.pivot.user_organization.model.UserOrganization;
import com.negoreserva.common.feature.concrete.user.enums.UserData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserOrganizationFaker {
    BOB_ACME(UserOrganization.builder()
            .type(UserOrganizationType.CREATED)
            .active(true)
            .organization(OrganizationData.ACME.getOrganization())
            .user(UserData.BOB.getUser())
            .build()
    ),
    JANE_GLOBAL(UserOrganization.builder()
            .type(UserOrganizationType.CREATED)
            .active(true)
            .organization(OrganizationData.GLOBAL.getOrganization())
            .user(UserData.JANE.getUser())
            .build()
    ),
    JOHN_TECHCORP(UserOrganization.builder()
            .type(UserOrganizationType.CREATED)
            .active(true)
            .organization(OrganizationData.TECHCORP.getOrganization())
            .user(UserData.JOHN.getUser())
            .build()
    ),
    MARIA_POUSADA(UserOrganization.builder()
            .type(UserOrganizationType.CREATED)
            .active(true)
            .organization(OrganizationData.POUSADA_RECANTO.getOrganization())
            .user(UserData.MARIA.getUser())
            .build()
    ),
    PEDRO_PENSAO(UserOrganization.builder()
            .type(UserOrganizationType.CREATED)
            .active(true)
            .organization(OrganizationData.PENSAO_FAMILIAR.getOrganization())
            .user(UserData.PEDRO.getUser())
            .build()
    ),
    ANA_RESTAURANTE(UserOrganization.builder()
            .type(UserOrganizationType.CREATED)
            .active(true)
            .organization(OrganizationData.RESTAURANT_SABER.getOrganization())
            .user(UserData.ANA.getUser())
            .build()
    ),
    CARLOS_LOJA(UserOrganization.builder()
            .type(UserOrganizationType.CREATED)
            .active(true)
            .organization(OrganizationData.LOJA_BAIRR0.getOrganization())
            .user(UserData.CARLOS.getUser())
            .build()
    );

    private final UserOrganization userOrganization;
}
