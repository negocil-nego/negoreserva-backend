package com.negoreserva.common.feature.concrete.organization.component;

import com.negoreserva.common.feature.concrete.organization.enums.OrganizationData;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.organization.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrganizationSeeder {
    private final OrganizationService organizationService;

    @Transactional
    public List<Organization> seed() {
        List<Organization> items = new ArrayList<>();
        for (OrganizationData organization : OrganizationData.values()) {
            items.add(organizationService.findOrCreate(organization.getOrganization()));
        }
        return items;
    }
}