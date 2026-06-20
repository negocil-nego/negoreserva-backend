package com.negoreserva.common.feature.general.me.service;

import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.organization.usecase.OrgOrganizationUseCase;
import com.negoreserva.common.feature.concrete.user.enums.UserType;
import com.negoreserva.common.feature.concrete.user.service.UserService;
import com.negoreserva.common.feature.general.me.dto.response.AccountSituationResponse;
import com.negoreserva.common.feature.concrete.organization.exception.ActiveUserWithoutOrganizationException;
import com.negoreserva.common.feature.concrete.organization.exception.UserWithMoreThanOneActiveOrganizationException;
import com.negoreserva.common.feature.concrete.organization.exception.UserWithoutOrganizationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeService {
    private final UserService userService;

    @Transactional(readOnly = true)
    public List<AccountSituationResponse> accountSituations(Authentication authentication) {
        var user = userService.findBy(authentication);
        var situations = new ArrayList<AccountSituationResponse>();

        if (user.getType() != UserType.ORGANIZATION) {
            return situations;
        }

        try {
            var organization = new OrgOrganizationUseCase(authentication, userService).findOrganization(user);
            validateOrganizationProfile(organization, situations);
        } catch (UserWithoutOrganizationException | ActiveUserWithoutOrganizationException exception) {
            situations.add(new AccountSituationResponse(
                    "ORGANIZATION_NOT_FOUND",
                    "A sua conta ainda não tem uma organização ativa associada."
            ));
        } catch (UserWithMoreThanOneActiveOrganizationException exception) {
            situations.add(new AccountSituationResponse(
                    "ORGANIZATION_ACTIVE_CONFLICT",
                    "A sua conta tem mais de uma organização ativa. Escolha apenas uma organização ativa."
            ));
        }

        return situations;
    }

    private void validateOrganizationProfile(Organization organization, List<AccountSituationResponse> situations) {
        if (isBlank(organization.getName())) {
            situations.add(new AccountSituationResponse(
                    "ORGANIZATION_NAME_REQUIRED",
                    "Informe o nome da organização para completar o perfil."
            ));
        }

        if (isBlank(organization.getImage())) {
            situations.add(new AccountSituationResponse(
                    "ORGANIZATION_IMAGE_REQUIRED",
                    "Adicione uma imagem de capa da organização."
            ));
        }

        if (isBlank(organization.getDescription())) {
            situations.add(new AccountSituationResponse(
                    "ORGANIZATION_DESCRIPTION_REQUIRED",
                    "Adicione uma descrição da organização."
            ));
        }

        if (organization.getProducts() == null || organization.getProducts().isEmpty()) {
            situations.add(new AccountSituationResponse(
                    "ORGANIZATION_PRODUCT_REQUIRED",
                    "Cadastre pelo menos um produto para a organização aparecer nas pesquisas."
            ));
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
