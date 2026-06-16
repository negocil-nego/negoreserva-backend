package com.negoreserva.common.feature.pivot.organization_address.repository;

import com.negoreserva.common.feature.concrete.address.model.Address;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.pivot.organization_address.model.OrganizationAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationAddressRepo extends JpaRepository<OrganizationAddress, Long> {
    Optional<OrganizationAddress> findByOrganizationAndAddress(Organization organization, Address address);
}
