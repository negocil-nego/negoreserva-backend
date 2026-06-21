package com.negoreserva.internal.admin.config;

import com.negoreserva.internal.admin.feature.permission.component.AdminPermissionSeeder;
import com.negoreserva.internal.admin.feature.role.component.AdminRoleSeeder;
import com.negoreserva.internal.admin.feature.role_permission.component.AdminRolePermissionSeeder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Order(2)
@Component
@RequiredArgsConstructor
public class AdminCommandLineRunner implements CommandLineRunner {
    private final AdminRolePermissionSeeder adminRolePermissionSeeder;
    private final AdminPermissionSeeder adminPermissionSeeder;
    private final AdminRoleSeeder adminRoleSeeder;

    @Override
    public void run(String... args) throws Exception {
        log.info("[SEEDER/Admin] Execute...");

        var permissions = adminPermissionSeeder.seed();
        var roles = adminRoleSeeder.seed();

        adminRolePermissionSeeder.setPermissions(permissions);
        adminRolePermissionSeeder.setRoles(roles);
        adminRolePermissionSeeder.seed();
    }
}
