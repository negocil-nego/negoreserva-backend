package com.negoreserva.common.feature.concrete.organization_social_media.enums;

import com.negoreserva.common.feature.concrete.organization.enums.OrganizationData;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrganizationSocialMediaData {
    ACME(
            OrganizationData.ACME.getOrganization(),
            "https://facebook.com/acmecorp",
            "https://instagram.com/acmecorp",
            "https://youtube.com/@acmecorp",
            "https://tiktok.com/@acmecorp",
            "https://linkedin.com/company/acmecorp"
    ),
    TECHCORP(
            OrganizationData.TECHCORP.getOrganization(),
            "https://facebook.com/techcorp",
            "https://instagram.com/techcorp",
            "https://youtube.com/@techcorp",
            "https://tiktok.com/@techcorp",
            "https://linkedin.com/company/techcorp"
    ),
    GLOBAL(
            OrganizationData.GLOBAL.getOrganization(),
            "https://facebook.com/globalsolutions",
            "https://instagram.com/globalsolutions",
            "https://youtube.com/@globalsolutions",
            "https://tiktok.com/@globalsolutions",
            "https://linkedin.com/company/globalsolutions"
    ),
    POUSADA_RECANTO(
            OrganizationData.POUSADA_RECANTO.getOrganization(),
            "https://facebook.com/pousadarecantoverde",
            "https://instagram.com/pousadarecantoverde",
            "https://youtube.com/@pousadarecantoverde",
            "https://tiktok.com/@pousadarecantoverde",
            "https://linkedin.com/company/pousadarecantoverde"
    ),
    PENSAO_FAMILIAR(
            OrganizationData.PENSAO_FAMILIAR.getOrganization(),
            "https://facebook.com/pensaofamiliar",
            "https://instagram.com/pensaofamiliar",
            "https://youtube.com/@pensaofamiliar",
            "https://tiktok.com/@pensaofamiliar",
            "https://linkedin.com/company/pensaofamiliar"
    ),
    RESTAURANTE_SABOR(
            OrganizationData.RESTAURANT_SABER.getOrganization(),
            "https://facebook.com/saborearterestaurante",
            "https://instagram.com/saborearterestaurante",
            "https://youtube.com/@saborearterestaurante",
            "https://tiktok.com/@saborearterestaurante",
            "https://linkedin.com/company/saborearterestaurante"
    ),
    LOJA_BAIRRO(
            OrganizationData.LOJA_BAIRR0.getOrganization(),
            "https://facebook.com/lojadobairro",
            "https://instagram.com/lojadobairro",
            "https://youtube.com/@lojadobairro",
            "https://tiktok.com/@lojadobairro",
            "https://linkedin.com/company/lojadobairro"
    );

    private final Organization organization;
    private final String facebook;
    private final String instagram;
    private final String youtube;
    private final String titok;
    private final String linkedin;
}
