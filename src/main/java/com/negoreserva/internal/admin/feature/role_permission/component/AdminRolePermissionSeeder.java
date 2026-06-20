package com.negoreserva.internal.admin.feature.role_permission.component;

import com.negoreserva.common.feature.concrete.permission.model.Permission;
import com.negoreserva.common.feature.pivot.organization_category.enums.OrganizationCategoryData;
import com.negoreserva.common.feature.pivot.organization_category.model.OrganizationCategory;
import com.negoreserva.internal.admin.feature.role.model.Role;
import com.negoreserva.internal.admin.feature.role_permission.enums.AdminRolePermissionFaker;
import com.negoreserva.internal.admin.feature.role_permission.model.RolePermission;
import com.negoreserva.internal.admin.feature.role_permission.service.AdminRolePermissionService;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminRolePermissionSeeder {
    private final AdminRolePermissionService service;

    @Setter
    private List<Role> roles;

    @Setter
    private List<Permission> permissions;

    public AdminRolePermissionSeeder(AdminRolePermissionService service) {
        this.service = service;
    }

    @Transactional
    public List<RolePermission> seed() {
        List<RolePermission> items = new ArrayList<>();
        for (var data : AdminRolePermissionFaker.values()) {
            var role = roles.stream().filter(it -> it.getName().equals(data.getRole().getName())).findFirst().orElse(null);
            var permission = permissions.stream().filter(it -> it.getName().equals(data.getPermission().getName())).findFirst().orElse(null);

            if (role == null || permission == null) continue;

            var item = new RolePermission();

            item.setRole(role);
            item.setPermission(permission);

            items.add(service.findOrCreate(item));
        }
        return items;
    }
}
