package com.negoreserva.internal.admin.feature.role.enums;

import com.negoreserva.internal.admin.feature.role.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdminRoleFaker {
    ADMIN(Role.builder().name("Gestor do sistema").code("MANAGE_SYSTEM").build()),
    ADDRESS(Role.builder().name("Gestor de endereço").code("MANAGE_ADDRESS").build()),
    CATEGORY(Role.builder().name("Gestor de categoria").code("MANAGE_CATEGORY").build()),
    MUNICIPALITY(Role.builder().name("Gestor de município").code("MANAGE_MUNICIPALITY").build()),
    ORGANIZATION(Role.builder().name("Gestor de organização").code("MANAGE_ORGANIZATION").build()),
    PLAIN(Role.builder().name("Gestor de plano").code("MANAGE_PLAIN").build()),
    PERMISSION(Role.builder().name("Gestor de permissões").code("MANAGE_PERMISSION").build()),
    PRODUCT(Role.builder().name("Gestor de produto").code("MANAGE_PRODUCT").build()),
    PRODUCT_FILE(Role.builder().name("Gestor de ficheiro de produtos").code("MANAGE_PRODUCT_FILE").build()),
    PROVINCE(Role.builder().name("Gestor de província").code("MANAGE_PROVINCE").build()),
    ROLE(Role.builder().name("Gestor de cargo").code("MANAGE_ROLE").build()),


    ROLE_PERMISSION(Role.builder().name("Gestor de associar cargo e permissão").code("MANAGE_ROLE_PERMISSION").build()),
    USER_ROLE(Role.builder().name("Gestor de associar utilizador e cargo").code("MANAGE_USER_ROLE").build());

    private final Role role;
}
