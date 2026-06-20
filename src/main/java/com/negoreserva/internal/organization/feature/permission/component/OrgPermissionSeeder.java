package com.negoreserva.internal.organization.feature.permission.component;

import com.negoreserva.common.feature.concrete.permission.model.Permission;
import com.negoreserva.internal.admin.feature.permission.service.AdminPermissionService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component
public class OrgPermissionSeeder {
    private final AdminPermissionService service;

    public OrgPermissionSeeder(AdminPermissionService service) {
        this.service = service;
    }

    @Transactional
    public List<Permission> seed() {
        Map<String, String> permissions = Map.ofEntries(
                Map.entry("READ_USER", "Permite visualizar usuários da organização."),
                Map.entry("CREATE_USER", "Permite criar usuários da organização."),
                Map.entry("EDIT_USER", "Permite editar usuários da organização."),
                Map.entry("REMOVE_USER", "Permite remover usuários da organização."),
                Map.entry("READ_PRODUCT", "Permite visualizar produtos da organização."),
                Map.entry("CREATE_PRODUCT", "Permite criar produtos da organização."),
                Map.entry("EDIT_PRODUCT", "Permite editar produtos da organização."),
                Map.entry("REMOVE_PRODUCT", "Permite remover produtos da organização."),
                Map.entry("CREATE_CATALOG", "Permite criar catálogos da organização."),
                Map.entry("EDIT_CATALOG", "Permite editar catálogos da organização."),
                Map.entry("REMOVE_CATALOG", "Permite remover catálogos da organização."),
                Map.entry("READ_ROLE", "Permite visualizar cargos da organização."),
                Map.entry("CREATE_ROLE", "Permite criar cargos da organização."),
                Map.entry("EDIT_ROLE", "Permite editar cargos da organização."),
                Map.entry("REMOVE_ROLE", "Permite remover cargos da organização."),
                Map.entry("READ_PERMISSION", "Permite visualizar permissões da organização."),
                Map.entry("CREATE_PERMISSION", "Permite criar permissões da organização."),
                Map.entry("EDIT_PERMISSION", "Permite editar permissões da organização."),
                Map.entry("REMOVE_PERMISSION", "Permite remover permissões da organização.")
        );

        return permissions.entrySet().stream()
                .map(entry -> service.findOrCreate(Permission.builder()
                        .name(entry.getKey())
                        .description(entry.getValue())
                        .build()))
                .toList();
    }
}
