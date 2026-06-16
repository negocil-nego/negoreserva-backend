package com.negoreserva.common.feature.pivot.organization_address.service;

import com.negoreserva.common.feature.concrete.address.model.Address;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.pivot.organization_address.model.OrganizationAddress;
import com.negoreserva.common.feature.pivot.organization_address.repository.OrganizationAddressRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class OrganizationAddressService {
    private final OrganizationAddressRepo organizationAddressRepo;

    public OrganizationAddressService(OrganizationAddressRepo organizationAddressRepo) {
        this.organizationAddressRepo = organizationAddressRepo;
    }

    public OrganizationAddress findOrCreate(OrganizationAddress organizationAddress) {
        return organizationAddressRepo.findByOrganizationAndAddress(
                organizationAddress.getOrganization(),
                organizationAddress.getAddress()
        ).orElseGet(() -> organizationAddressRepo.save(organizationAddress));
    }
}
