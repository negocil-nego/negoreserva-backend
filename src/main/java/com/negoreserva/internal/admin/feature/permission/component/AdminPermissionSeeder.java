package com.negoreserva.internal.admin.feature.permission.component;

import com.negoreserva.common.feature.concrete.permission.model.Permission;
import com.negoreserva.internal.admin.feature.permission.enums.AdminPermissionData;
import com.negoreserva.internal.admin.feature.permission.service.AdminPermissionService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
public class AdminPermissionSeeder {
    private final AdminPermissionService service;

    public AdminPermissionSeeder(AdminPermissionService service) {
        this.service = service;
    }

    @Transactional
    public List<Permission> seed() {
        return  Arrays.stream(AdminPermissionData.values())
                .map(AdminPermissionData::getPermission)
                .map(service::findOrCreate).toList();
    }
}
