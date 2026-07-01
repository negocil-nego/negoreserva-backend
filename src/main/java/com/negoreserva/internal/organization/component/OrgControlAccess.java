package com.negoreserva.internal.organization.component;

import com.negoreserva.internal.organization.feature.permission.enums.OrgPermissionData;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class OrgControlAccess {

    public void canPermission(OrgPermissionData permissionData, Authentication authentication) {

    }

}
