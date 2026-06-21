package com.negoreserva.internal.organization.componet;

import com.negoreserva.common.feature.concrete.organization.service.OrganizationService;
import com.negoreserva.common.feature.core.event.OrganizationCreatedEvent;
import com.negoreserva.internal.organization.feature.organization.service.OrganizationCreateRolePermissionService;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrganizationNotificationListener {
    private final OrganizationCreateRolePermissionService organizationCreateRolePermissionService;
    private final OrganizationService organizationService;

    @ApplicationModuleListener
    public void onCreated(OrganizationCreatedEvent event) {
        var organization = organizationService.findByUuid(event.organizationId());
        organizationCreateRolePermissionService.createRoleAdmin(organization);
    }
}