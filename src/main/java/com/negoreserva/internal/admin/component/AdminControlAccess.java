package com.negoreserva.internal.admin.component;

import com.negoreserva.internal.admin.feature.permission.enums.AdminPermissionData;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AdminControlAccess {

    public void canPermission(AdminPermissionData permissionData, Authentication authentication) {

    }

}
