package com.negoreserva.internal.organization.config;

import com.negoreserva.common.feature.concrete.organization.enums.OrganizationData;
import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.internal.organization.feature.organization.service.OrgOrganizationService;
import com.negoreserva.internal.organization.feature.organization.service.OrganizationCreateRolePermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Order(3)
@Component
@RequiredArgsConstructor
public class OrgOrganizationCommandLiner implements CommandLineRunner {
    private final OrganizationCreateRolePermissionService organizationCreateRolePermissionService;
    private final OrgOrganizationService orgOrganizationService;

    @Override
    public void run(String... args) throws Exception {
        log.info("[SEEDER/Organization] Execute...");

        var names = Arrays.stream(OrganizationData.values())
                .map(OrganizationData::getOrganization)
                .map(Organization::getName)
                .toList();

        var items = orgOrganizationService.findByNameIn(names);

        for (var it : items) {
            organizationCreateRolePermissionService.createRoleAdmin(it);
        }
    }
}
