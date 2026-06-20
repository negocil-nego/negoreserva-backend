package com.negoreserva.internal.admin.feature.role.component;

import com.negoreserva.internal.admin.feature.role.enums.AdminRoleFaker;
import com.negoreserva.internal.admin.feature.role.model.Role;
import com.negoreserva.internal.admin.feature.role.service.AdminRoleService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
public class AdminRoleSeeder {
    private final AdminRoleService service;

    public AdminRoleSeeder(AdminRoleService service) {
        this.service = service;
    }

    @Transactional
    public List<Role> seed() {
        return  Arrays.stream(AdminRoleFaker.values())
                .map(AdminRoleFaker::getRole)
                .map(service::findOrCreate)
                .toList();
    }
}
