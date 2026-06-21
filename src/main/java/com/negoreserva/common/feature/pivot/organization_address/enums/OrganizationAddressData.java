package com.negoreserva.common.feature.pivot.organization_address.enums;

import com.negoreserva.common.feature.concrete.address.enums.AddressFaker;
import com.negoreserva.common.feature.concrete.address.model.Address;
import com.negoreserva.common.feature.concrete.organization.enums.OrganizationData;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrganizationAddressData {
    ACME_ADDRESS(OrganizationData.ACME.getOrganization(), AddressFaker.ACME.getAddress()),
    TECHCORP_ADDRESS(OrganizationData.TECHCORP.getOrganization(), AddressFaker.TECHCORP.getAddress()),
    GLOBAL_ADDRESS(OrganizationData.GLOBAL.getOrganization(), AddressFaker.GLOBAL.getAddress()),
    POUSADA_RECANTO_ADDRESS(OrganizationData.POUSADA_RECANTO.getOrganization(), AddressFaker.POUSADA_RECANTO.getAddress()),
    PENSAO_FAMILIAR_ADDRESS(OrganizationData.PENSAO_FAMILIAR.getOrganization(), AddressFaker.PENSAO_FAMILIAR.getAddress()),
    RESTAURANT_SABER_ADDRESS(OrganizationData.RESTAURANT_SABER.getOrganization(), AddressFaker.RESTAURANT_SABER.getAddress()),
    LOJA_BAIRRO_ADDRESS(OrganizationData.LOJA_BAIRR0.getOrganization(), AddressFaker.LOJA_BAIRRO.getAddress());

    private final Organization organization;
    private final Address address;
}
